package com.example.to_dolist.modul.login;

import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UtilProvider;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();

        loginFragment = new LoginFragment(tokenSharedUtil);
        setCurrentFragment(loginFragment, false);

    }
}