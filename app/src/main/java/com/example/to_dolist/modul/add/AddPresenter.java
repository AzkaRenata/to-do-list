package com.example.to_dolist.modul.add;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.utils.RequestCallback;

import java.util.ArrayList;

public class AddPresenter implements AddContract.Presenter {
    private final AddActivity activity;
    private final AddContract.View view;

    public AddPresenter(AddContract.View view, AddActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {}

    @Override
    public void moveToList() {
        view.redirectToList();
    }

    @Override
    public void performAdd(Task newTask) {
        activity.startLoading();
        view.addData(newTask, new RequestCallback<AddResponse>() {
            @Override
            public void requestSuccess(AddResponse data) {
                activity.stopLoading();
                view.showSuccessMessage();
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
