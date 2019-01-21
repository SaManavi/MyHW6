package com.example.admin.myhw6.ORM;

import android.app.Application;

import com.example.admin.myhw6.Model.DaoMaster;
import com.example.admin.myhw6.Model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private static App myapp;
    DaoSession mydaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        MyDevOpenHelper myDevOpenHelper=new MyDevOpenHelper(this,"ToDoListDataBase");
        Database mDatabase  =myDevOpenHelper.getReadableDb();

        mydaoSession=new DaoMaster(mDatabase).newSession();
        myapp=this;
    }


    public static App getApp() {
        return myapp;
    }

    public DaoSession getDaoSession() {
        return mydaoSession;
    }

}
