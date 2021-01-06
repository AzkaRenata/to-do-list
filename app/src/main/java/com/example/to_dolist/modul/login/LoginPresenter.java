package com.example.to_dolist.modul.login;

import android.util.Log;

import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.TokenSharedUtil;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginActivity activity;
    private final LoginContract.View view;
    private final TokenSharedUtil sessionRepository;

    public LoginPresenter(LoginContract.View view, TokenSharedUtil sessionRepository, LoginActivity activity) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        this.activity = activity;
    }

    @Override
    public void start() {

    }

    @Override
    public void performLogin(final String email, final String password){
        activity.startLoading();
        view.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                activity.stopLoading();
                view.saveToken(data.token);
                view.redirectToList();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
                view.showFailedMessage(errorMessage);
            }
        });
    }

}