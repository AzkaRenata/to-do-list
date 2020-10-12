package com.example.to_dolist.modul.add;

import com.example.to_dolist.base.BasePresenter;
import com.example.to_dolist.base.BaseView;

public interface AddContract {
    interface View extends BaseView<AddContract.Presenter> {
        void redirectToList();
    }

    interface Presenter extends BasePresenter {
        void moveToList();
    }
}
