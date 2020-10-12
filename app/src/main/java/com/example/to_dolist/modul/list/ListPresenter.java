package com.example.to_dolist.modul.list;

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
}