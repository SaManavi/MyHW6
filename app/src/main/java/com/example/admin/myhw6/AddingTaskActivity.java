package com.example.admin.myhw6;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class AddingTaskActivity extends AppCompatActivity {

    public static Intent newIntent(Context c){
        Intent intent=new Intent(c,AddingTaskActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        FragmentManager myFragMan=getSupportFragmentManager();

        if (myFragMan.findFragmentById(R.id.task_detail_container)==null){
            myFragMan.beginTransaction().add(R.id.task_detail_container, new AddingTaskFragment())
                    .commit();
        }
    }
}
