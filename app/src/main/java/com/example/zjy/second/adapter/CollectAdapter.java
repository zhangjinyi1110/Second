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
import com.example.zjy.second.GoodsActivity;
import com.example.zjy.second.MainActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.CollectFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by ZJY on 2017/11/30.
 */

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder> {
    private List<GoodsBean> list;
    private Context context;
    private CollectFragmentPresenter presenter;
    private boolean flag;
    private final int TYPE_NORMAL = 0;
    private final int TYPE_FOOTER = 1;

    public CollectAdapter(List<GoodsBean> list, Context context, boolean flag, CollectFragmentPresenter presenter) {
        this.list = list;
        this.context = context;
        this.flag = flag;
        this.presenter = presenter;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            CollectAdapter.ViewHolder holder = new CollectAdapter.ViewHolder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collect_recycler_item, parent, false);
            CollectAdapter.ViewHolder holder = new CollectAdapter.ViewHolder(view);
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
                    presenter.unsetCollects((String)SharedPreferencesUtils.getParam(context,"token",""),list.get(position).getId(), position);
                }
            });
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GoodsActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    ((MainActivity)context).startActivityForResult(intent,MainActivity.PUBLISH);
                }
            });
            if (flag) {
                holder.remove.setVisibility(View.VISIBLE);
            } else {
                holder.remove.setVisibility(View.GONE);
            }
        }
    }

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
            goodsContext = (TextView) view.findViewById(R.id.collect_goods_context);
            goodsName = (TextView) view.findViewById(R.id.collect_goods_name);
            goodsImage = (ImageView) view.findViewById(R.id.collect_goods_image);
            removeBtn = (Button) view.findViewById(R.id.collect_remove_btn);
            remove = (View) view.findViewById(R.id.collect_remove);
        }
    }
}
