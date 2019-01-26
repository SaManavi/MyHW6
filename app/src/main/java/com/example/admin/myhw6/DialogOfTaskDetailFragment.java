package com.example.admin.myhw6;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskRepository;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Button mShare;
    private CheckBox mIsDoneCheckBox;
    private Button mTaskDate;
    private Button mTaskTime;
    private Button mConfirm;
    private ImageView mPhoto;
    private File mPhotoFile;


    public static DialogOfTaskDetailFragment newInstance(Long TaskId) {

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

        Long mCurrentTaskId = (Long) getArguments().getSerializable(TASK_ID);
        mCurrentTask= TaskRepository.getInstance(getActivity()).getTaskById(mCurrentTaskId);
        mPhotoFile = TaskRepository.getInstance(getActivity()).getPhotoFile(mCurrentTask);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_d2, container);


    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);


        mTaskTitle = v.findViewById(R.id.task_detail_title);
        mTaskDes = v.findViewById(R.id.task_detail_des);
        mPhoto = v.findViewById(R.id.task_detail_photo);
        mIsDoneCheckBox = v.findViewById(R.id.task_detail_isDone);
        mTaskDate = v.findViewById(R.id.date_of_task_button);
        mTaskTime = v.findViewById(R.id.time_of_task_button);
//        mDel = v.findViewById(R.id.task_detail_del_butt);
        mEdit = v.findViewById(R.id.task_detail_edit_butt);
        mConfirm = v.findViewById(R.id.task_detail_confirm);


        mConfirm.setVisibility(View.INVISIBLE);
            mPhoto.setImageURI(FileProvider.getUriForFile(getActivity(),"com.example.admin.myhw6.fileprovider", mPhotoFile));


        mIsDoneCheckBox.setChecked(mCurrentTask.getMIsdone());
        mTaskTitle.setText(mCurrentTask.getTitle());
        mTaskDes.setText(mCurrentTask.getMDescription());


        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        SimpleDateFormat mTimeFormat = new SimpleDateFormat(" hh:mm a ");
        Date GetDate = mCurrentTask.getMDate();
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
//                Intent myInt= TaskDetailActivity.newIntent(getActivity(), mCurrentTask.getTaskId(),2);
//                startActivity(myInt);
//                getActivity().finish();


                FragmentManager myFm = getFragmentManager();

                DialogOfTaskEditingFragment myDiFragment = DialogOfTaskEditingFragment.newInstance(mCurrentTask.getTaskId());
                myDiFragment.show(myFm, "dialog");


            }
        });


    }

}
