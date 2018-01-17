package com.example.zjy.second.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zjy.second.ClassifyActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.bean.CategoryBean;

import java.util.List;

/**
 * Created by ZJY on 2017/12/12.
 */

public class ClassifyCAdapter extends RecyclerView.Adapter<ClassifyCAdapter.ViewHolder> {
    private List<CategoryBean> list;
    private Context context;

    public ClassifyCAdapter(List<CategoryBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recycler_view_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("classify",list.get(position).getName());
                intent.putExtra("id",list.get(position).getId());
                ((ClassifyActivity)context).setResult(Activity.RESULT_OK,intent);
                ((ClassifyActivity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textView = (TextView)view.findViewById(R.id.classify_recycler_view_text);
        }
    }
}
