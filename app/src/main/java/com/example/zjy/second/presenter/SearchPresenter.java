package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.model.SearchModel;
import com.example.zjy.second.model.SearchModelImpl;
import com.example.zjy.second.view.SearchView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/19.
 */

public class SearchPresenter {

    private SearchView view;
    private SearchModelImpl model;

    public SearchPresenter(SearchView view) {
        this.view = view;
        model = new SearchModelImpl();
    }

    public void search(String token,String title){
        model.search(this,token,title);
    }

    public void result(List<SearchNameBean> list){
        view.setDate(list);
    }
}
