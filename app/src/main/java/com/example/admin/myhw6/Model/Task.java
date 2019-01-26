package com.example.admin.myhw6.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;


@Entity
public class Task {

//    private UUID mTaskUUID;
//    private UUID mUserId;


    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mIsdone;

    @Id(autoincrement = true)
    private Long mTaskId;

    private Long mUserId;
    @ToOne(joinProperty = "mUserId")
    private User users;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;
    @Generated(hash = 73025128)
    private transient Long users__resolvedKey;


//
//@Keep
//    public Task(String mTitle, String mDescription, Date mDate, boolean mIsdone,
//                Long mUserId) {
//        this.mTitle = mTitle;
//        this.mDescription = mDescription;
//        this.mDate = mDate;
//        this.mIsdone = mIsdone;
//        this.mTaskId = mTaskId;
//        this.mUserId = mUserId;
//    }


    @Generated(hash = 1641614117)
    public Task(String mTitle, String mDescription, Date mDate, boolean mIsdone, Long mTaskId,
            Long mUserId) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mIsdone = mIsdone;
        this.mTaskId = mTaskId;
        this.mUserId = mUserId;
    }

    @Generated(hash = 733837707)
    public Task() {
    }

    public String getTitle() {
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
        return this.mTaskId;
    }

    public void setTaskId(Long taskId) {
        this.mTaskId = taskId;
    }

    public Long getUserId() {
        return this.mUserId;
    }

    public void setUserId(Long userId) {
        this.mUserId = userId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1358069503)
    public User getUsers() {
        Long __key = this.mUserId;
        if (users__resolvedKey == null || !users__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User usersNew = targetDao.load(__key);
            synchronized (this) {
                users = usersNew;
                users__resolvedKey = __key;
            }
        }
        return users;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 151719297)
    public void setUsers(User users) {
        synchronized (this) {
            this.users = users;
            mUserId = users == null ? null : users.getUserId();
            users__resolvedKey = mUserId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public String getMTitle() {
        return this.mTitle;
    }

    public String getPhotoName() {
        return "IMG_" + mTaskId.toString() + ".jpg";
    }

    public Long getMTaskId() {
        return this.mTaskId;
    }

    public void setMTaskId(Long mTaskId) {
        this.mTaskId = mTaskId;
    }

    public Long getMUserId() {
        return this.mUserId;
    }

    public void setMUserId(Long mUserId) {
        this.mUserId = mUserId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }
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
//    public Task( String title, String description,boolean isdone, Date date, UUID mUserId) {
//        this();
//        mTitle = title;
//        mDescription = description;
//        mIsdone=isdone;
//        mDate = date;
//        mUserId=mUserId;
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
//    public void setUserId(UUID mUserId) {
//        mUserId = mUserId;
//    }

