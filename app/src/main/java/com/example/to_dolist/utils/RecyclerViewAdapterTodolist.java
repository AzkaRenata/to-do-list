package com.example.to_dolist.utils;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.to_dolist.R;
import com.example.to_dolist.data.model.Task;
import com.example.to_dolist.modul.list.ListContract;
import com.example.to_dolist.modul.list.ListFragment;

public class RecyclerViewAdapterTodolist extends RecyclerView.Adapter<RecyclerViewAdapterTodolist.MyViewHolder> {
    private static List<Task> mDataset;
    private static MyClickListener myClickListener;
    private static ListContract.View view;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle;
        TextView tvDate;
        CheckBox cbCheck;

        public MyViewHolder(View itemView) {
            super(itemView);
            cbCheck = itemView.findViewById(R.id.task_check_cv);
            tvTitle = itemView.findViewById(R.id.task_title_cv);
            tvDate = itemView.findViewById(R.id.task_description_cv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterTodolist(List<Task> myDataset, ListContract.View view) {
        mDataset = myDataset;
        this.view = view;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_item_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvTitle.setText(mDataset.get(position).getTitle());
        holder.tvDate.setText("Due : " + mDataset.get(position).getDue_date());
        if(mDataset.get(position).isChecked()){
            holder.cbCheck.setChecked(true);
            holder.tvTitle.setPaintFlags(holder.tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvDate.setPaintFlags(holder.tvDate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.cbCheck.setChecked(false);
        }

        holder.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("tes", mDataset.get(position).getTitle());

                if(isChecked){
                    view.checkTask("true", mDataset.get(position).getId());
                }else{
                    view.checkTask("false", mDataset.get(position).getId());
                }

            }
        });
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