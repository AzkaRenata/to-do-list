package com.example.to_dolist.modul.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.modul.add.AddActivity;

public class ListFragment extends BaseFragment<ListActivity, ListContract.Presenter> implements ListContract.View {
    Button btnAdd;

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false);
        mPresenter = new ListPresenter(this);
        mPresenter.start();

        btnAdd = fragmentView.findViewById(R.id.button_to_add_layout);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnAddClick();
            }
        });

        setTitle("My To-do List");

        return fragmentView;
    }

    public void setBtnAddClick(){
        mPresenter.moveToAdd();
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToAdd() {
        Intent intent = new Intent(activity, AddActivity.class);
        startActivity(intent);
        activity.finish();
    }
}