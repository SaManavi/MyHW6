package com.example.admin.myhw6;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.UUID;

public class AddingTaskActivity extends AppCompatActivity {
    private static final String EXTRA_USER_ID = "com.example.admin.myhw6.user_id";

    public static Intent newIntent(Context c, Long userId){
        Intent intent=new Intent(c,AddingTaskActivity.class);
        intent.putExtra(EXTRA_USER_ID,userId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Long user_Id = (Long) getIntent().getSerializableExtra(EXTRA_USER_ID);


        FragmentManager myFragMan=getSupportFragmentManager();

        if (myFragMan.findFragmentById(R.id.task_detail_container)==null){
            myFragMan.beginTransaction()
                    .replace(R.id.task_detail_container, new AddingTaskFragment().newInstance(user_Id))
                    .commit();
        }


    }
}
