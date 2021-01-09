package com.example.to_dolist.modul.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.data.model.User;
import com.example.to_dolist.modul.login.LoginActivity;
import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.myURL;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.to_dolist.R.id.confirm_passwordR;
import static com.example.to_dolist.R.id.emailR;
import static com.example.to_dolist.R.id.login;
import static com.example.to_dolist.R.id.passwordR;
import static com.example.to_dolist.R.id.register_btnR;
import static com.example.to_dolist.R.id.register_confirm_password_til;
import static com.example.to_dolist.R.id.register_password_til;
import static com.example.to_dolist.R.id.usernameR;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View {
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;

    public RegisterFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        mPresenter = new RegisterPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setItems(){
        TextInputLayout tilPassword;
        TextInputLayout tilConfirmPassword;

        RadioGroup rgGender;
        Button btnRegister;
        TextView tvLogin;

        etUsername = fragmentView.findViewById(usernameR);
        etEmail = fragmentView.findViewById(emailR);
        etPassword = fragmentView.findViewById(passwordR);
        etConfirmPassword = fragmentView.findViewById(confirm_passwordR);

        btnRegister = fragmentView.findViewById(register_btnR);
        tvLogin = fragmentView.findViewById(login);
        tilPassword = fragmentView.findViewById(register_password_til);
        tilConfirmPassword = fragmentView.findViewById(register_confirm_password_til);

        tilPassword.setHintEnabled(false);
        tilConfirmPassword.setHintEnabled(false);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtRegisterClick();
            }
        });

        tvLogin.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTvLoginClick();
                return true;
            }
        });

        setTitle("Register");
    }

    public void setBtRegisterClick(){
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        User user = new User(0, username, email);

        mPresenter.performRegister(user);
    }

    public void setTvLoginClick(){
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestRegister(final User user, final RequestCallback<RegisterResponse> requestCallback) {
        String password = etPassword.getText().toString();
        String confirm_password = etConfirmPassword.getText().toString();
        AndroidNetworking.post(myURL.REGISTER_URL)
                .addBodyParameter("username", user.getUsername())
                .addBodyParameter("email", user.getEmail())
                .addBodyParameter("password", password)
                .addBodyParameter("password_confirmation", confirm_password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener<RegisterResponse>() {
                    @Override
                    public void onResponse(RegisterResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 400) {
                            String error = anError.getResponse().header("error");
                            requestCallback.requestFailed(error);
                        }else{
                            requestCallback.requestFailed("Ada yang Salah");
                        }
                    }
                });
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "User Berhasil Didaftarkan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }
}