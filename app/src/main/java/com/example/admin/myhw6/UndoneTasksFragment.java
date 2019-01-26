package com.example.admin.myhw6;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskRepository;

import java.util.List;

import static com.example.admin.myhw6.AllTasksFragment.ARG_USER_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class UndoneTasksFragment extends AbstractFragment {


    private Long mCurUsId;

    public UndoneTasksFragment() {
// Required empty public constructor
    }





    public static UndoneTasksFragment newInstance(Long userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);

        UndoneTasksFragment fragment = new UndoneTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Long userId = (Long) getArguments().getSerializable(ARG_USER_ID);
//        mCurrentUser = UserRepository.getInstance(getActivity()).getUserById(userId);
        mCurUsId=userId;


    }


    @Override
    public Long getCurentUserId() {
        return mCurUsId;

    }



    @Override
    public int AttachingAdapter() {
        TaskRepository instance = TaskRepository.getInstance(getActivity());
        List<Task> tl = instance.getUnDoneTasksList(mCurUsId);
        MyAdapter mAdapt = new MyAdapter(tl);
        mRecyclerView.setAdapter(mAdapt);
        mAdapt.notifyItemChanged(mCurrentPosition);
        return tl.size();

    }



}