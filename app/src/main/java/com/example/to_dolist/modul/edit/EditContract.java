package com.example.to_dolist.modul.edit;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;

public interface EditContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList(ArrayList<Task> taskList);
        void showData(Task task);
        void setId(int id);
        void setTask(Task task);
    }

    interface Presenter extends BasePresenter {
        void saveData(String title, String description, ArrayList<Task> taskList, int id);
        void loadData(ArrayList<Task> taskList, int id);
    }
}
