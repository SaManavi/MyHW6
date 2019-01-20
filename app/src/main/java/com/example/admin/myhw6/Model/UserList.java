package com.example.admin.myhw6.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.admin.myhw6.database.ToDoListBaseHelper;
import com.example.admin.myhw6.database.ToDoListSchema;

import java.util.List;
import java.util.UUID;

public class UserList {



    private static UserList instance;

    private List<User> mUserList;
    private UUID  correctUserId;
    private SQLiteDatabase mDatabase;
    private Context mContext;





    private UserList(Context c) {
        mContext=c.getApplicationContext();
        mDatabase=new ToDoListBaseHelper(mContext).getWritableDatabase();


    }


    public static UserList getInstance(Context c) {
        if (instance == null)
            instance = new UserList(c);

        return instance;
    }


    public void addUser(User u) {
        mDatabase.insert(ToDoListSchema.UserTable.NAME,null,getContentValues(u));
//        mDatabase.insertWithOnConflict(ToDoListSchema.UserTable.NAME,null,getContentValues(u),SQLiteDatabase.CONFLICT_REPLACE);

    }


    public ContentValues getContentValues(User u){
        ContentValues myValue=new ContentValues();
        myValue.put(ToDoListSchema.UserTable.Colmns.USER_ID,u.getId().toString());
        myValue.put(ToDoListSchema.UserTable.Colmns.USERNAME,u.getUserName());
        myValue.put(ToDoListSchema.UserTable.Colmns.USERPASS,u.getPassword());
        myValue.put(ToDoListSchema.UserTable.Colmns.USEREMAIL,u.getEmail());

        return myValue;

    }


    public User getUserById(UUID id) {
        String whereClause=ToDoListSchema.UserTable.Colmns.USER_ID+ " = ?"  ;
        String[] whereArgs =new String[]{id.toString()};

        Cursor myCursor= mDatabase.query(ToDoListSchema.UserTable.NAME,
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
            UUID user_Id=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USER_ID)));
            String user_Name=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERNAME));
            String user_Password=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERPASS));
            String user_Email=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USEREMAIL));


            User user=new User(user_Id,user_Name,user_Password,user_Email);


            return user;

        }

        finally {
            myCursor.close();
        }
    }


    public User getUserByName(String name) {


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
            if(myCursor.getCount()==0)
                return null;

            myCursor.moveToFirst();
            UUID user_Id=UUID.fromString(myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USER_ID)));
            String user_Name=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERNAME));
            String user_Password=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USERPASS));
            String user_Email=myCursor.getString(myCursor.getColumnIndex(ToDoListSchema.UserTable.Colmns.USEREMAIL));


            User user=new User(user_Id,user_Name,user_Password,user_Email);


            return user;

        }

        finally {
            myCursor.close();
        }
    }



    /*
    public UUID userExistId(String name,String password){


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
