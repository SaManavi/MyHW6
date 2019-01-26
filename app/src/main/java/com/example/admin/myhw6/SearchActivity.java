package com.example.admin.myhw6;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SearchActivity extends AppCompatActivity {

    private static final String EXTRA_USER_ID ="com.example.admin.myhw6.user_id" ;
    private static final String SEARCH_STRING ="com.example.admin.myhw6.search_string" ;

    private String mSearchString;
    private Long mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchString=getIntent().getStringExtra(SEARCH_STRING);
        mUserId=getIntent().getLongExtra(EXTRA_USER_ID,-1);

        FragmentManager fm=getSupportFragmentManager();

        if (fm.findFragmentById(R.id.act_search_container) == null) {
            fm.beginTransaction()
                    .add(R.id.act_search_container,new SearchResultFragment().newInstance(mUserId,mSearchString))
                    .commit();
        }    }


    public static Intent newIntent(Context c, Long userId,String searchString) {
        Intent intent = new Intent(c, SearchActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        intent.putExtra(SEARCH_STRING,searchString);
        return intent;
    }


}
