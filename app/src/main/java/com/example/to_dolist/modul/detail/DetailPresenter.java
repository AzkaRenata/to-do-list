package com.example.to_dolist.modul.detail;

import android.util.Log;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.utils.RequestCallback;

import java.util.List;

public class DetailPresenter implements DetailContract.Presenter{
    private final DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {           //add
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void moveToAdd(){
        view.redirectToAdd();
    }

    public void getTaskList(){
        view.getList(new RequestCallback<List<Task>>() {
            @Override
            public void requestSuccess(List<Task> data) {
                Log.e("MASOK", "ahahahah");
                //Log.e("SIZE", "5" + String.valueOf(data.size()));
                view.setList(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                Log.e("GAGAL", "AHAHAHA");
                view.showFailedMessage(errorMessage);
            }
        });
    }
}