package com.example.to_dolist.utils;

public class myURL {
    private final static String BASE_URL = "http://192.168.1.5:8000/api/";
    private final static String IMAGE_URL = "http://192.168.1.5:8000/storage/";

    public final static String LOGIN_URL = BASE_URL + "user/login";
    public final static String GOOGLE_LOGIN_URL = BASE_URL + "user/login/google";
    public final static String REGISTER_URL = BASE_URL + "user/register";
    public final static String GET_TASK_LIST_URL = BASE_URL + "task/list";
    public final static String UPDATE_TASK_URL = BASE_URL + "task/update/";
    public final static String UPDATE_CHECKED_TASK_URL = BASE_URL + "task/update/checked/";
    public final static String CREATE_TASK_URL = BASE_URL + "task/create";
    public final static String DELETE_TASK_URL = BASE_URL + "task/delete/";

    public static String getImageUrl(){
        return IMAGE_URL;
    }


}
