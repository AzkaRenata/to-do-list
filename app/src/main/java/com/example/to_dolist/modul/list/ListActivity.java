package com.example.to_dolist.modul.list;

import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;

public class ListActivity extends BaseFragmentHolderActivity {
    ListFragment listFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        listFragment = new ListFragment();
        setCurrentFragment(listFragment, false);

    }
}