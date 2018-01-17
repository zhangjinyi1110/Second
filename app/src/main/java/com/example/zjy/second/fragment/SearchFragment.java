package com.example.zjy.second.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.zjy.second.R;
import com.example.zjy.second.adapter.SearchAdapter;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.presenter.SearchFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.SearchFragmentView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchFragmentView {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private SearchAdapter adapter;
    private List<SearchNameBean> list;
    private TextChangeBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private SearchFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        initView();
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TEXT_CHANGE");
        broadcastReceiver = new TextChangeBroadcastReceiver();
        getActivity().registerReceiver(broadcastReceiver,intentFilter);
        Log.i("SearchFragment","onCreateView");
        presenter = new SearchFragmentPresenter(this);
        return view;
    }

    private void initView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.search_recycler);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        list = history();
        adapter = new SearchAdapter(list,getActivity());
        recyclerView.setAdapter(adapter);
    }

    private List<SearchNameBean> history(){
        List<SearchNameBean> list = new ArrayList<>();
        for(int i = 0 ; i<10 ; i++){
//            SearchNameBean searchNameBean = new SearchNameBean(true,"SearchNameBean"+i);
//            list.add(searchNameBean);
//            int sum = ((int)SharedPreferencesUtils.getParam(getContext(),"historyNum",0));
            SharedPreferencesUtils.setParam(getContext(),"history"+i,"history"+i);
            int num = 1+((int)SharedPreferencesUtils.getParam(getContext(),"historyNum",0));
            SharedPreferencesUtils.setParam(getContext(),"historyNum",num);
            Log.i("SearchFragment",num+"+++");
        }
        for(int i = 0 ; i<((int)SharedPreferencesUtils.getParam(getContext(),"historyNum",0)) ; i++){
            SearchNameBean goodsBean = new SearchNameBean();
            goodsBean.setTitle((String)SharedPreferencesUtils.getParam(getContext(),"history"+i,""));
            list.add(goodsBean);
            Log.i("SearchFragment",((int)SharedPreferencesUtils.getParam(getContext(),"historyNum",0))+"+++"+goodsBean.getTitle());
        }
        return list;
    }

    @Override
    public void searchResult(List<SearchNameBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public class TextChangeBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("text");
//            list.clear();
            if(text.equals("history")){
                list.addAll(history());
                adapter.notifyDataSetChanged();
                Log.i("SearchFragment","++++++++");
                return;
            }
            presenter.setSearchTitle((String) SharedPreferencesUtils.getParam(getContext(),"token",""),text);
//            for(int i = 0 ; i<5 ; i++){
//                SearchNameBean searchNameBean = new SearchNameBean((i%2)==0?false:true,text+i);
//                list.add(searchNameBean);
//            }
//            adapter.notifyDataSetChanged();
        }
    }
}
