package com.example.to_dolist.modul.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.list.ListActivity;
import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddFragment extends BaseFragment<AddActivity, AddContract.Presenter> implements AddContract.View {
    Button btnBack;
    Button btnAdd;
    EditText etTaskTitle;
    EditText etTaskDescription;
    ArrayList<Task> taskList = new ArrayList<>();

    public AddFragment(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_add, container, false);
        mPresenter = new AddPresenter(this);
        mPresenter.start();

        etTaskTitle = fragmentView.findViewById(R.id.task_add);
        etTaskDescription = fragmentView.findViewById(R.id.task_description);
        btnAdd = fragmentView.findViewById(R.id.button_add);
        btnBack = fragmentView.findViewById(R.id.button_back);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnAddClick();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnBackClick();
            }
        });

        setTitle("New Activity");

        return fragmentView;
    }

    public void setBtnAddClick(){
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        mPresenter.performAdd(title,description,taskList);
    }

    public void setBtnBackClick(){
        mPresenter.moveToList(taskList);
    }

    @Override
    public void redirectToList(ArrayList<Task> taskList) {
        Intent intent = new Intent(activity, ListActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("data", taskList);
        intent.putExtra("taskList", args);
        startActivity(intent);
    }

    @Override
    public void setPresenter(AddContract.Presenter presenter) {
        mPresenter = presenter;
    }
}