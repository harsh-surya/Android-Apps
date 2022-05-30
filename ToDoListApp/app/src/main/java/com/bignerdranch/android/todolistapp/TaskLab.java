package com.bignerdranch.android.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.todolistapp.database.TaskBaseHelper;
import com.bignerdranch.android.todolistapp.database.TaskCursorWrapper;
import com.bignerdranch.android.todolistapp.database.TaskDbSchema.TaskTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskLab {
    private static TaskLab sTaskLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TaskLab get (Context context){
    if(sTaskLab == null){
        sTaskLab = new TaskLab(context);
    }
    return sTaskLab;
    }

    private TaskLab(Context context){
    mContext = context.getApplicationContext();
    mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
    }

    public void addTask(Task t){
        ContentValues values = getContentValues(t);
        mDatabase.insert(TaskTable.NAME, null, values);
    }

    public List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>();
        TaskCursorWrapper cursor = queryTasks(null, null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return tasks;
    }

    public Task getTask(UUID id){
        TaskCursorWrapper cursor = queryTasks(
                TaskTable.Cols.UUID + " = ?" ,
                new String[] {id.toString()}
        );
        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTask();
        }finally {
            cursor.close();
        }
    }

    public void updateTask(Task task){
        String uuidString = task.getId().toString();
        ContentValues values = getContentValues(task);
        mDatabase.update(TaskTable.NAME, values,
                TaskTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public void deleteTask(Task task){
        String uuidString = task.getId().toString();
        mDatabase.delete(TaskTable.NAME,
                TaskTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                TaskTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new TaskCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskTable.Cols.UUID, task.getId().toString());
        contentValues.put(TaskTable.Cols.DESCRIPTION, task.getDescription());
        contentValues.put(TaskTable.Cols.DATE, task.getDate().getTime());
        contentValues.put(TaskTable.Cols.COMPLETED, task.isCompleted() ? 1 : 0);

        return contentValues;
    }

}
