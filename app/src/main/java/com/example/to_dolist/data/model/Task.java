package com.example.to_dolist.data.model;

import android.os.Parcelable;

import com.example.to_dolist.base.BaseModel;

import java.io.Serializable;

public class Task extends BaseModel{
    private int id;
    private String title;
    private String description;
    private String due_date;
    private String checked;

    public Task(int id, String title, String description, String due_date, String checked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
