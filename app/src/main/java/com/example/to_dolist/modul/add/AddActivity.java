package com.example.to_dolist.modul.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;
import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;

public class AddActivity extends BaseFragmentHolderActivity {
    AddFragment addFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        addFragment = new AddFragment();
        setCurrentFragment(addFragment, false);
    }
}
