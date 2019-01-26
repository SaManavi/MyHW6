package com.example.admin.myhw6;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskRepository;
import com.example.admin.myhw6.Model.User;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogOfSearchFragment extends DialogFragment {
    public static final String ARG_USER_ID = "ARG_USER_ID";
    private static final String DIALOG_TAG = "DialogSearch";
    public static final String TASK_ID = "com.example.admin.myhw6.Task uuid as Id";


    private User mcurrentUser;
    private Long mCurUsId;

    private Task mCurrentTask;
    private Button mSearchBtn;
    private EditText mSearchTextView;


    public DialogOfSearchFragment() {
// Required empty public constructor
    }


    public static DialogOfSearchFragment newInstance(Long UserId) {

        DialogOfSearchFragment myFrag = new DialogOfSearchFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, UserId);
        myFrag.setArguments(args);
        return myFrag;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCurUsId = (Long) getArguments().getSerializable(ARG_USER_ID);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.dialog_search, container);

        return v;

    }


    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);


        mSearchBtn = v.findViewById(R.id.dialog_search_btn);
        mSearchTextView = v.findViewById(R.id.dialog_search_text);


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = SearchActivity.newIntent(getActivity(), mCurUsId,mSearchTextView.getText().toString());
                startActivity(myIntent);
//                getActivity().finish();


            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle(R.string.dialog);


//        return super.onCreateDialog(savedInstanceState);
        return  dialog;
    }
}









