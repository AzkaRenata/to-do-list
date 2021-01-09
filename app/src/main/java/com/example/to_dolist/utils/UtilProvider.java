package com.example.to_dolist.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilProvider {
    private static TokenSharedUtil tokenSharedUtil;
    private static TaskSharedUtil taskSharedUtil;
    private static UserSharedUtil userSharedUtil;

    public static void initialize(Context context){
        initSharedPreferencesUtil(context);
    }

    private static void initSharedPreferencesUtil(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("example", Context.MODE_PRIVATE);
        tokenSharedUtil = new TokenSharedUtil(sharedPreferences);
        taskSharedUtil = new TaskSharedUtil(sharedPreferences);
        userSharedUtil = new UserSharedUtil(sharedPreferences);
    }

    public static TokenSharedUtil getTokenSharedUtil(){
        return tokenSharedUtil;
    }

    public static TaskSharedUtil getTaskSharedUtil(){
        return taskSharedUtil;
    }

    public static UserSharedUtil getUserSharedUtil(){
        return userSharedUtil;
    }
}
