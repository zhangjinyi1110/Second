package com.example.zjy.second.model;

import com.example.zjy.second.presenter.ClassifyFragmentPresenter;

/**
 * Created by ZJY on 2017/12/6.
 */

public interface ClassifyFragmentModel {

    void getGoods(ClassifyFragmentPresenter presenter,int id);
    void collect(ClassifyFragmentPresenter presenter,String token,int id);

}
