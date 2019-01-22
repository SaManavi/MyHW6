package com.example.admin.myhw6;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskList;
import com.example.admin.myhw6.Model.User;

import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTasksFragment extends AbstractFragment {

    private User mcurrentUser;
    private Long mCurUsId;

    public static final String ARG_USER_ID = "ARG_USER_ID";

    public AllTasksFragment() {
// Required empty public constructor
    }

    public static AllTasksFragment newInstance(Long userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);

        AllTasksFragment fragment = new AllTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.task_detail,menu);

        //        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.del_task:
                TaskList.getInstance(getActivity()).delTasks(mCurUsId);
                Intent myIntent = MainActivity.newIntent(getActivity(),mCurUsId);
                startActivity(myIntent);
            getActivity().finish();

            break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Long userId = (Long) getArguments().getSerializable(ARG_USER_ID);
        // mCurrentUser = UserList.getInstance(getActivity()).getUserById(userId);
        mCurUsId=userId;

        setHasOptionsMenu(true);
    }


    @Override
    public Long getCurentUserId() {
        return mCurUsId;
    }

    @Override
    public int AttachingAdapter() {
        TaskList instance=TaskList.getInstance(getActivity());
        List<Task> tl=instance.getTasksList(mCurUsId);
        MyAdapter mAdapt=new MyAdapter(tl);
        mRecyclerView.setAdapter(mAdapt);
        mAdapt.notifyItemChanged(mCurrentPosition);
        return tl.size();


    }

    @Override
    public void onResume() {
        super.onResume();
        AttachingAdapter();


    }
}

