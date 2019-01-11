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
                ToDoListSchema.TaskTable.Colmns.USER+" , "+
                ToDoListSchema.TaskTable.Colmns.USERPASS +" , "+
                ToDoListSchema.TaskTable.Colmns.UUID +" , "+
                ToDoListSchema.TaskTable.Colmns.TITLE +" , "+
                ToDoListSchema.TaskTable.Colmns.DESCRIPTION +" , "+
                ToDoListSchema.TaskTable.Colmns.ISDONE +" , "+
                ToDoListSchema.TaskTable.Colmns.DATE +

                " ) ;"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
