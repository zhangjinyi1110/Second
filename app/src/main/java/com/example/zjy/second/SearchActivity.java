package com.example.zjy.second;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.example.zjy.second.adapter.ResultAdapter;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.fragment.SearchItemFragment;
import com.example.zjy.second.presenter.SearchPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.SearchView;

import java.util.ArrayList;
import java.util.List;

import xyz.sahildave.widget.SearchViewLayout;

public class SearchActivity extends AppCompatActivity implements SearchView {
    private SearchViewLayout searchViewLayout;
    private SearchBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ResultAdapter adapter;
    private List<SearchNameBean> list;
    private SearchPresenter presenter;
    private SearchViewLayout.SearchListener searchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.setStatusBarColor(this,0xff0555ff);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new SearchPresenter(this);

        broadcastReceiver = new SearchBroadcastReceiver();
        intentFilter = new IntentFilter("android.intent.action.SEARCH_RESULT");
        registerReceiver(broadcastReceiver,intentFilter);

        recyclerView = (RecyclerView)findViewById(R.id.search_recycler_result);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new ResultAdapter(list,this);
        recyclerView.setAdapter(adapter);

        searchViewLayout = (SearchViewLayout) findViewById(R.id.search_view_container);
        searchViewLayout.setExpandedContentFragment(this, new SearchItemFragment());
        searchViewLayout.handleToolbarAnimation(toolbar);
        searchListener = new SearchViewLayout.SearchListener() {
            @Override
            public void onFinished(String searchKeyword) {
                searchViewLayout.collapse();
//                Snackbar.make(searchViewLayout, "Search Done - " + searchKeyword, Snackbar.LENGTH_LONG).show();
                String token = (String) SharedPreferencesUtils.getParam(SearchActivity.this,"token","");
                String title = searchKeyword;
                presenter.search(token,title);
            }
        };
        searchViewLayout.setSearchListener(searchListener);
        searchViewLayout.setSearchBoxListener(new SearchViewLayout.SearchBoxListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intent intent = new Intent("android.intent.action.SEARCH_TEXT");
                intent.putExtra("title",s.toString());
                sendBroadcast(intent);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void setDate(List<SearchNameBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return false;
    }

    public class SearchBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            searchListener.onFinished(intent.getStringExtra("title"));
            Log.i("SearchActivity","onReceive");
//            String token = (String) SharedPreferencesUtils.getParam(SearchActivity.this,"token","");
//            String title = intent.getStringExtra("title");
//            presenter.search(token,title);
        }
    }
}
