package com.example.admin.myhw6.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class Task {

//    private UUID mTaskUUID;
//    private UUID mUserId;


    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mIsdone;

    @Id(autoincrement = true)
    private Long taskId;

    private Long userId;

    @ToOne(joinProperty = "userId")
    private User users;





    @Generated(hash = 676794248)
    public Task(String mTitle, String mDescription, Date mDate, boolean mIsdone,
            Long taskId) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mIsdone = mIsdone;
        this.taskId = taskId;
    }

    @Generated(hash = 733837707)
    public Task() {
    }

    public String getMTitle() {
        return this.mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMDescription() {
        return this.mDescription;
    }

    public void setMDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getMDate() {
        return this.mDate;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean getMIsdone() {
        return this.mIsdone;
    }

    public void setMIsdone(boolean mIsdone) {
        this.mIsdone = mIsdone;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }













//
//    public Task() {
//     this(UUID.randomUUID());
//
//
//    }
//
//    public Task(UUID id){
//     mTaskUUID=id;
//        mIsdone=false;
//        mDate= new Date();
//
//
//    }
//
//
//    public Task( String title, String description,boolean isdone, Date date, UUID userId) {
//        this();
//        mTitle = title;
//        mDescription = description;
//        mIsdone=isdone;
//        mDate = date;
//        mUserId=userId;
//    }
//
//    public UUID getTaskUUID() {
//        return mTaskUUID;
//    }
//
////    public int getTaskId() {
////        return mTaskId;
////    }
//
//    public String getTitle() {
//        return mTitle;
//    }
//
//    public void setTitle(String title) {
//        mTitle = title;
//    }
//
//    public String getDescription() {
//        return mDescription;
//    }
//
//    public void setDescription(String description) {
//        mDescription = description;
//    }
//
//    public Date getDate() {
//        return mDate;
//    }
//
//    public void setDate(Date date) {
//        mDate = date;
//    }
//
//    public boolean isIsdone() {
//        return mIsdone;
//    }
//
//    public void setIsdone(boolean isdone) {
//        mIsdone = isdone;
//    }
//
//    public UUID getUserId() {
//        return mUserId;
//    }
//
//    public void setUserId(UUID userId) {
//        mUserId = userId;
//    }
}
