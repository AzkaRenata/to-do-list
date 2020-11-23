package com.example.to_dolist.modul.add;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;

public interface AddContract {
    interface View extends BaseView<AddContract.Presenter> {
        void redirectToList(ArrayList<Task> taskList);
    }

    interface Presenter extends BasePresenter {
        void moveToList(ArrayList<Task> taskList);
        void performAdd(String title, String description, ArrayList<Task> taskList);
    }
}
