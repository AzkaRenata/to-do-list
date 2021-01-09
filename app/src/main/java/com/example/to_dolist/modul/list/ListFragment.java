package com.example.to_dolist.modul.list;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.example.to_dolist.modul.detail.DetailActivity;
import com.example.to_dolist.modul.edit.EditActivity;
import com.example.to_dolist.modul.edit.EditResponse;
import com.example.to_dolist.modul.login.LoginActivity;
import com.example.to_dolist.modul.login.LoginResponse;
import com.example.to_dolist.utils.RecyclerViewAdapterTodolist;
import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.TaskSharedUtil;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UserSharedUtil;
import com.example.to_dolist.utils.UtilProvider;
import com.example.to_dolist.utils.myURL;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment<ListActivity, ListContract.Presenter> implements ListContract.View {
    TextView tvTodo;
    TextView tvFinished;
    View viewTodo;
    View viewFinished;
    ImageButton btLogout;
    boolean onTodo;
    FloatingActionButton fabAdd;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RelativeLayout rlNoTodo;
    RelativeLayout rlNoFinished;
    TokenSharedUtil tokenSharedUtil;
    TaskSharedUtil taskSharedUtil;
    UserSharedUtil userSharedUtil;

    public ListFragment(TokenSharedUtil tokenSharedUtil, ImageButton btLogout) {
        this.tokenSharedUtil = tokenSharedUtil;
        this.taskSharedUtil = UtilProvider.getTaskSharedUtil();
        this.userSharedUtil = UtilProvider.getUserSharedUtil();
        this.btLogout = btLogout;
        onTodo = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false);
        mPresenter = new ListPresenter(this, activity);
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewTodolist);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        fabAdd = fragmentView.findViewById(R.id.task_list_add_fab);
        tvTodo = fragmentView.findViewById(R.id.task_list_todo_tv);
        tvFinished = fragmentView.findViewById(R.id.task_list_finished_tv);
        viewTodo = fragmentView.findViewById(R.id.task_list_todo_view);
        viewFinished = fragmentView.findViewById(R.id.task_list_finished_view);
        rlNoTodo = fragmentView.findViewById(R.id.no_todo_layout);
        rlNoFinished = fragmentView.findViewById(R.id.no_finished_layout);
        setTvTodoClick();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFabAddClick();
            }
        });

        tvTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTvTodoClick();
            }
        });

        tvFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTvFinishedClick();
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtLogoutClick();
            }
        });

        mPresenter.getTaskList();

        setTitle("My To-do List");

        return fragmentView;
    }

    public void checkResult(){
        if(mAdapter.getItemCount() == 0){
            if(onTodo){
                rlNoFinished.setVisibility(View.GONE);
                rlNoTodo.setVisibility(View.VISIBLE);
            }else{
                rlNoTodo.setVisibility(View.GONE);
                rlNoFinished.setVisibility(View.VISIBLE);
            }
        }else{
            rlNoTodo.setVisibility(View.GONE);
            rlNoFinished.setVisibility(View.GONE);
        }
    }

    public void setBtLogoutClick(){
        tokenSharedUtil.clear();
        userSharedUtil.clear();
        taskSharedUtil.clear();
        Toast.makeText(getContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
        mPresenter.performLogout();
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    public void setFabAddClick(){
        mPresenter.moveToAdd();
    }

    public void setTvTodoClick(){
        tvTodo.setTextColor(getResources().getColor(R.color.customPrimary));
        tvFinished.setTextColor(getResources().getColor(R.color.customText));
        viewTodo.setVisibility(View.VISIBLE);
        viewFinished.setVisibility(View.INVISIBLE);
        onTodo = true;
        setList();
        checkResult();
    }

    public void setTvFinishedClick(){
        tvFinished.setTextColor(getResources().getColor(R.color.customPrimary));
        tvTodo.setTextColor(getResources().getColor(R.color.customText));
        viewFinished.setVisibility(View.VISIBLE);
        viewTodo.setVisibility(View.INVISIBLE);
        onTodo = false;
        setList();
        checkResult();
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToAdd() {
        Intent intent = new Intent(activity, AddActivity.class);
        startActivity(intent);
    }

    @Override
    public void setList() {
        final List<Task> tempList = taskSharedUtil.getTask();
        final List<Task> taskList = new ArrayList<>();

        if(tempList != null){
            for(Task temp : tempList){
                if(onTodo){
                    if(!temp.isChecked()){
                        taskList.add(temp);
                    }
                }else{
                    if(temp.isChecked()){
                        taskList.add(temp);
                    }
                }
            }
        }

        mAdapter = new RecyclerViewAdapterTodolist(taskList, this);
        mRecyclerView.setAdapter(mAdapter);

        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id = taskList.get(position).getId();
                moveToDetail(id);
            }
        });
    }

    public void moveToDetail(int id){
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void saveTask(List<Task> task){
        taskSharedUtil.setTask(task);

        List<Task> test = taskSharedUtil.getTask();
    }

    public void getList(final RequestCallback<List<Task>> requestCallback) {
        AndroidNetworking.get(myURL.GET_TASK_LIST_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(ListResponse.class, new ParsedRequestListener<ListResponse>() {
                    @Override
                    public void onResponse(ListResponse response) {
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

    @Override
    public void checkTask(String isChecked, int id) {
        mPresenter.performChecked(isChecked, id);
    }

    @Override
    public void requestCheckedChange(String isChecked, int id, final RequestCallback<ListResponse> requestCallback) {
        AndroidNetworking.post(myURL.UPDATE_CHECKED_TASK_URL + id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addBodyParameter("checked", isChecked)
                .build()
                .getAsObject(ListResponse.class, new ParsedRequestListener<ListResponse>() {
                    @Override
                    public void onResponse(ListResponse response) {
                        if (response == null) {
                            Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
                            requestCallback.requestFailed("Null Response");
                        }else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tesww", String.valueOf(anError.getErrorCode()));
                        Log.e("teswwwww", "fdfd" + anError.getErrorBody());
                        Log.e("teswwww", "fdfdsasa" + anError.getErrorDetail());
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        requestCallback.requestFailed("Something is Wrong");
                    }
                });
    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}