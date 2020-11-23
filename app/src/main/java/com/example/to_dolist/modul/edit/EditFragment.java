package com.example.to_dolist.modul.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.list.ListActivity;

import java.util.ArrayList;

public class EditFragment extends BaseFragment<EditActivity, EditContract.Presenter> implements EditContract.View {
    EditText etTaskTitle;
    EditText etTaskDescription;
    Button btnAdd;
    //String id;
    Task task;
    TextView test;
    ArrayList<Task> taskList;
    int id;


    public EditFragment(ArrayList<Task> taskList, int id) {
        this.taskList = taskList;
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_add, container, false);
        mPresenter = new EditPresenter(this);
        mPresenter.start();

        etTaskTitle = fragmentView.findViewById(R.id.task_add);
        etTaskDescription = fragmentView.findViewById(R.id.task_description);
        btnAdd = fragmentView.findViewById(R.id.button_add);
        btnAdd.setText("Edit");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        setTitle("Edit Task");
        mPresenter.loadData(taskList, id);

        return fragmentView;
    }

    public void setBtSaveClick(){
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        mPresenter.saveData(title, description, taskList, id);
    }

    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList(ArrayList<Task> taskList) {
        Intent intent = new Intent(activity, ListActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("data", taskList);
        intent.putExtra("taskList", args);
        startActivity(intent);
    }

    @Override
    public void showData(Task task) {
        etTaskTitle.setText(task.getTitle());
        etTaskDescription.setText(task.getDescription());
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

}
