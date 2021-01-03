package com.example.to_dolist.data.source.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import com.example.to_dolist.data.model.Task;

public class TaskSessionRepository implements SessionRepository<Task>{

    private static String SESSION_TASK = "SessionTask";
    private SharedPreferences sharedPref;

    public TaskSessionRepository(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Task initialize(Task sessionData) {
        //save to shared preference
        setSessionData(sessionData);

        //load from shared preference
        return getSessionData();
    }

    @Override
    public Task getSessionData() {
        String sessionDataJson = sharedPref.getString(SESSION_TASK, null);

        if (sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, Task.class);
        }
        return null;
    }

    @Override
    public void setSessionData(Task sessionData) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SESSION_TASK, new Gson().toJson(sessionData));
        editor.apply();
    }

    @Override
    public void destroy() {
        sharedPref.edit().clear().apply();
    }

    @Override
    public void update(Task sessionData) {

        destroy();
        setSessionData(sessionData);
    }
}
