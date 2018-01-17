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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zjy.second.ClassifyActivity;
import com.example.zjy.second.GoodsActivity;
import com.example.zjy.second.MainActivity;
import com.example.zjy.second.PublishActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.ClassifyFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by ZJY on 2017/11/24.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ViewHolder> {
    private List<GoodsBean> list;
    private Context context;
    private ClassifyFragmentPresenter presenter;

    public ClassifyAdapter(List<GoodsBean> list, Context context,ClassifyFragmentPresenter presenter) {
        this.list = list;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recycler_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getUser().getPic()).into(holder.userImage);
//        holder.userImage.setImageResource(list.get(position).getUser().getId());
        holder.userName.setText(list.get(position).getUser().getName());
        holder.goodsMoney.setText(" ￥"+list.get(position).getPrice()+" ");
        Glide.with(context).load(list.get(position).getUrl().get(0)).into(holder.goodsImage);
//        holder.goodsImage.setImageResource(list.get(position).getGoodsBean().getImage());
        holder.goodsName.setText(list.get(position).getTitle());
        holder.browseNum.setText(list.get(position).getClick()+"");
        holder.enshrineNum.setText(list.get(position).getCollect_num()+"");
        holder.enshrineImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferencesUtils.getParam(context,"token","").equals("")){
                    Toast.makeText(context,"请先登录",Toast.LENGTH_SHORT);
                }else{
                    presenter.collect((String) SharedPreferencesUtils.getParam(context,"token",""),list.get(position).getId());
                }
            }
        });
        holder.commentNum.setText(list.get(position).getComment_num()+"");
        holder.commentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ClassifyAdapter","commentImage");
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GoodsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                ((MainActivity)context).startActivityForResult(intent,MainActivity.PUBLISH);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;
        TextView goodsMoney;
        ImageView goodsImage;
        TextView goodsName;
        ImageView browseImage;
        TextView browseNum;
        ImageView enshrineImage;
        TextView enshrineNum;
        ImageView commentImage;
        TextView commentNum;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            userImage = (ImageView)itemView.findViewById(R.id.main_card_user_image);
            userName = (TextView) itemView.findViewById(R.id.main_card_user_name);
            goodsMoney = (TextView)itemView.findViewById(R.id.main_card_goods_money);
            goodsImage = (ImageView)itemView.findViewById(R.id.main_card_goods_image);
            goodsName = (TextView)itemView.findViewById(R.id.main_card_goods_name);
            browseImage = (ImageView)itemView.findViewById(R.id.main_card_browse);
            browseNum = (TextView)itemView.findViewById(R.id.main_card_browse_num);
            enshrineImage = (ImageView)itemView.findViewById(R.id.main_card_enshrine);
            enshrineNum = (TextView)itemView.findViewById(R.id.main_card_enshrine_num);
            commentImage = (ImageView)itemView.findViewById(R.id.main_card_comment);
            commentNum = (TextView)itemView.findViewById(R.id.main_card_comment_num);
        }
    }
}
