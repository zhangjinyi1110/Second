package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.model.SearchItemModelImpl;
import com.example.zjy.second.view.SearchItemView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/19.
 */

public class SearchItemPresenter {

    private SearchItemModelImpl model;
    private SearchItemView view;

    public SearchItemPresenter(SearchItemView view) {
        this.view = view;
        model = new SearchItemModelImpl();
    }

    public void search(String token,String title){
        model.search(this,token,title);
    }

    public void returnValue(List<SearchNameBean> list) {
        view.setDate(list);
    }
}
