package com.bignerdranch.android.todolistapp;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mDescription;
    private boolean mCompleted;
    private Date mDate;

    public Task(){
        this(UUID.randomUUID());
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Task(UUID id){
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }
}
