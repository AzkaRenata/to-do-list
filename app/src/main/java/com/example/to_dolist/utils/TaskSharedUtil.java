package com.example.to_dolist.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.to_dolist.data.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskSharedUtil {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public TaskSharedUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setTask(List<Task> task){
        Gson gson = new Gson();
        String json = gson.toJson(task);
        editor.putString("Task", json);
        editor.commit();
    }

    public List<Task> getTask(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Task", "");
        Type type = new TypeToken<List<Task>>(){}.getType();
        List<Task> task = gson.fromJson(json, type);

        return task;
    }

    public void clear(){
        editor.putString("Task", null).apply();
    }

}
