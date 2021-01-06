package com.example.to_dolist.modul.register;

import android.widget.RelativeLayout;

import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        RegisterFragment registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, false);

    }
}