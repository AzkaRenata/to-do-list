package com.example.to_dolist.modul.login;

import android.content.Intent;
import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UtilProvider;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();

        loginFragment = new LoginFragment(tokenSharedUtil);
        setCurrentFragment(loginFragment, false);

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}