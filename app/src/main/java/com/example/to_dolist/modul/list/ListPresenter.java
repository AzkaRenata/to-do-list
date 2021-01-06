package com.example.to_dolist.modul.list;

import android.util.Log;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.data.source.local.TableHandler;
import com.example.to_dolist.data.source.session.SessionRepository;
import com.example.to_dolist.utils.RequestCallback;

import java.util.ArrayList;
import java.util.List;

public class ListPresenter implements ListContract.Presenter{
    private final ListActivity activity;
    private final ListContract.View view;

    public ListPresenter(ListContract.View view, ListActivity activity) {           //add
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {}

    @Override
    public void moveToAdd(){
        view.redirectToAdd();
    }

    public void getTaskList(){
        activity.startLoading();
        view.getList(new RequestCallback<List<Task>>() {
            @Override
            public void requestSuccess(List<Task> data) {
                view.saveTask(data);
                view.setList();
                view.checkResult();
                activity.stopLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
                view.showFailedMessage(errorMessage);
            }
        });
    }

    @Override
    public void performChecked(String isChecked, int id) {
        activity.startLoading();
        view.requestCheckedChange(isChecked, id, new RequestCallback<ListResponse>() {
            @Override
            public void requestSuccess(ListResponse data) {
                view.saveTask(data.taskList);
                view.setList();
                view.checkResult();
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