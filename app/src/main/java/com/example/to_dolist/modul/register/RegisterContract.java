package com.example.to_dolist.modul.register;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.User;
import com.example.to_dolist.utils.RequestCallback;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void redirectToLogin();
        void setItems();
        void requestRegister(User user, final RequestCallback<RegisterResponse> requestCallback);
        void showSuccessMessage();
        void showErrorMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void performRegister(User user);
    }
}
