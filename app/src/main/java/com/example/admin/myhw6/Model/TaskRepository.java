package com.example.admin.myhw6.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.myhw6.ORM.App;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private static TaskRepository instance;
    private List<Task> mTaskLists;
    private List<Task> mSearchResultList;

    private Task mTask;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    private DaoSession daoSession= App.getApp().getDaoSession();
    private TaskDao taskDao=daoSession.getTaskDao();


    private TaskRepository(Context c) {

mContext=c;


//        mDatabase=new ToDoListBaseHelper(mContext).getWritableDatabase();
//
//
    }
//
//
    public static TaskRepository getInstance(Context c) {
        if (instance == null)
            instance = new TaskRepository(c);






//            instance = new TaskRepository(c);

        return instance;
    }




    public List<Task> getTasksList(Long userId) {


        mTaskLists = taskDao.queryBuilder()
                .where(TaskDao.Properties.MUserId.eq(userId))
                .list();
        return mTaskLists;








//        mTaskLists=new ArrayList<>();
//        String whereClause= ToDoListSchema.TaskTable.Colns.USER_ID +" = ? "  ;
//        String[] whereArgs =new String[]{userId.toString()};
//
//        Cursor myCursor= mDatabase.query(
//                ToDoListSchema.TaskTable.NAME ,
//                null ,
//           whereClause ,
//            whereArgs ,
//                null ,
//                null ,
//                null );
//
//        try {
//            if(myCursor.getCount()==0)
//                return mTaskLists;
//
//            myCursor.moveToFirst();
//
//            while (!myCursor.isAfterLast()){
//                UUID uuid=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.UUID)));
//                String title=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.TITLE));
//                String des=myCursor.getColumnName(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.DESCRIPTION));
//                Date date=new Date(myCursor.getLong(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.DATE)));
//                boolean isDone=myCursor.getInt(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.ISDONE))==0?false:true;
//                UUID user_Id=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.USER_ID)));
//
//                Task task=new Task(uuid);
//                task.setTitle(title);
//                task.setDescription(des);
//                task.setDate(date);
//                task.setIsdone(isDone);
//                task.setUserId(user_Id);
//
//                mTaskLists.add(task);
//
//                myCursor.moveToNext();
//            }
//        }
//
//        finally {
//            myCursor.close();
//        }


    }

//    public List<Task> getTasksList() {
//        mTaskLists=new ArrayList<>();
//        Cursor myCursor= mDatabase.query(
//                ToDoListSchema.TaskTable.NAME ,
//                null ,
//                null ,
//                null ,
//                null ,
//                null ,
//                null );
//
//        try {
//            if(myCursor.getCount()==0)
//                return mTaskLists;
//
//            myCursor.moveToFirst();
//
//            while (!myCursor.isAfterLast()){
//                UUID uuid=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.UUID)));
//                String title=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.TITLE));
//                String des=myCursor.getColumnName(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.DESCRIPTION));
//                Date date=new Date(myCursor.getLong(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.DATE)));
//                boolean isDone=myCursor.getInt(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colmns.ISDONE))==0?false:true;
//
//                Task task=new Task(uuid);
//                task.setTitle(title);
//                task.setDescription(des);
//                task.setDate(date);
//                task.setIsdone(isDone);
//
//                mTaskLists.add(task);
//
//                myCursor.moveToNext();
//            }
//        }
//
//        finally {
//            myCursor.close();
//        }
//
//        return mTaskLists;
//
//    }




    public List<Task> getDoneTasksList(Long userId) {
        List<Task> TaskListsOfDB =getTasksList(userId) ;
        List<Task> doneTaskLists = new ArrayList<>();

        for (Task t : TaskListsOfDB) {
            if (t.getMIsdone()) {
                doneTaskLists.add(t);
            }
        }

        return doneTaskLists;
//        return new ArrayList();

    }


    public List<Task> getUnDoneTasksList(Long userId) {
        List<Task> TaskListsOfDB =getTasksList(userId) ;
        List<Task> unDoneTaskLists = new ArrayList<>();

        for (Task t : TaskListsOfDB) {
            if (!t.getMIsdone()) {
                unDoneTaskLists.add(t);
            }
        }

        return unDoneTaskLists;
//        return new ArrayList();
    }


    public Task getTaskById(Long taskId) {

        Task mTask=taskDao.load(taskId);
        return mTask;




//        String whereClause=ToDoListSchema.TaskTable.Colns.UUID+ "= ?"  ;
//        String[] whereArgs =new String[]{id.toString()};
//
//        Cursor myCursor= mDatabase.query(ToDoListSchema.TaskTable.NAME,
//                null,
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null);
//
//        try {
//            if(myCursor.getCount()==0)
//                return null;
//
//            myCursor.moveToFirst();
//            UUID uuid=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.UUID)));
//            String title=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.TITLE));
//            String des=myCursor.getColumnName(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.DESCRIPTION));
//            Date date=new Date(myCursor.getLong(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.DATE)));
//            boolean isDone=myCursor.getInt(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.ISDONE))==0?false:true;
//            UUID userId=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.USER_ID)));
//
//
//            Task task=new Task(uuid);
//            task.setTitle(title);
//            task.setDescription(des);
//            task.setDate(date);
//            task.setIsdone(isDone);
//            task.setUserId(userId);
//
//            return task;
//
//        }
//
//        finally {
//            myCursor.close();
//        }
////        return null;
    }

    public void addTask(Task t) {

        taskDao.insert(t);



//        mDatabase.insert(ToDoListSchema.TaskTable.NAME,null,getContentValues(t));

    }

    public void removeTask(Task t) {
        taskDao.delete(t);



//
//        String whereClause=ToDoListSchema.TaskTable.Colns.USER_ID+ "= ?"  ;
//        String[] whereArgs =new String[]{userId.toString()};
//
//        mDatabase.delete(ToDoListSchema.TaskTable.NAME,
//                whereClause,
//                whereArgs);

    }

    public void delTasks(Long userId) {
        List<Task> list = taskDao.queryBuilder()
                .where(TaskDao.Properties.MUserId.eq(userId))
                .list();
        taskDao.deleteInTx(list);


    }

