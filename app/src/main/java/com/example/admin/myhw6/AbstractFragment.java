package com.example.admin.myhw6;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.myhw6.Model.Task;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AbstractFragment extends Fragment {


    protected RecyclerView mRecyclerView;
   // private Adapter mAdapter;
   protected int mCurrentPosition;
   protected TextView mEmptyList;


    public AbstractFragment() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_first, container, false);

        mRecyclerView=v.findViewById(R.id.recycler_all);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEmptyList=v.findViewById(R.id.recycler_empty);

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myInt=AddingTaskActivity.newIntent(getActivity());
                startActivity(myInt);

            }
        });


        AttachingAdapter();

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();

        int listSize= AttachingAdapter();
        if (listSize==0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyList.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyList.setVisibility(View.GONE);
        }    }

    public abstract int AttachingAdapter();


    protected class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTaskTitleOfHolder;
        private Button mTaskIconOfHolder;
        private Task mCurrentTask;



        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mTaskTitleOfHolder=itemView.findViewById(R.id.task_title_of_viewholder);
            mTaskIconOfHolder=itemView.findViewById(R.id.icon_of_viewholder);
            mCurrentPosition=getAdapterPosition();


            mTaskIconOfHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   Intent myInt= TaskDetailActivity.newIntent(getActivity(), mCurrentTask.getTaskUUID());
                    startActivity(myInt);

                }
            });


        }

        public void bind(Task ts) {
            mCurrentTask =ts;
            mTaskTitleOfHolder.setText(ts.getTitle());
            mTaskIconOfHolder.setText(mCurrentTask.getTitle().substring(0,1));

        }
    }



    protected class MyAdapter extends RecyclerView.Adapter<MyHolder>{

        private List<Task> mTaskArrayList;

        public MyAdapter(List<Task> myTaskList) {
            mTaskArrayList = myTaskList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater myInf=LayoutInflater.from(getActivity());
            View v=myInf.inflate(R.layout.list_item_view,viewGroup,false);
            MyHolder mh=new MyHolder(v);
            return mh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
            Task ts=mTaskArrayList.get(i);
            myHolder.bind(ts);




        }

        @Override
        public int getItemCount() {

            return mTaskArrayList.size();


        }
    }

}

