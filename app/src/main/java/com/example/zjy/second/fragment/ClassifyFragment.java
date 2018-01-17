package com.example.zjy.second.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zjy.second.R;
import com.example.zjy.second.adapter.ClassifyAdapter;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.ClassifyFragmentPresenter;
import com.example.zjy.second.view.ClassifyFragmentView;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends Fragment implements ClassifyFragmentView {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ClassifyAdapter adapter;
    private List<GoodsBean> list;
    private int id;
    private ClassifyFragmentPresenter presenter;
    private ClassifyFragmentBroadcastReceiver broadcastReceiver;

    public ClassifyFragment(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_classify, container, false);
        presenter = new ClassifyFragmentPresenter(this);
        initView();
        broadcastReceiver = new ClassifyFragmentBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.CLASSIFY");
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
        presenter.getGoods(id);
        return view;
    }

    private void initView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.classify_recycler);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new ClassifyAdapter(list,getContext(),presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setGoods(List<GoodsBean> list) {
        this.list.clear();
        this.list.addAll(list);
//        Log.i("ClassifyFragment","setGoods++++++++");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void collectSuccess(String msg) {
        presenter.getGoods(id);
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collectFailure(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public class ClassifyFragmentBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            presenter.getGoods(id);
        }
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
