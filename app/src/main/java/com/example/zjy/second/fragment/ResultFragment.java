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

import com.example.zjy.second.R;
import com.example.zjy.second.adapter.ResultAdapter;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.presenter.ResultFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.ResultFragmentView;

import java.util.ArrayList;
import java.util.List;

public class ResultFragment extends Fragment implements ResultFragmentView {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ResultAdapter adapter;
    private List<SearchNameBean> list;
    private ResultFragmentPresenter presenter;
    private ResultFragmentBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_result, container, false);
        iniView();
        broadcastReceiver = new ResultFragmentBroadcastReceiver();
        intentFilter = new IntentFilter("android.intent.action.RESULT");
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
        presenter = new ResultFragmentPresenter(this);
        String token = (String) SharedPreferencesUtils.getParam(getContext(),"token","");
        String title = (String) SharedPreferencesUtils.getParam(getContext(),"history0","");
        presenter.getResult(token,title);
        Log.i("ResultFragment","onCreateView");
        return view;
    }

    private void iniView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.result_recycler);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new ResultAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setDate(List<SearchNameBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public class ResultFragmentBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("ResultFragment","onReceive");
            String token = (String) SharedPreferencesUtils.getParam(getContext(),"token","");
            String title = (String) SharedPreferencesUtils.getParam(getContext(),"history0","");
            presenter.getResult(token,title);
        }
    }
}
