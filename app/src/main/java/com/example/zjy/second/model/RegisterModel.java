package com.example.zjy.second.model;

import com.example.zjy.second.presenter.RegisterPresenter;

/**
 * Created by ZJY on 2017/12/13.
 */

public interface RegisterModel {

    void register(RegisterPresenter presenter,String username,String password);

}
