package com.example.to_dolist.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.data.source.session.UserSessionRepositoryRepository;
import com.example.to_dolist.modul.list.ListActivity;

import java.util.ArrayList;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    ArrayList<Task> taskList = new ArrayList<>();

    public LoginFragment(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, new UserSessionRepositoryRepository(getActivity()));
        mPresenter.start();

        etUsername = fragmentView.findViewById(R.id.et_username);
        etPassword = fragmentView.findViewById(R.id.et_password);
        btnLogin = fragmentView.findViewById(R.id.bt_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        setTitle("Sign in");

        return fragmentView;
    }

    public void setBtLoginClick(){
        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin(email, password);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToList() {
        Intent intent = new Intent(activity, ListActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("data", taskList);
        intent.putExtra("taskList", args);
        startActivity(intent);
    }
}