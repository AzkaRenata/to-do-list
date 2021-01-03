package com.example.to_dolist.utils;

public interface RequestCallback<T> {
    void requestSuccess(T data);
    void requestFailed(String errorMessage);
}

