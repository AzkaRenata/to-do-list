package com.example.to_dolist.data.model;

import android.os.Parcelable;

import com.example.to_dolist.base.BaseModel;

import java.io.Serializable;

public class Task extends BaseModel{
    private int id;
    private String title;
    private String description;
    private String due_date;
    private boolean checked;
    private String created_at;

    public Task(int id, String title, String description, String due_date, boolean checked, String created_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.checked = checked;
        this.created_at = created_at;
    }

    public Task(String title, String description, String due_date) {
        this.title = title;
        this.description = description;
        this.due_date = due_date;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
