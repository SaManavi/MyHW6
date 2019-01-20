package com.example.admin.myhw6;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_container);

        FragmentManager myFragManager = getSupportFragmentManager();
        Fragment f = new UserDetailFragment();

        if (myFragManager.findFragmentById(R.id.fragment_container) == null) {
            myFragManager.beginTransaction()
                    .add(R.id.fragment_container, f)
                    .commit();

        }
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        return intent;
    }
}