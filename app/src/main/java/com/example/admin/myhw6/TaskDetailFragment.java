package com.example.admin.myhw6;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {


    public static final String TASK_ID = "com.example.admin.myhw6.Task uuid as Id";
    private static final String DIALOG_TAG = "DialogDate";
    private static final int REQ_DATE_PICKER = 0;
    private static final int REQ_TIME_PICKER =1 ;

    private Task mCurrentTask;
    private TextView mTaskTitle;
    private TextView mTaskDes;
    private Button mEdit;
    private Button mDel;
    private Button mDone;
    private CheckBox mIsDoneCheckBox;
    private Button mTaskDate;
    private Button mTaskTime;
    private Button mConfirm;





    public static TaskDetailFragment newInstance(UUID TaskId){

        TaskDetailFragment myFrag=new TaskDetailFragment();
        Bundle args=new Bundle();
        args.putSerializable(TASK_ID,TaskId);
        myFrag.setArguments(args);
        return myFrag;
    }



    public TaskDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID mcurrentTaskId= (UUID) getArguments().getSerializable(TASK_ID);
        mCurrentTask=TaskList.getInstance(getActivity()).getTaskById(mcurrentTaskId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_task_detail, container, false);// show task detail...


        mTaskTitle=v.findViewById(R.id.task_detail_title);
        mTaskDes=v.findViewById(R.id.task_detail_des);
        mDone=v.findViewById(R.id.task_detail_done_butt);
        mIsDoneCheckBox =v.findViewById(R.id.task_detail_isDone);
        mTaskDate=v.findViewById(R.id.date_of_task_button);
        mTaskTime=v.findViewById(R.id.time_of_task_button);
        mDel=v.findViewById(R.id.task_detail_del_butt);
        mEdit=v.findViewById(R.id.task_detail_edit_butt);
        mConfirm=v.findViewById(R.id.task_detail_confirm);


        mConfirm.setVisibility(View.INVISIBLE);
        if(mCurrentTask.isIsdone()==true)mDone.setEnabled(false);
        mIsDoneCheckBox.setChecked(mCurrentTask.isIsdone());
        mTaskTitle.setText(mCurrentTask.getTitle());
        mTaskDes.setText(mCurrentTask.getDescription());

        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        SimpleDateFormat mTimeFormat = new SimpleDateFormat(" hh:mm a ");
        Date GetDate = mCurrentTask.getDate();
        String DateStr = mDateFormat.format(GetDate);
        String TimeStr=mTimeFormat.format(GetDate);
        mTaskDate.setText("Date Of Task:   "+DateStr);
        mTaskTime.setText("Time Of Task:   "+TimeStr);


        mTaskTitle.setEnabled(false);
        mTaskDes.setEnabled(false);
        mIsDoneCheckBox.setVisibility(View.INVISIBLE);
        mTaskDate.setEnabled(false);
        mTaskTime.setEnabled(false);

        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentTask.setIsdone(true);
                mDone.setEnabled(false);
            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTaskTitle.setEnabled(true);
                mTaskDes.setEnabled(true);
                mIsDoneCheckBox.setVisibility(View.VISIBLE);
                mTaskDate.setEnabled(true);
                mTaskTime.setEnabled(true);
                mConfirm.setVisibility(View.VISIBLE);

            }
        });

        mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TaskList.getInstance(getActivity()).removeTask(mCurrentTask.getTaskUUID());
                Toast.makeText(getActivity(), "Your Task has been deleted.", Toast.LENGTH_SHORT).show();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTaskTitle.setEnabled(false);
                mTaskDes.setEnabled(false);
                mIsDoneCheckBox.setVisibility(View.INVISIBLE);
                mTaskDate.setEnabled(false);
                mTaskTime.setEnabled(false);
                mConfirm.setEnabled(false);
                Toast.makeText(getActivity(), "Your task has been edited.", Toast.LENGTH_SHORT).show();

            }
        });


        mTaskTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCurrentTask.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mTaskDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCurrentTask.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mIsDoneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCurrentTask.setIsdone(isChecked);
                mDone.setEnabled(!isChecked);
            }
        });

        mTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCurrentTask.getDate());
                datePickerFragment.setTargetFragment(TaskDetailFragment.this,
                        REQ_DATE_PICKER);
                datePickerFragment.show(getFragmentManager(), DIALOG_TAG);
            }
        });


        mTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickFrag = TimePickerFragment.newInstance(mCurrentTask.getDate());
                timePickFrag.setTargetFragment(TaskDetailFragment.this,
                        REQ_TIME_PICKER);
                timePickFrag.show(getFragmentManager(), DIALOG_TAG);

            }
        });





        return v;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK)
            return;

        if (requestCode == REQ_DATE_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCurrentTask.setDate(date);

            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
//            SimpleDateFormat mTimeFormat = new SimpleDateFormat(" hh:mm a ");
            Date GetDate = mCurrentTask.getDate();
            String DateStr = mDateFormat.format(GetDate);
//            String TimeStr=mTimeFormat.format(GetDate);
            mTaskDate.setText(DateStr);
//            mTaskTime.setText(TimeStr);
        }
        if (requestCode == REQ_TIME_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCurrentTask.setDate(date);

//            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
            SimpleDateFormat mTimeFormat = new SimpleDateFormat(" hh:mm a ");
            Date GetDate = mCurrentTask.getDate();
//            String DateStr = mDateFormat.format(GetDate);
            String TimeStr=mTimeFormat.format(GetDate);
//            mTaskDate.setText(DateStr);
            mTaskTime.setText(TimeStr);
        }
    }


    @Override
    public void onPause() {
        super.onPause();

        TaskList.getInstance(getActivity()).update(mCurrentTask);

    }
}
