package com.example.to_dolist.modul.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.list.ListActivity;
import com.example.to_dolist.modul.register.RegisterActivity;
import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.myURL;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    TokenSharedUtil tokenSharedUtil;
    TextInputLayout tilEmail;
    TextInputLayout tilPassword;
    TextView tvRegister;

    public LoginFragment(TokenSharedUtil tokenSharedUtil) {
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, tokenSharedUtil, activity);
        mPresenter.start();

        etUsername = fragmentView.findViewById(R.id.login_email_et);
        etPassword = fragmentView.findViewById(R.id.login_password_et);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
        tilEmail = fragmentView.findViewById(R.id.login_email_til);
        tilPassword = fragmentView.findViewById(R.id.login_password_til);
        tvRegister = fragmentView.findViewById(R.id.register);

        tilEmail.setHintEnabled(false);
        tilPassword.setHintEnabled(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTvRegisterClick();
            }
        });

        setTitle("Sign in");

        return fragmentView;
    }

    public void setTvRegisterClick() {
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
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
        startActivity(intent);
    }

    public void requestLogin(final String email, String password, final RequestCallback<LoginResponse> requestCallback) {
        Log.e("tes", "tes");
        Log.e("tess", "ajez@gmail.com");
        Log.e("tess", "ajez123");
        AndroidNetworking.post(myURL.LOGIN_URL)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        //Log.e("tes", "tes2");
                        Log.e("tes", email);
                        if (response == null) {
                            Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
                            requestCallback.requestFailed("Null Response");
                        } else if (response.token == null) {
                            Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
                            requestCallback.requestFailed("Wrong Email or Password");
                        } else {
                            Toast.makeText(getContext(), email, Toast.LENGTH_SHORT).show();
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tesww", String.valueOf(anError.getErrorCode()));
                        Log.e("teswwwww", "fdfd" + anError.getErrorBody());
                        Log.e("teswwww", "fdfdsasa" + anError.getErrorDetail());
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        requestCallback.requestFailed("Wrong Email or Password");
                    }
                });
    }

    public void saveToken(String token) {
        Log.e("tes555", token);
        tokenSharedUtil.setToken(token);
    }

    public void showFailedMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}