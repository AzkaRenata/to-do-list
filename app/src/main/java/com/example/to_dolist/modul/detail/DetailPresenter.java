package com.example.to_dolist.modul.detail;

import android.util.Log;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.list.ListResponse;
import com.example.to_dolist.utils.RequestCallback;

import java.util.List;

public class DetailPresenter implements DetailContract.Presenter{
    private final DetailActivity activity;
    private final DetailContract.View view;

    public DetailPresenter(DetailContract.View view, DetailActivity activity) {           //add
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {}

    public void getTaskDetail(){
        view.setTaskDetail();
    }

    @Override
    public void performDelete() {
        activity.startLoading();
        view.requestDelete(new RequestCallback<DetailResponse>() {
            @Override
            public void requestSuccess(DetailResponse data) {
                activity.stopLoading();
                view.showSuccessMessage();
                view.redirectToTaskList();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
                view.showFailedMessage(errorMessage);
            }
        });
    }

    @Override
    public void performChecked(String isChecked) {
        activity.startLoading();
        view.requestCheckedChange(isChecked, new RequestCallback<DetailResponse>() {
            @Override
            public void requestSuccess(DetailResponse data) {
                view.redirectToTaskList();
                activity.stopLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
                view.showFailedMessage(errorMessage);
            }
        });
    }
}