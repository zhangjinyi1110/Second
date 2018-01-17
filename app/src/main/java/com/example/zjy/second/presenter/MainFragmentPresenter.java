package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.BannerBean;
import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.model.MainFragmentModelImpl;
import com.example.zjy.second.view.MainFragmentView;

import java.util.List;

/**
 * Created by ZJY on 2017/11/29.
 */

public class MainFragmentPresenter {

    private MainFragmentView view;
    private MainFragmentModelImpl model;

    public MainFragmentPresenter(MainFragmentView view) {
        this.view = view;
        model = new MainFragmentModelImpl();
    }

    public void getBanner(){
        model.getBanner(this);
    }

    public void setBanner(List<String> list){
        view.setBanner(list);
    }

    public void getCategory(){
        view.showBar();
        model.getCategory(this);
    }

    public void setCategory(List<CategoryBean> list){
        view.setCategory(list);
        view.hideBar();
    }
}
