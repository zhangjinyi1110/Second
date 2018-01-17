package com.example.zjy.second.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zjy.second.R;
import com.example.zjy.second.adapter.ResultAdapter;
import com.example.zjy.second.adapter.SearchAdapter;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.presenter.SearchItemPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.SearchItemView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchItemFragment extends Fragment implements SearchItemView {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private SearchAdapter adapter;
    private List<SearchNameBean> list;
    private SearchItemPresenter presenter;
    private SearchItemBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_item, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.search_recycler_view);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        list = history();
        adapter = new SearchAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
        presenter = new SearchItemPresenter(this);
        broadcastReceiver = new SearchItemBroadcastReceiver();
        intentFilter = new IntentFilter("android.intent.action.SEARCH_TEXT");
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
        return view;
    }

    @Override
    public void setDate(List<SearchNameBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public class SearchItemBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String token = (String) SharedPreferencesUtils.getParam(getContext(),"token","");
            String title = intent.getStringExtra("title");
            if(title.equals("")){
                list.clear();
                list.addAll(history());
            }else{
                presenter.search(token,title);
            }
        }
    }

    private List<SearchNameBean> history() {
        List<SearchNameBean> list = new ArrayList<>();
        int num = (int) SharedPreferencesUtils.getParam(getContext(),"historyNum",0);
        for(int i = 0 ; i<((num>10)?10:num) ; i++){
            SearchNameBean search = new SearchNameBean();
            search.setTitle((String) SharedPreferencesUtils.getParam(getContext(),"history"+i,""));
            list.add(search);
        }
        Log.i("SearchItem",list.size()+"+++");
        return list;
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
