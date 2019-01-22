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
public class DoneTasksFragment extends AbstractFragment {


    private Long mCurUsId;

    public DoneTasksFragment() {
// Required empty public constructor
    }



    public static DoneTasksFragment newInstance(Long userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);

        DoneTasksFragment fragment = new DoneTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Long userId = (Long) getArguments().getSerializable(ARG_USER_ID);
//        mCurrentUser = UserList.getInstance(getActivity()).getUserById(userId);
        mCurUsId=userId;



    }

    @Override
    public Long getCurentUserId() {
        return mCurrentUser.getUserId();
    }




    @Override
    public int AttachingAdapter() {
        TaskList instance = TaskList.getInstance(getActivity());
        List<Task> tl = instance.getDoneTasksList(mCurUsId);
        MyAdapter mAdapt = new MyAdapter(tl);
        mRecyclerView.setAdapter(mAdapt);
        mAdapt.notifyItemChanged(mCurrentPosition);
        return  tl.size();

    }

}