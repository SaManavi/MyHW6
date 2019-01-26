package com.example.admin.myhw6;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myhw6.Model.Task;
import com.example.admin.myhw6.Model.TaskRepository;
import com.example.admin.myhw6.Utils.PictureUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogOfTaskEditingFragment extends DialogFragment {


    public static final String TASK_ID = "com.example.admin.myhw6.Task uuid as Id";
    private static final String DIALOG_TAG = "DialogDate";
    private static final int REQ_DATE_PICKER = 0;
    private static final int REQ_TIME_PICKER = 1;


    private ImageView mPhoto;
    private ImageButton mCapture;
    private File mPhotoFile;
    private Task mCurrentTask;
    private TextView mTaskTitle;
    private TextView mTaskDes;
    private Button mEdit;
    private Button mDel;
    private Button mShare;
    private CheckBox mIsDoneCheckBox;
    private Button mTaskDate;
    private Button mTaskTime;
    private Button mConfirm;
    private int REQ_PHOTOS = 2;

    public DialogOfTaskEditingFragment() {
        // Required empty public constructor
    }

    public static DialogOfTaskEditingFragment newInstance(Long TaskId) {

        DialogOfTaskEditingFragment myFrag = new DialogOfTaskEditingFragment();
        Bundle args = new Bundle();
        args.putSerializable(TASK_ID, TaskId);
        myFrag.setArguments(args);
        return myFrag;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Long mCurrentTaskId = (Long) getArguments().getSerializable(TASK_ID);
        mCurrentTask = TaskRepository.getInstance(getActivity()).getTaskById(mCurrentTaskId);
        mPhotoFile = TaskRepository.getInstance(getActivity()).getPhotoFile(mCurrentTask);
        //        View v=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_task_detail,null);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_detail, container);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        mCapture = v.findViewById(R.id.task_detail_capture_button);
        mPhoto = v.findViewById(R.id.task_detail_edit_photo);
        mTaskTitle = v.findViewById(R.id.task_detail_title);
        mTaskDes = v.findViewById(R.id.task_detail_des);
        mShare = v.findViewById(R.id.task_detail_share_butt);
        mIsDoneCheckBox = v.findViewById(R.id.task_detail_isDone);
        mTaskDate = v.findViewById(R.id.date_of_task_button);
        mTaskTime = v.findViewById(R.id.time_of_task_button);
        mDel = v.findViewById(R.id.task_detail_del_butt);
        mEdit = v.findViewById(R.id.task_detail_edit_butt);
        mConfirm = v.findViewById(R.id.task_detail_confirm);
        updatePhotoView();


        mConfirm.setVisibility(View.INVISIBLE);
        mIsDoneCheckBox.setChecked(mCurrentTask.getMIsdone());
        mTaskTitle.setText(mCurrentTask.getTitle());
        mTaskDes.setText(mCurrentTask.getMDescription());


        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        SimpleDateFormat mTimeFormat = new SimpleDateFormat(" hh:mm a ");
        Date GetDate = mCurrentTask.getMDate();
        String DateStr = mDateFormat.format(GetDate);
        String TimeStr = mTimeFormat.format(GetDate);
        mTaskDate.setText("Date Of Task:   " + DateStr);
        mTaskTime.setText("Time Of Task:   " + TimeStr);


        mTaskTitle.setEnabled(false);
        mTaskDes.setEnabled(false);
        mIsDoneCheckBox.setVisibility(View.INVISIBLE);
        mTaskDate.setEnabled(false);
        mTaskTime.setEnabled(false);

        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "mphoto exist", Toast.LENGTH_SHORT).show();
            }
        });
        mCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri uri = getPhotoFileUri();
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(captureIntent, PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo activity : activities) {
                    getActivity().grantUriPermission(activity.activityInfo.packageName, uri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivityForResult(captureIntent, REQ_PHOTOS);

            }
        });


        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent reportIntent = new Intent(Intent.ACTION_SEND);
                reportIntent.setType("text/plain");
                reportIntent.putExtra(Intent.EXTRA_TEXT, getTaskReport());
                startActivity(Intent.createChooser(reportIntent, getTaskReport()));


            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTaskTitle.setEnabled(true);
                mTaskDes.setEnabled(true);
                mIsDoneCheckBox.setVisibility(View.VISIBLE);
                mTaskDate.setEnabled(true);
                mTaskTime.setEnabled(true);
                mConfirm.setVisibility(View.VISIBLE);


            }
        });

        mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskRepository.getInstance(getActivity()).removeTask(mCurrentTask);//???
                Toast.makeText(getActivity(), "Your Task has been deleted.", Toast.LENGTH_SHORT).show();

                Intent myIntent = MainActivity.newIntent(getActivity(), mCurrentTask.getUserId());
                startActivity(myIntent);
