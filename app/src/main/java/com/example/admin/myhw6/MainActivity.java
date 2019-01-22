package com.example.admin.myhw6;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.myhw6.Model.TaskList;

import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_USER_ID = "com.example.admin.myhw6.user_id";


    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  Long user_Id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());


        user_Id = (Long) getIntent().getSerializableExtra(EXTRA_USER_ID);



//        User currentUser = UserList.getInstance(this).getUserById(user_Id);

        adapter.addFragment(new AllTasksFragment().newInstance(user_Id), "All Tasks");
        adapter.addFragment(new DoneTasksFragment().newInstance(user_Id), "Done");
        adapter.addFragment(new UndoneTasksFragment().newInstance(user_Id), "Undone");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    public static Intent newIntent(Context c, Long userId) {
        Intent intent = new Intent(c, MainActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(user_Id==Long.MAX_VALUE){
            TaskList.getInstance(this).delTasks(user_Id);

        Toast.makeText(this, "Your tasks deleted ... ", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}