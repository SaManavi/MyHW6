package com.example.admin.myhw6;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.admin.myhw6.Model.Task;

import java.util.UUID;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TASK="com.example.admin.myhw6.Extra task for detail";


    public static Intent newIntent(Context c, UUID taskId){
        Intent intent=new Intent(c,TaskDetailActivity.class);
        intent.putExtra(EXTRA_TASK,taskId);

        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
            UUID tId= (UUID) getIntent().getSerializableExtra(EXTRA_TASK);


        FragmentManager myFragMan=getSupportFragmentManager();

        if (myFragMan.findFragmentById(R.id.task_detail_container)==null){
            myFragMan.beginTransaction().add(R.id.task_detail_container,TaskDetailFragment.newInstance(tId))
                    .commit();
        }

    }
}
