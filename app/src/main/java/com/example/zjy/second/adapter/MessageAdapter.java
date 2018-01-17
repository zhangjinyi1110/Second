package com.example.zjy.second.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zjy.second.GoodsActivity;
import com.example.zjy.second.PublishActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.bean.CommentBean;

import java.util.List;

/**
 * Created by ZJY on 2017/11/30.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<CommentBean> list;
    private Context context;

    public MessageAdapter(List<CommentBean> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_recycler_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getUser().getName());
        holder.text.setText(list.get(position).getContent());
        holder.date.setText(list.get(position).getCreate_time());
        holder.look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = list.get(position).getGoods_id();
                int commentId = list.get(position).getId();
                Intent intent = new Intent(context, GoodsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("commentId",commentId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView text;
        TextView look;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.message_user_name);
            text = (TextView)itemView.findViewById(R.id.message_text);
            look = (TextView)itemView.findViewById(R.id.message_look);
            date = (TextView)itemView.findViewById(R.id.message_date);
        }
    }
}
