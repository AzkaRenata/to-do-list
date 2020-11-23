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

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("taskList");
        ArrayList<Task> taskList = (ArrayList<Task>) args.getSerializable("data");
        int id = intent.getIntExtra("id", 0);

        editTaskFragment = new EditFragment(taskList, id);
        //editTaskFragment.setTask(task);
        setCurrentFragment(editTaskFragment, false);

    }




}
