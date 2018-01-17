package com.example.zjy.second.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zjy.second.R;
import com.example.zjy.second.adapter.CollectAdapter;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.CollectFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.CollectFragmentView;

import java.util.ArrayList;
import java.util.List;

public class CollectFragment extends Fragment implements CollectFragmentView {
    private View view;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager manager;
    private CollectAdapter adapter;
    private List<GoodsBean> list;
    private CollectBroadcastReceiver broadcastReceiver;
    private CollectFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_collect, container, false);
        presenter = new CollectFragmentPresenter(this);
        initView();
        presenter.getGoods((String) SharedPreferencesUtils.getParam(getContext(),"token",""));
        broadcastReceiver = new CollectBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.COLLECT_REMOVE");
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
        return view;
    }

    private void initView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.collect_recycler);
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new CollectAdapter(list,getContext(),false,presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setGoods(List<GoodsBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void success(String msg,int position) {
        list.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public class CollectBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("action").equals("ok")){
                adapter.setFlag(false);
            }else if(intent.getStringExtra("action").equals("update")){
                presenter.getGoods((String) SharedPreferencesUtils.getParam(getContext(),"token",""));
            }else{
                adapter.setFlag(true);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
