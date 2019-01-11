package com.example.admin.myhw6.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.myhw6.database.ToDoListBaseHelper;
import com.example.admin.myhw6.database.ToDoListSchema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskList {
    private static TaskList instance;

    private List<Task> mTaskLists;
    private Task mTask;
    private SQLiteDatabase mDatabase;
    private Context mContext;



    private TaskList(Context c) {
        mContext=c.getApplicationContext();
        mDatabase=new ToDoListBaseHelper(mContext).getWritableDatabase();


    }


    public static TaskList getInstance(Context c) {
        if (instance == null)
            instance = new TaskList(c);

        return instance;
    }

    public List<Task> getTasksList() {
        mTaskLists=new ArrayList<>();
        Cursor myCursor= mDatabase.query(
                ToDoListSchema.TaskTable.NAME ,
                null ,
                null ,
                null ,
                null ,
                null ,
                null );

        try {
            if(myCursor.getCount()==0)
                return mTaskLists;

            myCursor.moveToFirst();

            while (!myCursor.isAfterLast()){
                UUID uuid=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.UUID)));
                String title=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.TITLE));
                String des=myCursor.getColumnName(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.DESCRIPTION));
                Date date=new Date(myCursor.getLong(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.DATE)));
                boolean isDone=myCursor.getInt(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.ISDONE))==0?false:true;

                Task task=new Task(uuid);
                task.setTitle(title);
                task.setDescription(des);
                task.setDate(date);
                task.setIsdone(isDone);

                mTaskLists.add(task);

                myCursor.moveToNext();
            }
        }

        finally {
            myCursor.close();
        }

        return mTaskLists;

    }

    public List<Task> getDoneTasksList() {
        List<Task> doneTaskLists = new ArrayList<>();

        for (Task dt : mTaskLists) {
            if (dt.isIsdone()) {
                doneTaskLists.add(dt);
            }
        }

        return doneTaskLists;
//        return new ArrayList();

    }


    public List<Task> getUnDoneTasksList() {
        List<Task> unDoneTaskLists = new ArrayList<>();

        for (Task dt : mTaskLists) {
            if (!dt.isIsdone()) {
                unDoneTaskLists.add(dt);
            }
        }

        return unDoneTaskLists;
//        return new ArrayList();
    }

    public Task getTaskById(UUID id) {
        String whereClause=ToDoListSchema.TaskTable.Colmns.UUID+ "= ?"  ;
        String[] whereArgs =new String[]{id.toString()};

        Cursor myCursor= mDatabase.query(ToDoListSchema.TaskTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        try {
            if(myCursor.getCount()==0)
                return null;

            myCursor.moveToFirst();
            UUID uuid=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.UUID)));
            String title=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.TITLE));
            String des=myCursor.getColumnName(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.DESCRIPTION));
            Date date=new Date(myCursor.getLong(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.DATE)));
            boolean isDone=myCursor.getInt(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.ISDONE))==0?false:true;

            Task task=new Task(uuid);
            task.setTitle(title);
            task.setDescription(des);
            task.setDate(date);
            task.setIsdone(isDone);

            return task;

        }

        finally {
            myCursor.close();
        }
//        return null;
    }

    public void addTask(Task t) {
        mDatabase.insert(ToDoListSchema.TaskTable.NAME,null,getContentValues(t));

    }

    public void removeTask(UUID id) {

    }

//public List<Task> getListOfTask(){
//
//}

    public ContentValues getContentValues(Task t){
        ContentValues myValue=new ContentValues();
        myValue.put(ToDoListSchema.TaskTable.Colmns.UUID,t.getTaskUUID().toString());
        myValue.put(ToDoListSchema.TaskTable.Colmns.TITLE, t.getTitle());
        myValue.put(ToDoListSchema.TaskTable.Colmns.DESCRIPTION,t.getDescription());
        myValue.put(ToDoListSchema.TaskTable.Colmns.DATE,t.getDate().getTime());
        myValue.put(ToDoListSchema.TaskTable.Colmns.ISDONE,t.isIsdone() ? 1:0);

        return myValue;

    }

    public void update(Task t){
        String whereClause=ToDoListSchema.TaskTable.Colmns.UUID +" = ?";
        mDatabase.update(ToDoListSchema.TaskTable.NAME,
                getContentValues(t),whereClause,
                new String[] {t.getTaskUUID().toString()});

    }


}
