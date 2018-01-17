package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.model.SearchFragmentModelImpl;
import com.example.zjy.second.view.SearchFragmentView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/19.
 */

public class SearchFragmentPresenter {

    private SearchFragmentView view;
    private SearchFragmentModelImpl model;

    public SearchFragmentPresenter(SearchFragmentView view) {
        this.view = view;
        model = new SearchFragmentModelImpl();
    }

    public void setSearchTitle(String token,String name){
        model.setSearchTitle(this,token,name);
    }

    public void searchResult(List<SearchNameBean> list){
        view.searchResult(list);
    }
}
