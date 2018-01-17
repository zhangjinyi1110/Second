package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.model.LoginModelImpl;
import com.example.zjy.second.view.LoginView;

/**
 * Created by ZJY on 2017/12/13.
 */

public class LoginPresenter {

    private LoginView view;
    private LoginModelImpl model;

    public LoginPresenter(LoginView view) {
        this.view = view;
        model = new LoginModelImpl();
    }

    public void login(String username,String password){
        model.login(this,username,password);
    }

    public void returnValue(ReturnBean returnBean){
        switch(returnBean.getCode()){
            case 200:
                view.success(returnBean.getMsg(),returnBean.get_$0().getToken());
                break;
            case 400:
                view.failure(returnBean.getMsg());
                break;
            case 0:
                view.failure(returnBean.getMsg());
                break;
            case 1:
                view.failure(returnBean.getMsg());
                break;
        }
    }
}
