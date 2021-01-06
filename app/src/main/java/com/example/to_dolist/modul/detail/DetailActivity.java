package com.example.to_dolist.modul.detail;

import android.view.View;

import com.example.to_dolist.base.BaseFragmentHolderActivity;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UtilProvider;

public class DetailActivity extends BaseFragmentHolderActivity {
    DetailFragment detailFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        int id =getIntent().getIntExtra("id", 0);
        btShare.setVisibility(View.VISIBLE);
        btDelete.setVisibility(View.VISIBLE);
        detailFragment = new DetailFragment(id, btShare, btDelete);
        setCurrentFragment(detailFragment, false);

    }
}