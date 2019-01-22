package com.example.admin.myhw6;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TASK="com.example.admin.myhw6.Extra task for detail";
    private static final String FRAG_TO_SHOW ="com.example.admin.myhw6.Extra int for showing fragment" ;


    public static Intent newIntent(Context c, Long taskId, int i){
        Intent intent=new Intent(c,TaskDetailActivity.class);
        intent.putExtra(EXTRA_TASK,taskId);
        intent.putExtra(FRAG_TO_SHOW,i);

        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
            Long tId= (Long) getIntent().getSerializableExtra(EXTRA_TASK);
        int fragType=  getIntent().getIntExtra(FRAG_TO_SHOW,0);

        FragmentManager myFragMan=getSupportFragmentManager();

//        if (myFragMan.findFragmentById(R.id.task_detail_container)==null){
////            myFragMan.beginTransaction().add(R.id.task_detail_container,TaskDetailFragment.newInstance(tId))
//            myFragMan.beginTransaction().add(R.id.task_detail_container,DialogOfTaskEditingFragment.newInstance(tId))
//                    .commit();



       if(fragType==1) {
           DialogOfTaskDetailFragment myDiFragment = DialogOfTaskDetailFragment.newInstance(tId);
           myDiFragment.show(myFragMan, "dialog");
       }
       else if(fragType==2){
           DialogOfTaskEditingFragment myEditFragment = DialogOfTaskEditingFragment.newInstance(tId);
           myEditFragment.show(myFragMan, "dialog");

       }



//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        android.app.Fragment prev = getFragmentManager().findFragmentByTag("dialog");
//        if (prev != null) {
//            ft.remove(prev);
//        }
//        ft.addToBackStack(null);
//        DialogFragment dialogFragment = new MyCustomDialogFragment();
//        dialogFragment.show(ft, "dialog");
//        }

    }
}
