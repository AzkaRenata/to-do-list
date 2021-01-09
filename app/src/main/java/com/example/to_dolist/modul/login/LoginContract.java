package com.example.to_dolist.modul.login;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.User;
import com.example.to_dolist.utils.RequestCallback;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
        void requestLogin(final String email, String password, final RequestCallback<LoginResponse> requestCallback);
        void saveToken(String token);
        void saveUser(User user);
        void showFailedMessage(String message);
        void requestGoogleLogin(String email, String name, final RequestCallback<LoginResponse> callback);
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
        void performGoogleLogin(String email, String name);
    }
}
