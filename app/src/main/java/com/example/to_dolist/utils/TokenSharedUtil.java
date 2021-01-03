package com.example.to_dolist.utils;

import android.content.SharedPreferences;
import android.util.Log;

public class TokenSharedUtil {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public TokenSharedUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setToken(String token){
        sharedPreferences.edit().putString("token", token).apply();
    }

    public String getToken(){
        return sharedPreferences.getString("token", null);
    }

    public void clear(){
        editor.putString("token", null).apply();
    }
}
