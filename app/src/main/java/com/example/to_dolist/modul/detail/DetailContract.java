package com.example.to_dolist.modul.detail;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.list.ListResponse;
import com.example.to_dolist.utils.RequestCallback;

import java.util.List;

public interface DetailContract {
    interface View extends BaseView<Presenter> {
        void setTaskDetail();
        void redirectToTaskList();
        void showFailedMessage(String message);
        void showSuccessMessage();
        void requestDelete(final RequestCallback<DetailResponse> callback);
        void requestCheckedChange(String isChecked, final RequestCallback<DetailResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void getTaskDetail();
        void performDelete();
        void performChecked(String isChecked);
    }
}
