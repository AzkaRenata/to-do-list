package com.example.to_dolist.modul.list;

import android.util.Log;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.data.source.local.TableHandler;
import com.example.to_dolist.data.source.session.SessionRepository;
import com.example.to_dolist.utils.RequestCallback;

import java.util.ArrayList;
import java.util.List;

public class ListPresenter implements ListContract.Presenter{
    private final ListContract.View view;

    public ListPresenter(ListContract.View view) {           //add
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
                view.saveTask(data);
                view.setList();
            }

            @Override
            public void requestFailed(String errorMessage) {
                Log.e("GAGAL", "AHAHAHA");
                view.showFailedMessage(errorMessage);
            }
        });
    }
}