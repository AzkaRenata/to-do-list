package com.example.to_dolist;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.example.to_dolist.utils.UtilProvider;

// Set this class to android:name in AndroidManifest.xml at application tag
public class Starter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
        UtilProvider.initialize(getApplicationContext());
    }
}
