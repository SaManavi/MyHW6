package com.example.admin.myhw6.Model;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.myhw6.ORM.App;
import com.example.admin.myhw6.ORM.MyDevOpenHelper;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class UserRepository {



    private static UserRepository instance;

    private List<User> mUserList;
//    private UUID  correctUserId;
//    private SQLiteDatabase mDatabase;
    private Context mContext;

    private Long userId;
    private   DaoSession daoSession= App.getApp().getDaoSession();
    private   UserDao userDao =daoSession.getUserDao();




    private UserRepository(Context c) {
        mContext=c.getApplicationContext();

        MyDevOpenHelper myDevOpenHelper=new MyDevOpenHelper(mContext,"ToDoListDataBase");
        Database mDatabase  =myDevOpenHelper.getWritableDb();



//        mDatabase=new ToDoListBaseHelper(mContext).getWritableDatabase();


    }


    public static UserRepository getInstance(Context c) {
        if (instance == null)
            instance = new UserRepository(c);

        return instance;
    }


    public void addUser(User u) {
        userDao.insert(u);
        Toast.makeText(mContext, "user added in orm", Toast.LENGTH_SHORT).show();


//        mDatabase.insert(ToDoListSchema.UserTable.NAME,null,getContentValues(u));
//        mDatabase.insertWithOnConflict(ToDoListSchema.UserTable.NAME,null,getContentValues(u),SQLiteDatabase.CONFLICT_REPLACE);

    }


//    public ContentValues getContentValues(User u){
//        ContentValues myValue=new ContentValues();
//        myValue.put(ToDoListSchema.UserTable.Colmns.USER_ID,u.getId().toString());
//        myValue.put(ToDoListSchema.UserTable.Colmns.USERNAME,u.getUserName());
//        myValue.put(ToDoListSchema.UserTable.Colmns.USERPASS,u.getPassword());
//        myValue.put(ToDoListSchema.UserTable.Colmns.USEREMAIL,u.getEmail());
//
//        return myValue;
//
//    }


    public User getUserById(Long userId) {
       User user=userDao.load(userId);
       return user;






//        String whereClause=ToDoListSchema.UserTable.Colmns.USER_ID+ " = ?"  ;
//        String[] whereArgs =new String[]{id.toString()};
//
//        Cursor myCursor= mDatabase.query(ToDoListSchema.UserTable.NAME,
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
//            UUID user_Id=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USER_ID)));
//            String user_Name=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERNAME));
//            String user_Password=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERPASS));
//            String user_Email=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USEREMAIL));
//
//
//            User user=new User(user_Id,user_Name,user_Password,user_Email);
//
//
//            return user;
//
//        }
//
//        finally {
//            myCursor.close();
//        }
    }


    public List<User> getUserByName(String name) {

        List<User> usersList = userDao.queryBuilder()
                .where(UserDao.Properties.UserName.eq(name))
                .list();

        return usersList;



//
//        String whereClause=ToDoListSchema.UserTable.Colmns.USERNAME+ " = ?"  ;
//        String[] whereArgs =new String[]{name};
//
//        Cursor myCursor= mDatabase.query(ToDoListSchema.UserTable.NAME,
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
//            UUID user_Id=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USER_ID)));
//            String user_Name=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERNAME));
//            String user_Password=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERPASS));
//            String user_Email=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USEREMAIL));
//
//
//            User user=new User(user_Id,user_Name,user_Password,user_Email);
//
//
//            return user;
//
//        }
//
//        finally {
//            myCursor.close();
//        }
    }




    public Long userExistId(String name,String password) {

        List<User> userList = userDao.queryBuilder()
                .where(UserDao.Properties.UserName.eq(name))
                .where(UserDao.Properties.Password.eq(password))
                .list();

        if (userList.size() == 1)
            return userList.get(0).getUserId();
        else return null;

    }

    public boolean nameIsUniq(String newUserName) {
        boolean nameIsUniq=true;
        List<User> userList = userDao.queryBuilder()
                .where(UserDao.Properties.UserName.eq(newUserName))
                .list();

        if(userList.size()!=0) nameIsUniq=false;

        return nameIsUniq;

    }




        /*
        String whereClause=ToDoListSchema.UserTable.Colmns.USERNAME+ " = ?"  ;
        String[] whereArgs =new String[]{name};

        Cursor myCursor= mDatabase.query(ToDoListSchema.UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        try {
            if(myCursor.getCount()==0) {
                Toast.makeText(mContext, "*Please First Create Your User*", Toast.LENGTH_SHORT).show();
                return null;
            }

            myCursor.moveToFirst();

            while (!myCursor.isAfterLast()){

                UUID user_Id=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USER_ID)));
                String user_Name=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERNAME));
                String user_Password=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERPASS));
                String user_Email=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USEREMAIL));


                User user=new User(user_Id,user_Name,user_Password,user_Email);

                if(user.getPassword().equals(password)){
                    correctUserId=user.getId();
                }

                else {myCursor.moveToNext();}
            }
        }
        finally {
            myCursor.close();
        }

        if(correctUserId==null)
            Toast.makeText(mContext, "**Please Check Your Password**", Toast.LENGTH_SHORT).show();


        return correctUserId;

    }
*/


}
