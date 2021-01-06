package com.example.to_dolist.modul.edit;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.utils.RequestCallback;

import java.util.ArrayList;

public interface EditContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
        void showData();
        void setId(int id);
        void setTask(Task task);
        void requestEdit(Task newTask, final RequestCallback<EditResponse> requestCallback);
        void showFailedMessage(String message);
        void showSuccessMessage();
    }

    interface Presenter extends BasePresenter {
        void saveData(Task newTask);
        void loadData(ArrayList<Task> taskList, int id);
    }
}
