package com.bignerdranch.android.todolistapp.database;

public class TaskDbSchema {
    public static final class TaskTable{
        public static final String NAME = "Tasks";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String DESCRIPTION = "description";
            public static final String DATE = "date";
            public static final String COMPLETED = "completed";
        }
    }
}