//              getActivity().finish();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTaskTitle.setEnabled(false);
                mTaskDes.setEnabled(false);
                mIsDoneCheckBox.setVisibility(View.INVISIBLE);
                mTaskDate.setEnabled(false);
                mTaskTime.setEnabled(false);
                mConfirm.setEnabled(false);
                Toast.makeText(getActivity(), "edited..." + mCurrentTask.getTitle(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Your task has been edited.", Toast.LENGTH_SHORT).show();


                Intent myIntent = MainActivity.newIntent(getActivity(), mCurrentTask.getUserId());
                startActivity(myIntent);
                getActivity().finish();

            }
        });


        mTaskTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCurrentTask.setMTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mTaskDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCurrentTask.setMDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mIsDoneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCurrentTask.setMIsdone(isChecked);
                mShare.setEnabled(!isChecked);
            }
        });

        mTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCurrentTask.getMDate());
                datePickerFragment.setTargetFragment(DialogOfTaskEditingFragment.this,
                        REQ_DATE_PICKER);
                datePickerFragment.show(getFragmentManager(), DIALOG_TAG);
            }
        });


        mTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickFrag = TimePickerFragment.newInstance(mCurrentTask.getMDate());
                timePickFrag.setTargetFragment(DialogOfTaskEditingFragment.this,
                        REQ_TIME_PICKER);
                timePickFrag.show(getFragmentManager(), DIALOG_TAG);

            }
        });


    }

    public String getTaskReport() {


        String report = "Your Task: " + mCurrentTask.getTitle() + " \n Description: " + mCurrentTask.getMDescription()
                + " \nis set for "
                + mCurrentTask.getMDate() +
                " to do. \nYour task is";


        return report;


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK)
            return;

        if (requestCode == REQ_DATE_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCurrentTask.setMDate(date);

            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd ");

            Date GetDate = mCurrentTask.getMDate();
            String DateStr = mDateFormat.format(GetDate);

            mTaskDate.setText(DateStr);

        }


        if (requestCode == REQ_TIME_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCurrentTask.setMDate(date);


            SimpleDateFormat mTimeFormat = new SimpleDateFormat(" hh:mm a ");
            Date GetDate = mCurrentTask.getMDate();

            String TimeStr = mTimeFormat.format(GetDate);

            mTaskTime.setText(TimeStr);
        }

        if (requestCode == REQ_PHOTOS) {

            Uri uri = getPhotoFileUri();
            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updatePhotoView();

        }

    }


    @Override
    public void onPause() {
        super.onPause();

        TaskRepository.getInstance(getActivity()).update(mCurrentTask);

    }


    private Uri getPhotoFileUri() {
        return FileProvider.getUriForFile(getActivity(),
                "com.example.admin.myhw6.fileprovider", mPhotoFile);
    }


    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
//            mPhoto.setImageDrawable(null);
            return;
        } else {

            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(),
                    getActivity());
            mPhoto.setImageBitmap(bitmap);

        }
    }


}
