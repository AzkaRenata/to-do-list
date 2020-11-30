package com.example.to_dolist.modul.login;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.data.model.User;
import com.example.to_dolist.data.source.session.SessionRepository;

import java.util.ArrayList;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void start() {
        if(sessionRepository.getSessionData() != null){
            view.redirectToList();
        }
    }

    @Override
    public void performLogin(final String email, final String password){
        User loggedUser = new User(email, "TOKEN123456");
        sessionRepository.setSessionData(loggedUser);

        view.redirectToList();
    }

}