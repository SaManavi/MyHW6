package com.example.admin.myhw6;


import android.support.v4.app.Fragment;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskList;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends AbstractFragment {


    public ThirdFragment() {
// Required empty public constructor
    }

    @Override
    public int AttachingAdapter() {
        TaskList instance = TaskList.getInstance(getActivity());
        List<Task> tl = instance.getUnDoneTasksList();
        MyAdapter mAdapt = new MyAdapter(tl);
        mRecyclerView.setAdapter(mAdapt);
        mAdapt.notifyItemChanged(mCurrentPosition);
        return tl.size();

    }



}