package com.example.to_dolist.modul.login;

import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(){
        view.redirectToList();
    }

}