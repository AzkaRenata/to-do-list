package com.example.to_dolist.modul.add;

public class AddPresenter implements AddContract.Presenter {
    private final AddContract.View view;

    public AddPresenter(AddContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void moveToList() {
        view.redirectToList();
    }
}

