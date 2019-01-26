package com.example.admin.myhw6;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskRepository;

import java.util.List;

import static com.example.admin.myhw6.AllTasksFragment.ARG_USER_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private int mCurrentPosition;
    private TextView mEmptyList;

    private static final String ARG_SEARCH_STRING = "search string";
    private Long mCurUsId;
    private String mSearchString;

    public SearchResultFragment() {
// Required empty public constructor
    }


    public static SearchResultFragment newInstance(Long userId, String searchString) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);
        args.putString(ARG_SEARCH_STRING, searchString);


        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSearchString = getArguments().getString(ARG_SEARCH_STRING);
        Long userId = (Long) getArguments().getSerializable(ARG_USER_ID);
        mCurUsId = userId;


    }



    public int AttachingAdapter() {
        TaskRepository instance = TaskRepository.getInstance(getActivity());
        List<Task> tl = instance.getSearchResultList(mCurUsId, mSearchString);
        MyAdapter mAdapt = new MyAdapter(tl);
        mRecyclerView.setAdapter(mAdapt);
        mAdapt.notifyItemChanged(mCurrentPosition);
        return tl.size();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.seaerch_result_layout, container, false);

        mRecyclerView = v.findViewById(R.id.search_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mEmptyList = v.findViewById(R.id.recycler_empty);


        AttachingAdapter();

        return v;

    }


    private class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTaskTitleOfHolder;
        private Button mTaskIconOfHolder;
        private Task mCurrentTask;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mTaskTitleOfHolder = itemView.findViewById(R.id.task_title_of_viewholder);
            mTaskIconOfHolder = itemView.findViewById(R.id.icon_of_viewholder);
            mCurrentPosition = getAdapterPosition();


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
            mCurrentTask = ts;
            mTaskTitleOfHolder.setText(ts.getTitle());

            if (!mCurrentTask.getTitle().isEmpty())
                mTaskIconOfHolder.setText(mCurrentTask.getTitle().substring(0, 1));
            else mTaskIconOfHolder.setText("*");


        }
    }


    protected class MyAdapter extends RecyclerView.Adapter<SearchResultFragment.MyHolder> {

        private List<Task> mTaskArrayList;

        public MyAdapter(List<Task> myTaskList) {
            mTaskArrayList = myTaskList;
        }

        @NonNull
        @Override
        public SearchResultFragment.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater myInf = LayoutInflater.from(getActivity());
            View v = myInf.inflate(R.layout.list_item_view, viewGroup, false);
            SearchResultFragment.MyHolder mh = new SearchResultFragment.MyHolder(v);
            return mh;
        }

        @Override
        public void onBindViewHolder(@NonNull SearchResultFragment.MyHolder myHolder, int i) {
            Task ts = mTaskArrayList.get(i);
            myHolder.bind(ts);


        }

        @Override
        public int getItemCount() {

            return mTaskArrayList.size();


        }

    }
}
