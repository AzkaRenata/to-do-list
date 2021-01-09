package com.example.to_dolist.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.to_dolist.data.model.User;
import com.example.to_dolist.modul.list.ListActivity;
import com.example.to_dolist.modul.register.RegisterActivity;
import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UserSharedUtil;
import com.example.to_dolist.utils.UtilProvider;
import com.example.to_dolist.utils.myURL;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    TokenSharedUtil tokenSharedUtil;
    UserSharedUtil userSharedUtil;
    TextInputLayout tilEmail;
    TextInputLayout tilPassword;
    TextView tvRegister;
    Button googleBtn;
    GoogleSignInClient mGoogleSignInClient;

    public LoginFragment(TokenSharedUtil tokenSharedUtil) {
        this.tokenSharedUtil = tokenSharedUtil;
        userSharedUtil = UtilProvider.getUserSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, activity);
        mPresenter.start();

        etUsername = fragmentView.findViewById(R.id.login_email_et);
        etPassword = fragmentView.findViewById(R.id.login_password_et);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
        tilEmail = fragmentView.findViewById(R.id.login_email_til);
        tilPassword = fragmentView.findViewById(R.id.login_password_til);
        tvRegister = fragmentView.findViewById(R.id.register);
        googleBtn = fragmentView.findViewById(R.id.google_button);

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

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        initGSO();

        setTitle("Sign in");

        return fragmentView;
    }

    private void googleSignIn() {
        Log.e("tes1", "tessss1");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("tes2", "tessss1");

        if (requestCode == 1) {
            Log.e("tes3", "tessss1");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.e("tes4", "tessss1");
            mPresenter.performGoogleLogin(account.getEmail(), account.getDisplayName());
        } catch (ApiException e) {
            Log.e("tes", String.valueOf(e.getStatusCode()));
            Log.e("tesss", "t " + e.getMessage());
            Toast.makeText(activity, "Failed to sign in with google", Toast.LENGTH_SHORT).show();
        }
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

    private void initGSO(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
        if(account != null)
            mGoogleSignInClient.signOut();
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
        AndroidNetworking.post(myURL.LOGIN_URL)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
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
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        requestCallback.requestFailed("Wrong Email or Password");
                    }
                });
    }

    public void requestGoogleLogin(String email, String name, final RequestCallback<LoginResponse> callback) {
        AndroidNetworking.post(myURL.GOOGLE_LOGIN_URL)
                .addBodyParameter("email", email)
                .addBodyParameter("name", name)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        callback.requestSuccess(response);
                    }
                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 401)
                            callback.requestFailed("Wrong Credentials");
                        else
                            callback.requestFailed("Something is Wrong");
                    }
                });
    }

    public void saveToken(String token) {
        tokenSharedUtil.setToken(token);
    }

    public void saveUser(User user){
        userSharedUtil.setUser(user);
    }

    public void showFailedMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}