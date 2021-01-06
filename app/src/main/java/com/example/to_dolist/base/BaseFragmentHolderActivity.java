package com.example.to_dolist.base;

import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_dolist.R;

public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected ImageButton btShare;
    protected ImageButton btDelete;
    protected View vMenuBarShadow;
    protected RelativeLayout rlActivityFragmentHolder;
    protected RelativeLayout loading;

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        btShare = findViewById(R.id.task_share_btn);
        btDelete = findViewById(R.id.task_delete_btn);
        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
        vMenuBarShadow = findViewById(R.id.vMenuBarShadow);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
        loading = (RelativeLayout) findViewById(R.id.loading_screen);

        btShare.setVisibility(View.GONE);
        btDelete.setVisibility(View.GONE);

    }

    public void setTitle(String title) {
        this.tvToolbarTitle.setText(title);
    }

    public void startLoading(){
        loading.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void stopLoading(){
        loading.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}

