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

        //ArrayList<Task> taskList = new ArrayList<>();

        //taskList.add(new Task(1,"Demo Android", "Demo todo list android"));
        //taskList.add(new Task(2, "WPPL Frontend", "Selesaikan Frontend Web"));

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        loginFragment = new LoginFragment(tokenSharedUtil);
        setCurrentFragment(loginFragment, false);

    }
}