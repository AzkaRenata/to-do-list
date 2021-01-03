package com.example.to_dolist.utils;

public class myURL {
    private final static String BASE_URL = "http://192.168.1.5:8000/api/";
    private final static String IMAGE_URL = "http://192.168.1.5:8000/storage/";

    public final static String LOGIN_URL = BASE_URL + "user/login";
    public final static String GET_TASK_LIST_URL = BASE_URL + "task/list";
    public static String getImageUrl(){
        return IMAGE_URL;
    }


}
