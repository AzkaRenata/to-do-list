package com.example.to_dolist.modul.edit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.to_dolist.R;
import com.example.to_dolist.base.BaseFragment;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.list.ListActivity;
import com.example.to_dolist.modul.login.LoginResponse;
import com.example.to_dolist.utils.RequestCallback;
import com.example.to_dolist.utils.TaskSharedUtil;
import com.example.to_dolist.utils.TokenSharedUtil;
import com.example.to_dolist.utils.UtilProvider;
import com.example.to_dolist.utils.myURL;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditFragment extends BaseFragment<EditActivity, EditContract.Presenter> implements EditContract.View {
    EditText etTaskTitle;
    EditText etTaskDescription;
    TextView tvDate;
    TextView tvTime;
    String strDate;
    String strTime;
    Button btnSave;

    Task task;
    ArrayList<Task> taskList;
    int id;
    TaskSharedUtil taskSharedUtil;
    TokenSharedUtil tokenSharedUtil;


    public EditFragment(int id) {
        this.id = id;
        taskSharedUtil = UtilProvider.getTaskSharedUtil();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_add, container, false);
        mPresenter = new EditPresenter(this, activity);
        mPresenter.start();

        etTaskTitle = fragmentView.findViewById(R.id.task_add_title);
        etTaskDescription = fragmentView.findViewById(R.id.task_add_description);
        tvDate = fragmentView.findViewById(R.id.task_add_date_tv);
        tvTime = fragmentView.findViewById(R.id.task_add_time_tv);
        btnSave = fragmentView.findViewById(R.id.task_add_add_btn);
        btnSave.setText("Save");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnSaveClick();
            }
        });

        initCalendar();
        showData();

        setTitle("Edit Task");

        return fragmentView;
    }

    public void initCalendar(){
        setDateCalendar();
        setTimeCalendar();
    }

    public void setDateCalendar() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;

                        strDate = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
                        tvDate.setText(strDate);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void setTimeCalendar() {
        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR);
        final int minute = calendar.get(Calendar.MINUTE);

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        strTime = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
                        tvTime.setText(strTime);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
    }

    public void setBtnSaveClick(){
        String dateTime = strDate + " " + strTime;

        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();

        Task newTask = new Task(title, description, dateTime);
        mPresenter.saveData(newTask);
    }

    public void requestEdit(Task newTask, final RequestCallback<EditResponse> requestCallback) {
        Log.e("tes", "tes");
        Log.e("tess", "ajez@gmail.com");
        Log.e("tess", "ajez123");
        AndroidNetworking.post(myURL.UPDATE_TASK_URL + id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addBodyParameter("task_title", newTask.getTitle())
                .addBodyParameter("task_description", newTask.getDescription())
                .addBodyParameter("due_date", newTask.getDue_date())
                .build()
                .getAsObject(EditResponse.class, new ParsedRequestListener<EditResponse>() {
                    @Override
                    public void onResponse(EditResponse response) {
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

    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        Intent intent = new Intent(activity, ListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showData() {
        List<Task> taskList = taskSharedUtil.getTask();
        task = null;

        for(Task temp : taskList){
            if(temp.getId() == id){
                task = temp;
                break;
            }
        }

        String datetime = task.getDue_date();
        String[] tmp = datetime.split(" ");
        strDate = tmp[0];
        strTime = tmp[1];

        etTaskTitle.setText(task.getTitle());
        etTaskDescription.setText(task.getDescription());
        tvDate.setText(strDate);
        tvTime.setText(strTime);
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    public void showFailedMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Task Edited", Toast.LENGTH_SHORT).show();
    }
}
