package com.example.to_dolist.modul.add;

import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;

public class AddPresenter implements AddContract.Presenter {
    private final AddContract.View view;

    public AddPresenter(AddContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void moveToList(ArrayList<Task> taskList) {
        view.redirectToList(taskList);
    }

    @Override
    public void performAdd(String title, String description, ArrayList<Task> taskList) {


        view.redirectToList(taskList);
    }
}

