package com.example.admin.myhw6;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.myhw6.Model.User;
import com.example.admin.myhw6.Model.UserRepository;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailFragment extends Fragment {

private User mNewUser;
    private Button mCreateUser;
    private EditText mUserName;
    private EditText mUserPassword;
    private EditText mEmail;

    public UserDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user_detail, container, false);

        mUserName=v.findViewById(R.id.user_name);
        mUserPassword=v.findViewById(R.id.user_password);
        mEmail=v.findViewById(R.id.user_email);
        mCreateUser=v.findViewById(R.id.create_user);

        mCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewUser = new User();
                if (!UserRepository.getInstance(getActivity()).nameIsUniq(mUserName.getText().toString()))
                    Toast.makeText(getActivity(), "Please use another Username!", Toast.LENGTH_SHORT).show();

                else {
                    mNewUser.setUserName(mUserName.getText().toString());

                    mNewUser.setPassword(mUserPassword.getText().toString());
                    mNewUser.setEmail(mEmail.getText().toString());

                    UserRepository.getInstance(getActivity()).addUser(mNewUser);

                    Toast.makeText(getActivity(), "Your User Account has been created.", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "..."+mNewUser.getUserName()+"/"+mNewUser.getPassword(), Toast.LENGTH_SHORT).show();


                    Intent myIntent = MainActivity.newIntent(getActivity(), mNewUser.getUserId());
                    startActivity(myIntent);
                    getActivity().finish();
                }
            }
        });




return v;
    }

}
