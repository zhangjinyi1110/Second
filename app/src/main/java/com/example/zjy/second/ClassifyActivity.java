package com.example.zjy.second;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.zjy.second.adapter.ClassifyCAdapter;
import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.presenter.ClassifyPresenter;
import com.example.zjy.second.view.ClassifyView;

import java.util.ArrayList;
import java.util.List;

public class ClassifyActivity extends AppCompatActivity implements ClassifyView {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private List<CategoryBean> list;
    private ClassifyCAdapter adapter;
    private ClassifyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        MainActivity.setStatusBarColor(this,0xff0555ff);
        toolbar = (Toolbar)findViewById(R.id.classify_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_off);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView)findViewById(R.id.classify_recycler_view);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new ClassifyCAdapter(list,this);
        recyclerView.setAdapter(adapter);

        presenter = new ClassifyPresenter(this);
        presenter.getDate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return false;
    }

    @Override
    public void setDate(List<CategoryBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
