package com.example.admin.myhw6.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.myhw6.database.ToDoListBaseHelper;
import com.example.admin.myhw6.database.ToDoListSchema;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;


@Entity
public class User {

//    private UUID id;


@Unique
    private String userName;
    private String email;
    private String password;

    @Id(autoincrement = true)
    private Long userId;

    @Generated(hash = 546201557)
    public User(String userName, String email, String password, Long userId) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }












//    public User(String userName, String password, String email) {
//            this(UUID.randomUUID(),userName,password,email);
//
//        }
//
//
//    public User(UUID userId,String userName, String password, String email) {
//        this.id = userId;
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//    }
//
//
//
//
//
//
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}

