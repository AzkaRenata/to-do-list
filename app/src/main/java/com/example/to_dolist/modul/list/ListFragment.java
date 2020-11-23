package com.example.to_dolist.modul.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.add.AddActivity;
import com.example.to_dolist.modul.edit.EditActivity;
import com.example.to_dolist.utils.RecyclerViewAdapterTodolist;

import java.io.Serializable;
import java.util.ArrayList;

public class ListFragment extends BaseFragment<ListActivity, ListContract.Presenter> implements ListContract.View {
    Button btnAdd;
    Button btnClear;
    ListView listView;
    ArrayList<Task> taskList = new ArrayList<>();
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> ListElementsArrayList;

    public ListFragment(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false);
        mPresenter = new ListPresenter(this);
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewTodolist);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterTodolist(taskList);
        mRecyclerView.setAdapter(mAdapter);

        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id = taskList.get(position).getId();
                //Log.d("BELAJAR ACTIVITY",">>>>>"+ position);
                editTask(id);
            }
        });

        btnAdd = fragmentView.findViewById(R.id.button_to_add_layout);
        btnClear = fragmentView.findViewById(R.id.button_clear_all);

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
        Bundle args = new Bundle();
        args.putSerializable("data", taskList);
        intent.putExtra("taskList", args);
        startActivity(intent);
    }

    public void editTask(int id) {
        Intent intent = new Intent(activity, EditActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("data", taskList);
        intent.putExtra("taskList", args);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}