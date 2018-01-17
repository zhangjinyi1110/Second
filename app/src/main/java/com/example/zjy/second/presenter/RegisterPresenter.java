package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.model.RegisterModelImpl;
import com.example.zjy.second.view.RegisterView;

/**
 * Created by ZJY on 2017/12/13.
 */

public class RegisterPresenter {

    private RegisterView view;
    private RegisterModelImpl model;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
        model = new RegisterModelImpl();
    }

    public void register(String username,String password){
        model.register(this,username,password);
    }

    public void returnValue(ReturnBean returnBean){
        switch(returnBean.getCode()){
            case 200:
                view.success(returnBean.getMsg());
                break;
            case 0:
                view.failure(returnBean.getMsg());
                break;
        }
    }
}
