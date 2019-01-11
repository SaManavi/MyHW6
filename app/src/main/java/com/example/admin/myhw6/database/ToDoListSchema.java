package com.example.admin.myhw6.database;

import java.util.UUID;

public class ToDoListSchema {

    public static final String NAME="TaskDataBase";
    public static final int VERSION=1;

    public static final class TaskTable{
        public static final String NAME="TableOfTask";

        public static final class Colmns{
            public static final String USER="user";
            public static final String USERPASS="userpass";
            public static final String UUID="uuid";
            public static final String TITLE="title";
            public static final String DESCRIPTION="description";
            public static final String ISDONE="isDone";
            public static final String DATE="date";


        }
    }


}
