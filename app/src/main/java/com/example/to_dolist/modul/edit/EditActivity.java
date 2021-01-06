package com.example.to_dolist.modul.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;
import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;


public class EditActivity extends BaseFragmentHolderActivity {
    EditFragment editTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        int id = getIntent().getIntExtra("id", 0);

        editTaskFragment = new EditFragment(id);
        setCurrentFragment(editTaskFragment, false);

    }




}
