package com.example.to_dolist.modul.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.add.AddActivity;
import com.example.to_dolist.modul.edit.EditActivity;
import com.example.to_dolist.utils.RecyclerViewAdapterTodolist;
import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.myURL;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends BaseFragment<DetailActivity, DetailContract.Presenter> implements DetailContract.View {
    Button btnAdd;
    Button btnClear;
    ListView listView;
    ArrayList<Task> taskList = new ArrayList<>();
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> ListElementsArrayList;
    TokenSharedUtil tokenSharedUtil;

    public DetailFragment(TokenSharedUtil tokenSharedUtil) {
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false);
        mPresenter = new DetailPresenter(this);
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewTodolist);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        btnAdd = fragmentView.findViewById(R.id.button_to_add_layout);
        btnClear = fragmentView.findViewById(R.id.button_clear_all);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnAddClick();
            }
        });

        mPresenter.getTaskList();

        setTitle("My To-do List");

        return fragmentView;
    }

    public void setBtnAddClick(){
        mPresenter.moveToAdd();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
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

    @Override
    public void setList(List<Task> taskList) {
        Log.e("TITLE", taskList.get(0).getTitle());
        mAdapter = new RecyclerViewAdapterTodolist(taskList);
        mRecyclerView.setAdapter(mAdapter);

        /*
        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //int id = taskList.get(position).getId();
                //Log.d("BELAJAR ACTIVITY",">>>>>"+ position);
                //editTask(id);
            }
        });

         */
    }

    public void editTask(int id) {
        Intent intent = new Intent(activity, EditActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("data", taskList);
        intent.putExtra("taskList", args);
        intent.putExtra("id", id);

        startActivity(intent);
    }

    public void getList(final RequestCallback<List<Task>> requestCallback) {
        AndroidNetworking.get(myURL.GET_TASK_LIST_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(DetailResponse.class, new ParsedRequestListener<DetailResponse>() {
                    @Override
                    public void onResponse(DetailResponse response) {
                        Log.e("TESS", "MASOKK");
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            //Log.e("SIZE OU", response.taskList.toString());
                            requestCallback.requestSuccess(response.taskList);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tesww", String.valueOf(anError.getErrorCode()));
                        Log.e("teswwwww", "TES " + anError.getErrorBody());
                        Log.e("teswwww", "TES " + anError.getErrorDetail());
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}