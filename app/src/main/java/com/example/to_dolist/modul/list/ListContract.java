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
        void checkResult();
        void setList();
        void getList(final RequestCallback<List<Task>> requestCallback);
        void requestCheckedChange(String isChecked, int id, final RequestCallback<ListResponse> requestCallback);
        void showFailedMessage(String message);
        void saveTask(List<Task> task);
        void checkTask(String isChecked, int id);
    }

    interface Presenter extends BasePresenter {
        void moveToAdd();
        void getTaskList();
        void performChecked(String isChecked, int id);
    }
}
