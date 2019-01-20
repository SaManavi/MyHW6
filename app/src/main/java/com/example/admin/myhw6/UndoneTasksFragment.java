package com.example.admin.myhw6;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskList;

import java.util.List;
import java.util.UUID;

import static com.example.admin.myhw6.AllTasksFragment.ARG_USER_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class UndoneTasksFragment extends AbstractFragment {


    private UUID mCurUsId;

    public UndoneTasksFragment() {
// Required empty public constructor
    }





    public static UndoneTasksFragment newInstance(UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);

        UndoneTasksFragment fragment = new UndoneTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID userId = (UUID) getArguments().getSerializable(ARG_USER_ID);
//        mCurrentUser = UserList.getInstance(getActivity()).getUserById(userId);
        mCurUsId=userId;


    }


    @Override
    public UUID getCurentUserId() {
        return mCurrentUser.getId();
    }



    @Override
    public int AttachingAdapter() {
        TaskList instance = TaskList.getInstance(getActivity());
        List<Task> tl = instance.getUnDoneTasksList(mCurUsId);
        MyAdapter mAdapt = new MyAdapter(tl);
        mRecyclerView.setAdapter(mAdapt);
        mAdapt.notifyItemChanged(mCurrentPosition);
        return tl.size();

    }



}