package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.model.ResultFragmentModelImpl;
import com.example.zjy.second.view.ResultFragmentView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/19.
 */

public class ResultFragmentPresenter {

    private ResultFragmentModelImpl model;
    private ResultFragmentView view;

    public ResultFragmentPresenter(ResultFragmentView view) {
        this.view = view;
        model = new ResultFragmentModelImpl();
    }

    public void getResult(String token,String title){
        model.getResult(this,token,title);
    }

    public void setDate(List<SearchNameBean> list){
        view.setDate(list);
    }
}
