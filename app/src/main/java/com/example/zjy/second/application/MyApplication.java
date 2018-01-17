package com.example.zjy.second.application;

import android.app.Application;

import com.example.zjy.second.presenter.MainPresenter;

/**
 * Created by ZJY on 2017/11/27.
 */

public class MyApplication extends Application {

    private MainPresenter presenter;

    public MainPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }
}
