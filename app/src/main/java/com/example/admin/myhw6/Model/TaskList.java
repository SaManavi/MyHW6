package com.example.admin.myhw6.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskList {
    private static TaskList instance;

    private List<Task> mTaskLists;
    private Task mTask;


    private TaskList() {
        mTaskLists=new ArrayList<>();
        for (int i = 0; i <7 ; i++) {
            mTask=new Task();
            mTask.setTitle("title is"+i);
            mTask.setDescription("description is ....");
            mTaskLists.add(mTask);

        }

    }


    public static TaskList getInstance(){
        if(instance==null)
            instance=new TaskList();

        return instance;
    }

    public List<Task> getTasksList(){

        return mTaskLists;
    }

    public Task getTaskById(UUID id){
        for (Task t:mTaskLists) {
            if (t.getTaskUUID().equals(id))
                return t;
        }

        return null;
    }

    public void addTask(Task t){
        mTaskLists.add(t);
    }

//    public void removeTask (Task t){
//        mTaskLists.r
//    }

//public List<Task> getListOfTask(){
//
//}





}
