package com.example.admin.myhw6;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogOfTaskDetailFragment extends DialogFragment {



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

    public static DialogOfTaskDetailFragment newInstance(UUID TaskId) {

        DialogOfTaskDetailFragment myFrag=new DialogOfTaskDetailFragment();
        Bundle args=new Bundle();
        args.putSerializable(TASK_ID,TaskId);
        myFrag.setArguments(args);
        return myFrag;

    }

    public DialogOfTaskDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID mCurrentTaskId = (UUID) getArguments().getSerializable(TASK_ID);
        mCurrentTask=TaskList.getInstance(getActivity()).getTaskById(mCurrentTaskId);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_detail, container);

    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);


        mTaskTitle = v.findViewById(R.id.task_detail_title);
        mTaskDes = v.findViewById(R.id.task_detail_des);
        mDone = v.findViewById(R.id.task_detail_done_butt);
        mIsDoneCheckBox = v.findViewById(R.id.task_detail_isDone);
        mTaskDate = v.findViewById(R.id.date_of_task_button);
        mTaskTime = v.findViewById(R.id.time_of_task_button);
        mDel = v.findViewById(R.id.task_detail_del_butt);
        mEdit = v.findViewById(R.id.task_detail_edit_butt);
        mConfirm = v.findViewById(R.id.task_detail_confirm);


        mConfirm.setVisibility(View.INVISIBLE);
        mDel.setVisibility(View.INVISIBLE);
        mDone.setVisibility(View.INVISIBLE);


        mIsDoneCheckBox.setChecked(mCurrentTask.isIsdone());
        mTaskTitle.setText(mCurrentTask.getTitle());
        mTaskDes.setText(mCurrentTask.getDescription());


        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        SimpleDateFormat mTimeFormat = new SimpleDateFormat(" hh:mm a ");
        Date GetDate = mCurrentTask.getDate();
        String DateStr = mDateFormat.format(GetDate);
        String TimeStr = mTimeFormat.format(GetDate);
        mTaskDate.setText("Date Of Task:   " + DateStr);
        mTaskTime.setText("Time Of Task:   " + TimeStr);


        mTaskTitle.setEnabled(false);
        mTaskDes.setEnabled(false);
        mTaskDate.setEnabled(false);
        mTaskTime.setEnabled(false);
        mIsDoneCheckBox.setEnabled(false);



        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myInt= TaskDetailActivity.newIntent(getActivity(), mCurrentTask.getTaskUUID(),2);
                startActivity(myInt);
                getActivity().finish();



            }
        });


    }

}
