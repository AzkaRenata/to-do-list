package com.example.to_dolist.modul.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;
import com.example.to_dolist.data.model.Task;

import java.util.ArrayList;

public class ListActivity extends BaseFragmentHolderActivity {
    ListFragment listFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();
        ArrayList<Task> taskList = new ArrayList<>();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("taskList");
        taskList = (ArrayList<Task>) args.getSerializable("data");

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        listFragment = new ListFragment(taskList);
        setCurrentFragment(listFragment, false);

    }
}