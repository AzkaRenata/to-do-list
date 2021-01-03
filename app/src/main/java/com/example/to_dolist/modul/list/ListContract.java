package com.example.to_dolist.modul.list;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.utils.RequestCallback;

import java.util.ArrayList;
import java.util.List;

public interface ListContract {
    interface View extends BaseView<Presenter> {
        void redirectToAdd();
        void setList();
        void getList(final RequestCallback<List<Task>> requestCallback);
        void showFailedMessage(String message);
        void saveTask(List<Task> task);
    }

    interface Presenter extends BasePresenter {
        void moveToAdd();
        void getTaskList();
    }
}
