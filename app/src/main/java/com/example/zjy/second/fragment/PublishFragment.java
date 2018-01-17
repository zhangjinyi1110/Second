package com.example.zjy.second.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zjy.second.R;
import com.example.zjy.second.adapter.PublishAdapter;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.PublishFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.PublishFragmentView;

import java.util.ArrayList;
import java.util.List;

public class PublishFragment extends Fragment implements PublishFragmentView {
    private View view;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager manager;
    private PublishAdapter adapter;
    private List<GoodsBean> list;
    private PublishBroadcastReceiver broadcastReceiver;
    private PublishFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_publish, container, false);
        presenter = new PublishFragmentPresenter(this);
        initView();
        broadcastReceiver = new PublishBroadcastReceiver();
        presenter.getGoods((String) SharedPreferencesUtils.getParam(getContext(),"token",""));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PUBLISH_REMOVE");
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
        return view;
    }

    private void initView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.publish_recycler);
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new PublishAdapter(list,getContext(),false,presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setGoods(List<GoodsBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void removeSuccess(String msg,int position) {
        list.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeFailure(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public class PublishBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("action").equals("ok")){
                adapter.setFlag(false);
            }else{
                adapter.setFlag(true);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
