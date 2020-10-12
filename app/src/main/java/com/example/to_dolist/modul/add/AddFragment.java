package com.example.to_dolist.modul.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.to_dolist.modul.list.ListActivity;
import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;

public class AddFragment extends BaseFragment<AddActivity, AddContract.Presenter> implements AddContract.View {
    Button btnBack;

    public AddFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_add, container, false);
        mPresenter = new AddPresenter(this);
        mPresenter.start();

        btnBack = fragmentView.findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnBackClick();
            }
        });

        setTitle("New Activity");

        return fragmentView;
    }

    public void setBtnBackClick(){
        mPresenter.moveToList();;
    }

    @Override
    public void redirectToList() {
        Intent intent = new Intent(activity, ListActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setPresenter(AddContract.Presenter presenter) {}
}
