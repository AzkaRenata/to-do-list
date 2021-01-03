package com.example.to_dolist.modul.login;

import android.util.Log;

import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.TokenSharedUtil;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final TokenSharedUtil sessionRepository;

    public LoginPresenter(LoginContract.View view, TokenSharedUtil sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void performLogin(final String email, final String password){
        view.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.redirectToList();
                Log.e("tes", data.token);
                view.saveToken(data.token);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }

}