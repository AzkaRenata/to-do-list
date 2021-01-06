package com.example.to_dolist.modul.add;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.utils.RequestCallback;

import java.util.ArrayList;

public interface AddContract {
    interface View extends BaseView<AddContract.Presenter> {
        void redirectToList();
        void addData(Task newTask, final RequestCallback<AddResponse> requestCallback);
        void showFailedMessage(String message);
        void showSuccessMessage();
    }

    interface Presenter extends BasePresenter {
        void moveToList();
        void performAdd(Task newTask);
    }
}
