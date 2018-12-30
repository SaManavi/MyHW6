package com.example.admin.myhw6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddingTaskFragment extends Fragment {
    private TextView mAddTaskTitle;
    private TextView mAddTaskDes;
    private TextView mAddTaskDate;
    private TextView mAddTaskTime;
    private Button mConfirm;


    public AddingTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_adding_task, container, false);

        mAddTaskTitle=v.findViewById(R.id.add_task_title);
        mAddTaskDes=v.findViewById(R.id.add_task_des);
        mAddTaskDate=v.findViewById(R.id.add_task_date);
        mAddTaskTime=v.findViewById(R.id.add_task_time);
        mConfirm=v.findViewById(R.id.add_task_confirm);
        mConfirm.setEnabled(false);

if(mAddTaskTitle.getText()!=null) {
    mConfirm.setEnabled(true);
    mConfirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Task mNewTask = new Task(mAddTaskTitle.getText().toString(), mAddTaskDes.getText().toString());// date and time?????????????
            TaskList.getInstance().addTask(mNewTask);
            Toast.makeText(getActivity(), "Your task added.", Toast.LENGTH_SHORT).show();
            mConfirm.setEnabled(false);
            mAddTaskTitle.setEnabled(false);
            mAddTaskDes.setEnabled(false);
        }
    });
}








        return v;
    }

}
