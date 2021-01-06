package com.example.to_dolist.modul.register;

import com.example.to_dolist.data.model.User;
import com.example.to_dolist.utils.RequestCallback;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterActivity activity;
    private final RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view, RegisterActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start(){
        view.setItems();
    }

    @Override
    public void performRegister(User user){
        activity.startLoading();
        view.requestRegister(user, new RequestCallback<RegisterResponse>() {
            @Override
            public void requestSuccess(RegisterResponse data) {
                activity.stopLoading();
                view.showSuccessMessage();
                view.redirectToLogin();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
                view.showErrorMessage(errorMessage);
            }
        });
    }
}