//public List<Task> getListOfTask(){
//
//}

//    public ContentValues getContentValues(Task t){
//        ContentValues myValue=new ContentValues();
//        myValue.put(ToDoListSchema.TaskTable.Colns.USER_ID,t.getUserId().toString());
//        myValue.put(ToDoListSchema.TaskTable.Colns.UUID,t.getTaskUUID().toString());
//        myValue.put(ToDoListSchema.TaskTable.Colns.TITLE, t.getTitle());
//        myValue.put(ToDoListSchema.TaskTable.Colns.DESCRIPTION,t.getDescription());
//        myValue.put(ToDoListSchema.TaskTable.Colns.DATE,t.getDate().getTime());
//        myValue.put(ToDoListSchema.TaskTable.Colns.ISDONE,t.isIsdone() ? 1:0);
//
//        return myValue;
//
//    }

    public void update(Task t) {

        taskDao.update(t);



//        String whereClause=ToDoListSchema.TaskTable.Colns.UUID +" = ?";
//        mDatabase.update(ToDoListSchema.TaskTable.NAME,
//                getContentValues(t),whereClause,
//                new String[] {t.getTaskUUID().toString()});

    }


//    public Task getTaskListOfUserWithPass(String user,String pass) {
//        String whereClause=ToDoListSchema.TaskTable.Colns.USER_ID + "= ? AND "+ToDoListSchema.TaskTable.Colns.USERPASS+ " = ?"  ;
//        String[] whereArgs =new String[]{user,pass};
//
//        Cursor myCursor= mDatabase.query(ToDoListSchema.TaskTable.NAME,
//                null,
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null);
//
//        try {
//            if(myCursor.getCount()==0)
//                return null;
//
//            myCursor.moveToFirst();
//
//
//            UUID uuid=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.UUID)));
//            String title=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.TITLE));
//            String des=myCursor.getColumnName(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.DESCRIPTION));
//            Date date=new Date(myCursor.getLong(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.DATE)));
//            boolean isDone=myCursor.getInt(myCursor.getColumnIndex(ToDoListSchema.TaskTable.Colns.ISDONE))==0?false:true;
//
//            Task task=new Task(uuid);
//            task.setTitle(title);
//            task.setDescription(des);
//            task.setDate(date);
//            task.setIsdone(isDone);
//
//            return task;
//
//        }
//
//        finally {
//            myCursor.close();
//        }
////        return null;
//    }

    public File getPhotoFile(Task t) {
        File filesDir = mContext.getFilesDir();
        File photoFile = new File(filesDir, t.getPhotoName());

        return photoFile;
    }


    public List<Task> getSearchResultList(Long userId,String searchString) {


        QueryBuilder<Task> qb = taskDao.queryBuilder();
              qb.where(TaskDao.Properties.MUserId.eq(userId),
                qb.or(TaskDao.Properties.MTitle.like("%" + searchString + "%"),
                TaskDao.Properties.MDescription.like("%" + searchString + "%")));

        mSearchResultList = qb.list();

        return mSearchResultList;
    }
}
