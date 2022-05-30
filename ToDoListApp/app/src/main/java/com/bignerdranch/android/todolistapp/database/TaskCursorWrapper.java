package com.bignerdranch.android.todolistapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.todolistapp.Task;
import com.bignerdranch.android.todolistapp.database.TaskDbSchema.TaskTable;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask(){
        String uuidString = getString(getColumnIndex(TaskTable.Cols.UUID));
        String descriptionString = getString(getColumnIndex(TaskTable.Cols.DESCRIPTION));
        long date = getLong(getColumnIndex(TaskTable.Cols.DATE));
        int isCompleted = getInt(getColumnIndex(TaskTable.Cols.COMPLETED));

        Task task = new Task(UUID.fromString(uuidString));
        task.setDescription(descriptionString);
        task.setDate(new Date(date));
        task.setCompleted(isCompleted != 0);

        return task;
    }
}
