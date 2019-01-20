package com.example.admin.myhw6.database;

import java.util.UUID;

public class ToDoListSchema {

    public static final String NAME="TaskDataBase";
    public static final int VERSION=1;

    public static final class TaskTable{
        public static final String NAME="TableOfTasks";

        public static final class Colns{
            public static final String USER_ID ="user_id";
            public static final String USERPASS="user_pass_notUsed";
            public static final String UUID="uuid";
            public static final String TITLE="title";
            public static final String DESCRIPTION="description";
            public static final String ISDONE="isDone";
            public static final String DATE="date";


        }
    }



    public static final class UserTable {
        public static final String NAME = "TableOfUsers";

        public static final class Colmns {
            public static final String USER_ID = "user_id";
            public static final String USERNAME= "user_name";
            public static final String USERPASS = "user_pass";
            public static final String USEREMAIL = "user_email";
        }
    }
}
