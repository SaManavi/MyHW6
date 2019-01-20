package com.example.admin.myhw6.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoListBaseHelper extends SQLiteOpenHelper {

    public ToDoListBaseHelper( Context context) {
        super(context, ToDoListSchema.NAME, null, ToDoListSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+ToDoListSchema.TaskTable.NAME+" ( "+
                " _id Integer primary key autoincrement ,  "+

                ToDoListSchema.TaskTable.Colns.UUID +" , "+
                ToDoListSchema.TaskTable.Colns.TITLE +" , "+
                ToDoListSchema.TaskTable.Colns.DESCRIPTION +" , "+
                ToDoListSchema.TaskTable.Colns.ISDONE +" , "+
                ToDoListSchema.TaskTable.Colns.DATE +" , "+
                ToDoListSchema.TaskTable.Colns.USER_ID +" , "+
                " FOREIGN KEY ( "+ToDoListSchema.TaskTable.Colns.USER_ID+" ) REFERENCES "+ToDoListSchema.UserTable.NAME+
                "("+ToDoListSchema.UserTable.Colmns.USER_ID+")"+


                " ) ;"
        );

        db.execSQL(" create table "+ToDoListSchema.UserTable.NAME+" ( "+
//autoincrement
                ToDoListSchema.UserTable.Colmns.USER_ID + " primary key  ,  "+
                ToDoListSchema.UserTable.Colmns.USERNAME + " , "+
                ToDoListSchema.UserTable.Colmns.USERPASS+" , "+
                ToDoListSchema.UserTable.Colmns.USEREMAIL +
                " ) ;"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
