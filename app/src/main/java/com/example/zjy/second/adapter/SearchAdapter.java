package com.example.zjy.second.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjy.second.MainActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.application.MyApplication;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by ZJY on 2017/11/24.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchNameBean> list;
    private Context context;

    public SearchAdapter(List<SearchNameBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        if(list.get(position).isHistory()){
//            holder.image.setImageResource(R.drawable.history);
//        }else{
//            holder.image.setImageResource(R.drawable.search);
//        }
        holder.image.setImageResource(R.drawable.search);
        holder.text.setText(list.get(position).getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDate(list.get(position).getTitle());
                Intent intent = new Intent("android.intent.action.SEARCH_RESULT");
                intent.putExtra("title", list.get(position).getTitle());
                context.sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            image = (ImageView) view.findViewById(R.id.search_item_image);
            text = (TextView) view.findViewById(R.id.search_item_text);
        }
    }

    private void upDate(String title) {
        int num = (int) SharedPreferencesUtils.getParam(context, "historyNum", 0);
        for (int i = 0; i < ((num>10)?10:num); i++) {
            String string = (String) SharedPreferencesUtils.getParam(context, "history" + i, "");
            if (string.equals(title)) {
                for (int j = i; j > 0; j--) {
                    String str = (String) SharedPreferencesUtils.getParam(context, "history" + (j - 1), "");
                    SharedPreferencesUtils.setParam(context, "history" + j, str);
                }
                SharedPreferencesUtils.setParam(context,"history0",title);
                return;
            }
        }
        if(num<=9){
            SharedPreferencesUtils.setParam(context,"history"+num,title);
            int sum = num+1;
            SharedPreferencesUtils.setParam(context,"historyNum",sum);
        }else{
            for(int i = 1 ; i<10 ; i++){
                String string = (String)SharedPreferencesUtils.getParam(context, "history" + (i - 1), "");
                SharedPreferencesUtils.setParam(context, "history" + i, string);
            }
            SharedPreferencesUtils.setParam(context, "history0", title);
        }
//        if((int)SharedPreferencesUtils.getParam(context,"historyNum",0)<=10){
//            int num = (int)SharedPreferencesUtils.getParam(context,"historyNum",0);
//            SharedPreferencesUtils.setParam(context,"history"+num,title);
//            int sum = num+1;
//            SharedPreferencesUtils.setParam(context,"historyNum",sum);
//        }else{
//            for(int i = 1 ; i<10 ; i++){
//                String string = (String)SharedPreferencesUtils.getParam(context,"history"+(i-1),"");
//                SharedPreferencesUtils.setParam(context,"history"+i,string);
//            }
//            SharedPreferencesUtils.setParam(context,"history0",title);
//        }
    }
}
