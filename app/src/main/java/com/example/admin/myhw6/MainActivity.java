package com.example.admin.myhw6;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.myhw6.Model.TaskRepository;


public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_USER_ID = "com.example.admin.myhw6.user_id";


    private TabAdapter mTabAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private  Long user_Id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mTabAdapter = new TabAdapter(getSupportFragmentManager());


        user_Id = (Long) getIntent().getSerializableExtra(EXTRA_USER_ID);

        mTabAdapter.addFragment(new AllTasksFragment().newInstance(user_Id), "All Tasks");
        mTabAdapter.addFragment(new DoneTasksFragment().newInstance(user_Id), "Done");
        mTabAdapter.addFragment(new UndoneTasksFragment().newInstance(user_Id), "Undone");

        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    public static Intent newIntent(Context c, Long userId) {
        Intent intent = new Intent(c, MainActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater myInflater=getMenuInflater();
        myInflater.inflate(R.menu.task_detail,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.app_bar_searchitem:


                FragmentManager myFm = getSupportFragmentManager();

                DialogOfSearchFragment myDiFragment = DialogOfSearchFragment.newInstance(user_Id);

                myDiFragment.show(myFm, "dialog");



                break;
            default:
              return   super.onOptionsItemSelected(item);
        }
        return true;
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(user_Id==Long.MAX_VALUE){
            TaskRepository.getInstance(this).delTasks(user_Id);

            Toast.makeText(this, "Your tasks deleted ... ", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}