package com.example.to_dolist.modul.list;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;

public interface ListContract {
    interface View extends BaseView<Presenter> {
        void redirectToAdd();
    }

    interface Presenter extends BasePresenter {
        void moveToAdd();
        ArrayList<Task> getTaskList();
    }
}
