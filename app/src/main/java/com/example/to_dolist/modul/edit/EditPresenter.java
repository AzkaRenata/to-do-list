package com.example.to_dolist.modul.edit;

import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;


public class EditPresenter implements EditContract.Presenter{
    private final EditContract.View view;

    public EditPresenter(EditContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(final String title, final String description, ArrayList<Task> taskList, int id){
        taskList.get(id-1).setTitle(title);
        taskList.get(id-1).setDescription(description);
        view.redirectToTaskList(taskList);
    }

    @Override
    public void loadData(ArrayList<Task> taskList, int id) {
        Task task = taskList.get(id - 1);
        //load data task by id
        //then send data to fragment
        //Task task = new Task(3, "title of taskIndex:"+id, "description of taskIndex:"+id);
        view.showData(task);
    }

}
