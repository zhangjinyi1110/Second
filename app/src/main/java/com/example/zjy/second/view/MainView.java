package com.example.zjy.second.view;

import com.example.zjy.second.bean.UserBean;

/**
 * Created by ZJY on 2017/11/27.
 */

public interface MainView {

    void toFragment(int code);
    void setDate(UserBean userBean);
    void exitSuccess(String msg);
    void exitFailure(String msg);
}
