package com.example.to_dolist.modul.list;

import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UtilProvider;

public class ListActivity extends BaseFragmentHolderActivity {
    ListFragment listFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();

        btLogout.setVisibility(View.VISIBLE);
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        listFragment = new ListFragment(tokenSharedUtil, btLogout);
        setCurrentFragment(listFragment, false);

    }
}