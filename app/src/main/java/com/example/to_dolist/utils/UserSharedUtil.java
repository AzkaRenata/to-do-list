package com.example.to_dolist.utils;

import android.content.SharedPreferences;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.data.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class UserSharedUtil {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public UserSharedUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setUser(User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("User", json);
        editor.commit();
    }

    public User getUser(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User", null);
        Type type = new TypeToken<User>(){}.getType();
        User user = gson.fromJson(json, type);

        return user;
    }

    public void clear(){
        editor.putString("User", null).apply();
    }

}
