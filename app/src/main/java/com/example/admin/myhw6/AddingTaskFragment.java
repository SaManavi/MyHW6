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
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskList;
import com.example.admin.myhw6.Model.User;
import com.example.admin.myhw6.Model.UserList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import static com.example.admin.myhw6.AllTasksFragment.ARG_USER_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddingTaskFragment extends Fragment {



    public static final String TASK_ID = "com.example.admin.myhw6.Task uuid as Id";
    private static final String DIALOG_TAG = "DialogDate";
    private static final int REQ_DATE_PICKER = 2;
    private static final int REQ_TIME_PICKER =3 ;

    private User mCurrentUser;


    private TextView mAddTaskTitle;
    private TextView mAddTaskDes;
    private Button mAddTaskDate;
    private Button mAddTaskTime;
    private Button mConfirm;
    //    private Switch mIsDone;
    private Task mNewTask;
    private Date mSetDate;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
    private UUID mCurUsId;



    public AddingTaskFragment() {
        // Required empty public constructor
    }

    public static AddingTaskFragment newInstance(UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);

        AddingTaskFragment fragment = new AddingTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UUID userId = (UUID) getArguments().getSerializable(ARG_USER_ID);
        mCurrentUser = UserList.getInstance(getActivity()).getUserById(userId);
        mCurUsId=userId;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_adding_task, container, false);

        mAddTaskTitle=v.findViewById(R.id.add_task_title);
        mAddTaskDes=v.findViewById(R.id.add_task_des);
        mAddTaskDate=v.findViewById(R.id.date_of_addTask_button);
        mAddTaskTime=v.findViewById(R.id.time_of_addTask_button);
        mConfirm=v.findViewById(R.id.add_task_confirm);
        mConfirm.setEnabled(false);
//        mIsDone=v.findViewById(R.id.add_task_isDone);
//        mIsDone.setChecked(false);

        mAddTaskTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mConfirm.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAddTaskTitle.getText().length()==0) Toast.makeText(getActivity(), "Enter Title!", Toast.LENGTH_SHORT).show();
                else {
                    if (mSetDate == null) mSetDate = new Date();
                    mNewTask = new Task(mAddTaskTitle.getText().toString(), mAddTaskDes.getText().toString(), false, mSetDate, mCurUsId);
                    TaskList.getInstance(getActivity()).addTask(mNewTask);
                    Toast.makeText(getActivity(), "Your task has been added...", Toast.LENGTH_SHORT).show();
                    mConfirm.setEnabled(false);
                    mAddTaskTitle.setEnabled(false);
                    mAddTaskDes.setEnabled(false);
                    mAddTaskDate.setEnabled(false);
                    mAddTaskTime.setEnabled(false);

                    Intent myIntent = MainActivity.newIntent(getActivity(), mNewTask.getUserId());
                    startActivity(myIntent);
//            getActivity().finish();

                }
            }
        });








        mAddTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date now=new Date();
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(now);
                datePickerFragment.setTargetFragment(AddingTaskFragment.this,
                        REQ_DATE_PICKER);
                datePickerFragment.show(getFragmentManager(), DIALOG_TAG);
            }
        });


        mAddTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date now=new Date();
                TimePickerFragment timePickFrag = TimePickerFragment.newInstance(now);
                timePickFrag.setTargetFragment(AddingTaskFragment.this,
                        REQ_TIME_PICKER);
                timePickFrag.show(getFragmentManager(), DIALOG_TAG);

            }
        });





        return v;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Calendar calendar = Calendar.getInstance();

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQ_DATE_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            calendar.setTime(date);

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);


        }




        if (requestCode == REQ_TIME_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            calendar.setTime(date);

            hour = calendar.get(Calendar.HOUR);
            min = calendar.get(Calendar.MINUTE);


            mSetDate = new GregorianCalendar(year, month, day,hour,min).getTime();

            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
            Date GetDate = mSetDate;
            String DateStr = mDateFormat.format(GetDate);
            mAddTaskDate.setText(DateStr);

            SimpleDateFormat mTimeFormat = new SimpleDateFormat(" hh:mm a ");
            Date GetDate2 = mSetDate;
            String TimeStr=mTimeFormat.format(GetDate2);
            mAddTaskTime.setText(TimeStr);
        }
    }

}
