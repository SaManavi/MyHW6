package com.example.admin.myhw6.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.UUID;
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
    private Long taskId;

    private Long userId;

    @ToOne(joinProperty = "userId")
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
//                Long userId) {
//        this.mTitle = mTitle;
//        this.mDescription = mDescription;
//        this.mDate = mDate;
//        this.mIsdone = mIsdone;
//        this.taskId = taskId;
//        this.userId = userId;
//    }


    @Generated(hash = 101736849)
    public Task(String mTitle, String mDescription, Date mDate, boolean mIsdone, Long taskId,
            Long userId) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mIsdone = mIsdone;
        this.taskId = taskId;
        this.userId = userId;
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
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1608763732)
    public User getUsers() {
        Long __key = this.userId;
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
    @Generated(hash = 1178717799)
    public void setUsers(User users) {
        synchronized (this) {
            this.users = users;
            userId = users == null ? null : users.getUserId();
            users__resolvedKey = userId;
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
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
