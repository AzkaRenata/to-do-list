package com.example.to_dolist.modul.add;

import android.content.Intent;
import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;

public class AddActivity extends BaseFragmentHolderActivity {
    AddFragment addFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        addFragment = new AddFragment();
        setCurrentFragment(addFragment, false);
    }
}
