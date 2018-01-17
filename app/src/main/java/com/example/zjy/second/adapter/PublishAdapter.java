package com.example.zjy.second.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zjy.second.PublishActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.PublishFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by ZJY on 2017/11/29.
 */

public class PublishAdapter extends RecyclerView.Adapter<PublishAdapter.ViewHolder> {
    private List<GoodsBean> list;
    private Context context;
    private boolean flag;
    private PublishFragmentPresenter publishFragmentPresenter;

    private final int TYPE_NORMAL = 0;
    private final int TYPE_FOOTER = 1;

    public PublishAdapter(List<GoodsBean> list, Context context, boolean flag,PublishFragmentPresenter publishFragmentPresenter) {
        this.list = list;
        this.context = context;
        this.flag = flag;
        this.publishFragmentPresenter = publishFragmentPresenter;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        if (position == list.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publish_recycler_item,parent,false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publish_recycler_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            Glide.with(context).load(list.get(position).getUrl().get(0)).into(holder.goodsImage);
//            holder.goodsImage.setImageResource(list.get(position).getImage());
            holder.goodsName.setText(list.get(position).getTitle());
            holder.goodsContext.setText(list.get(position).getDescription());
            holder.removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    publishFragmentPresenter.remove((String) SharedPreferencesUtils.getParam(context,"token",""),list.get(position).getId(),position);
//                    list.remove(position);
//                    notifyDataSetChanged();
                    Log.i("PublishAdapter", "remove");
                }
            });
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("PublishAdapter", "go_to_goods");
                    Intent intent = new Intent(context, PublishActivity.class);
                    context.startActivity(intent);
                }
            });
            if (flag) {
                holder.remove.setVisibility(View.VISIBLE);
            } else {
                holder.remove.setVisibility(View.GONE);
            }
        }
    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.goodsImage.setImageResource(list.get(position).getImage());
//        holder.goodsName.setText(list.get(position).getTitle());
//        holder.goodsContext.setText(list.get(position).getDescription());
//        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("PublishAdapter", "remove");
//            }
//        });
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("PublishAdapter", "go_to_goods");
//            }
//        });
//        if (flag) {
//            holder.remove.setVisibility(View.VISIBLE);
//        } else {
//            holder.remove.setVisibility(View.GONE);
//        }
//    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImage;
        TextView goodsName;
        TextView goodsContext;
        View view;
        View remove;
        Button removeBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            goodsContext = (TextView) view.findViewById(R.id.publish_goods_context);
            goodsName = (TextView) view.findViewById(R.id.publish_goods_name);
            goodsImage = (ImageView) view.findViewById(R.id.publish_goods_image);
            removeBtn = (Button) view.findViewById(R.id.publish_remove_btn);
            remove = (View) view.findViewById(R.id.publish_remove);
        }
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == list.size()) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }
}
