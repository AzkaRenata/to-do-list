package com.example.to_dolist.modul.list;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;

public interface ListContract {
    interface View extends BaseView<Presenter> {
        void redirectToAdd();
    }

    interface Presenter extends BasePresenter {
        void moveToAdd();
    }
}
