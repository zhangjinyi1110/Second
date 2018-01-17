package com.example.zjy.second.model;

import com.example.zjy.second.presenter.LoginPresenter;

/**
 * Created by ZJY on 2017/12/13.
 */

public interface LoginModel {

    void login(LoginPresenter presenter,String username,String password);

}
