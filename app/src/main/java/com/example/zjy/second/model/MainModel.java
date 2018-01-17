package com.example.zjy.second.model;

import android.content.Context;

import com.example.zjy.second.presenter.MainPresenter;

/**
 * Created by ZJY on 2017/11/27.
 */

public interface MainModel {

    void init(MainPresenter presenter, Context context);
    void exit(MainPresenter presenter,String token);

}
