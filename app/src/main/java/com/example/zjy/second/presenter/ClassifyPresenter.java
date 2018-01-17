package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.model.ClassifyModelImpl;
import com.example.zjy.second.view.ClassifyView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/12.
 */

public class ClassifyPresenter {

    private ClassifyView view;
    private ClassifyModelImpl model;

    public ClassifyPresenter(ClassifyView view) {
        this.view = view;
        model = new ClassifyModelImpl();
    }

    public void getDate(){
        model.getDate(this);
    }

    public void setDate(List<CategoryBean> list){
        view.setDate(list);
    }
}
