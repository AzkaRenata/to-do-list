package com.example.to_dolist.modul.login;

import android.util.Log;
import android.widget.Toast;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.utils.RequestCallback;

import java.util.ArrayList;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
        void requestLogin(final String email, String password, final RequestCallback<LoginResponse> requestCallback);
        void saveToken(String token);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
