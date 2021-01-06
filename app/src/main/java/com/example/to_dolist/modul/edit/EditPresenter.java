package com.example.to_dolist.modul.edit;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.utils.RequestCallback;

import java.util.ArrayList;


public class EditPresenter implements EditContract.Presenter{
    private final EditActivity activity;
    private final EditContract.View view;

    public EditPresenter(EditContract.View view, EditActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(Task newTask){
        activity.startLoading();
        view.requestEdit(newTask, new RequestCallback<EditResponse>() {
            @Override
            public void requestSuccess(EditResponse data) {
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
    public void loadData(ArrayList<Task> taskList, int id) {

    }

    public void performEdit(){

    }
}
