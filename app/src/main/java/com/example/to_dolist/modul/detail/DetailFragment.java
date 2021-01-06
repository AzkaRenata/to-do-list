package com.example.to_dolist.modul.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.edit.EditActivity;
import com.example.to_dolist.modul.list.ListActivity;
import com.example.to_dolist.modul.list.ListResponse;
import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.TaskSharedUtil;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UtilProvider;
import com.example.to_dolist.utils.myURL;

import java.util.List;

public class DetailFragment extends BaseFragment<DetailActivity, DetailContract.Presenter> implements DetailContract.View {
    TextView tvTitle;
    TextView tvDescription;
    TextView tvDueDate;
    CheckBox cbCheck;
    CheckBox cbAlarm;
    ImageButton ibEdit;
    ImageButton btShare;
    ImageButton btDelete;
    TextView tvCreatedAt;

    int id;
    TokenSharedUtil tokenSharedUtil;
    TaskSharedUtil taskSharedUtil;

    public DetailFragment(int id, ImageButton btShare, ImageButton btDelete) {
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        taskSharedUtil = UtilProvider.getTaskSharedUtil();
        this.id = id;
        this.btShare = btShare;
        this.btDelete = btDelete;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_detail, container, false);
        mPresenter = new DetailPresenter(this, activity);
        mPresenter.start();

        tvTitle = fragmentView.findViewById(R.id.task_title_detail);
        tvDescription = fragmentView.findViewById(R.id.task_description_detail);
        tvDueDate = fragmentView.findViewById(R.id.task_detail_date_tv);
        cbCheck = fragmentView.findViewById(R.id.task_detail_check_cb);
        cbAlarm = fragmentView.findViewById(R.id.task_detail_alarm_cb);
        ibEdit = fragmentView.findViewById(R.id.task_detail_edit_ib);
        tvCreatedAt = fragmentView.findViewById(R.id.task_detail_created_tv);

        ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToEdit();
            }
        });

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareClick();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClick();
            }
        });



        mPresenter.getTaskDetail();

        setTitle("My To-do List");

        return fragmentView;
    }

    public void onShareClick(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        String text;

        text = "*" + tvTitle.getText().toString() + "*" + "\n\n"
                + tvDescription.getText().toString() + "\n\n"
                + "Due : " + tvDueDate.getText().toString();


        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void onDeleteClick(){
        showDeleteAlert();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void editTask(int id) {
        Intent intent = new Intent(activity, EditActivity.class);
        Bundle args = new Bundle();
        intent.putExtra("taskList", args);
        intent.putExtra("id", id);

        startActivity(intent);
    }

    public void showDeleteAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Delete Task");
        builder.setMessage("Delete this Task ?");
        builder.setNegativeButton(Html.fromHtml("<font color='#eb5334'>No</font>"), null);
        builder.setPositiveButton(Html.fromHtml("<font color='#20a860'>Yes</font>"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                mPresenter.performDelete();
            }
        });
        builder.create();
        builder.show();
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

    public void moveToEdit(){
        Intent intent = new Intent(activity, EditActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void setTaskDetail() {
        List<Task> taskList = taskSharedUtil.getTask();
        Task task = null;

        for(Task temp : taskList){
            if(temp.getId() == id){
                task = temp;
                break;
            }
        }

        tvTitle.setText(task.getTitle());
        tvDescription.setText(task.getDescription());
        tvDueDate.setText(task.getDue_date());
        tvCreatedAt.setText(task.getCreated_at());

        if(task.isChecked()){
            cbCheck.setChecked(true);
            tvTitle.setPaintFlags(tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvDueDate.setPaintFlags(tvDueDate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            cbCheck.setChecked(false);
        }

        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mPresenter.performChecked("true");
                }else{
                    mPresenter.performChecked("false");
                }
            }
        });

    }

    public void requestDelete(final RequestCallback<DetailResponse> callback){
        AndroidNetworking.delete(myURL.DELETE_TASK_URL + id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .build()
                .getAsObject(DetailResponse.class, new ParsedRequestListener<DetailResponse>() {
                    @Override
                    public void onResponse(DetailResponse response) {
                        callback.requestSuccess(response);
                    }
                    @Override
                    public void onError(ANError anError) {
                        callback.requestFailed("Something is Wrong");
                    }
                });

    }

    @Override
    public void requestCheckedChange(String isChecked, final RequestCallback<DetailResponse> requestCallback) {
        AndroidNetworking.post(myURL.UPDATE_CHECKED_TASK_URL + id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addBodyParameter("checked", isChecked)
                .build()
                .getAsObject(DetailResponse.class, new ParsedRequestListener<DetailResponse>() {
                    @Override
                    public void onResponse(DetailResponse response) {
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

    public void redirectToTaskList() {
        Intent intent = new Intent(activity, ListActivity.class);
        startActivity(intent);
    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Task Deleted", Toast.LENGTH_SHORT).show();
    }
}