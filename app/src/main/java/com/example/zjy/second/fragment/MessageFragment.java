package com.example.zjy.second.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zjy.second.R;
import com.example.zjy.second.adapter.MessageAdapter;
import com.example.zjy.second.bean.CommentBean;
import com.example.zjy.second.presenter.MessageFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.MessageFragmentView;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment implements MessageFragmentView {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private MessageAdapter adapter;
    private List<CommentBean> list;
    private MessageFragmentPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_message, container, false);
        initView();
        String token = (String) SharedPreferencesUtils.getParam(getContext(),"token","");
        presenter = new MessageFragmentPresenter(this);
        presenter.getComment(token);
        return view;
    }

    private void initView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.message_recycler);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new MessageAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setMessage(List<CommentBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
