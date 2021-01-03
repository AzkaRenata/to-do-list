package com.example.to_dolist.modul.detail;

import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UtilProvider;

public class DetailActivity extends BaseFragmentHolderActivity {
    DetailFragment detailFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        detailFragment = new DetailFragment(tokenSharedUtil);
        setCurrentFragment(detailFragment, false);

    }
}