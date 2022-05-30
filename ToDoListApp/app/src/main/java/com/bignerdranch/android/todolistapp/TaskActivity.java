package com.bignerdranch.android.todolistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

public class TaskActivity extends AppCompatActivity {
    private static final String EXTRA_TASK_ID = "com.bignerdranch.android.todolistapp.task_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UUID taskId =(UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_frame);
        if(fragment == null){
            fragment = TaskFragment.newInstance(taskId);
            fm.beginTransaction()
                    .add(R.id.main_frame, fragment)
                    .commit();
        }


    }

    public static Intent newIntent(Context context, UUID taskId){
        Intent intent = new Intent(context, TaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID,taskId);
        return intent;
    }

}
