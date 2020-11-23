package com.example.to_dolist.modul.list;

import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;

public class ListPresenter implements ListContract.Presenter{
    private final ListContract.View view;

    public ListPresenter(ListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void moveToAdd(){
        view.redirectToAdd();
    }

    public ArrayList<Task> getTaskList(){
        ArrayList<Task> data = new ArrayList<Task> ();
        data.add(new Task(1,"Demo Android", "Demo todo list android"));
        data.add(new Task(2, "WPPL Frontend", "Selesaikan Frontend Web"));

        return data;
    }
}