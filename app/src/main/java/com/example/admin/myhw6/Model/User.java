package com.example.admin.myhw6.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.myhw6.database.ToDoListBaseHelper;
import com.example.admin.myhw6.database.ToDoListSchema;

import java.util.UUID;

public class User {


    private UUID id;
    private String userName;
    private String email;
    private String password;



    public User(String userName, String password, String email) {
            this(UUID.randomUUID(),userName,password,email);

        }


    public User(UUID userId,String userName, String password, String email) {
        this.id = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }







    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

