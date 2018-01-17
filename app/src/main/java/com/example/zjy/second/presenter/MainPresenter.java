package com.example.zjy.second.presenter;

import com.example.zjy.second.MainActivity;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.bean.UserBean;
import com.example.zjy.second.model.MainModelImpl;
import com.example.zjy.second.view.MainView;

/**
 * Created by ZJY on 2017/11/27.
 */

public class MainPresenter {

    private MainView view;
    private MainModelImpl mainModel;

    public MainPresenter(MainView view) {
        this.view = view;
        mainModel = new MainModelImpl();
    }

    public void setFragment(int code){
        view.toFragment(code);
    }

    public void init(){
        mainModel.init(this,(MainActivity)view);
    }

    public void setDate(UserBean userBean){
        view.setDate(userBean);
    }

    public void exit(String token){
        mainModel.exit(this,token);
    }

    public void exitReturn(ReturnBean returnBean) {
        switch(returnBean.getCode()){
            case 200:
                view.exitSuccess(returnBean.getMsg());
                break;
            default:
                view.exitFailure("未知错误,退出登录失败。");
                break;
        }
    }
}
