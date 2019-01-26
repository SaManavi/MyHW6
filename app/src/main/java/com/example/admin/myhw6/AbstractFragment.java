package com.example.admin.myhw6;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.User;

import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AbstractFragment extends Fragment {

    private static final String DIALOG_TAG ="Task Detail Dialog" ;

    protected RecyclerView mRecyclerView;
    // private Adapter mAdapter;
    protected int mCurrentPosition;
    protected TextView mEmptyList;
    protected User mCurrentUser;


    public AbstractFragment() {
// Required empty public constructor
    }
    abstract Long getCurentUserId();

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

                Intent myInt=AddingTaskActivity.newIntent(getActivity(),getCurentUserId());
                startActivity(myInt);
//                getActivity().finish();
            }
        });


        AttachingAdapter();

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
// you don't have any item..
        int listSize=AttachingAdapter();
        if (listSize==0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyList.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyList.setVisibility(View.GONE);
        }


    }



    protected class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTaskTitleOfHolder;
        private Button mTaskIconOfHolder;
        private Task mCurrentTask;



        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mTaskTitleOfHolder=itemView.findViewById(R.id.task_title_of_viewholder);
            mTaskIconOfHolder=itemView.findViewById(R.id.icon_of_viewholder);
            mCurrentPosition=getAdapterPosition();


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    FragmentManager myFm = getFragmentManager();

                    DialogOfTaskDetailFragment myDiFragment = DialogOfTaskDetailFragment.newInstance(mCurrentTask.getTaskId());
                    myDiFragment.show(myFm, "dialog");

                }
            });


        }


        public void bind(Task ts) {
            mCurrentTask =ts;
            mTaskTitleOfHolder.setText(ts.getTitle());

            if(!mCurrentTask.getTitle().isEmpty())   mTaskIconOfHolder.setText(mCurrentTask.getTitle().substring(0,1));
            else  mTaskIconOfHolder.setText("*");


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


    public abstract int AttachingAdapter();

}

