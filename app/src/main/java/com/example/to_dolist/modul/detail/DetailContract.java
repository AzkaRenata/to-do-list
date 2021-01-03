package com.example.to_dolist.modul.detail;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.utils.RequestCallback;

import java.util.List;

public interface DetailContract {
    interface View extends BaseView<Presenter> {
        void redirectToAdd();
        void setList(List<Task> taskList);
        void getList(final RequestCallback<List<Task>> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void moveToAdd();
        void getTaskList();
    }
}
