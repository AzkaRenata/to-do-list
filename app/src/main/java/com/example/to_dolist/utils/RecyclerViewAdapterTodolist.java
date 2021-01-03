package com.example.to_dolist.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.to_dolist.R;
import com.example.to_dolist.data.model.Task;

public class RecyclerViewAdapterTodolist extends RecyclerView.Adapter<RecyclerViewAdapterTodolist.MyViewHolder> {
    private static List<Task> mDataset;
    private static MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle;
        TextView tvDescription;
        CheckBox cbCheck;

        public MyViewHolder(View itemView) {
            super(itemView);
            cbCheck = itemView.findViewById(R.id.task_check_cv);
            tvTitle = itemView.findViewById(R.id.task_title_cv);
            tvDescription = itemView.findViewById(R.id.task_description_cv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterTodolist(List<Task> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_item_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(mDataset.get(position).getTitle());
        holder.tvDescription.setText(mDataset.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if(mDataset != null){
            return mDataset.size();
        }else{
            return 0;
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}