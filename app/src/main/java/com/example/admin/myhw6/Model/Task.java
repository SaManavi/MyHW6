package com.example.admin.myhw6.Model;

import java.util.Date;
import java.util.UUID;

public class Task {

    private UUID mTaskUUID;
    //    private int mTaskId;
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mIsdone;


    public Task() {
        mTaskUUID = UUID.randomUUID();
        mIsdone=false;
        mDate= new Date();

    }

    public Task( String title, String description,boolean isdone) {
        this();
        mTitle = title;
        mDescription = description;
        mIsdone=isdone;
//        mDate = date;
    }

    public UUID getTaskUUID() {
        return mTaskUUID;
    }

//    public int getTaskId() {
//        return mTaskId;
//    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isIsdone() {
        return mIsdone;
    }

    public void setIsdone(boolean isdone) {
        mIsdone = isdone;
    }


}
