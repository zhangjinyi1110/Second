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

import com.bumptech.glide.Glide;
import com.example.zjy.second.GoodsActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.SearchActivity;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.SearchNameBean;

import java.util.List;

/**
 * Created by ZJY on 2017/11/24.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private List<SearchNameBean> list;
    private Context context;

    public ResultAdapter(List<SearchNameBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recycler_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getUrl().get(0)).into(holder.goodsImage);
//        holder.goodsImage.setImageResource(R.drawable.image);
        holder.goodsName.setText(list.get(position).getTitle());
        holder.goodsMoney.setText("￥"+list.get(position).getPrice());
        holder.goodsContext.setText(list.get(position).getDescription());
        holder.browseNum.setText(""+list.get(position).getClick());
        holder.enshrineNum.setText(""+list.get(position).getClick());
        holder.commentNum.setText(""+list.get(position).getTell());
        holder.browse.setImageResource(R.drawable.browse);
        holder.enshrine.setImageResource(R.drawable.enshrine);
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ResultAdapter","comment");
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GoodsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                ((SearchActivity)context).startActivityForResult(intent,100);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImage;
        TextView goodsName;
        TextView goodsMoney;
        TextView goodsContext;
        ImageView browse;
        TextView browseNum;
        ImageView enshrine;
        TextView enshrineNum;
        ImageView comment;
        TextView commentNum;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            goodsImage = (ImageView)view.findViewById(R.id.result_goods_image);
            goodsName = (TextView)view.findViewById(R.id.result_goods_name);
            goodsMoney = (TextView)view.findViewById(R.id.result_goods_money);
            goodsContext = (TextView)view.findViewById(R.id.result_goods_context);
            browse = (ImageView)view.findViewById(R.id.search_card_browse);
            browseNum = (TextView)view.findViewById(R.id.search_card_browse_num);
            enshrine = (ImageView)view.findViewById(R.id.search_card_enshrine);
            enshrineNum = (TextView)view.findViewById(R.id.search_card_enshrine_num);
            comment = (ImageView)view.findViewById(R.id.search_card_comment);
            commentNum = (TextView)view.findViewById(R.id.search_card_comment_num);
        }
    }
}
