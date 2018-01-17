package com.example.zjy.second.view;

import com.example.zjy.second.bean.UserBean;

/**
 * Created by ZJY on 2017/12/13.
 */

public interface UserInfoFragmentView {

    void setUserInfo(UserBean userInfo);
    void check(String msg);
    void success(String msg);
    void failure(String msg);
    void imageSuccess(String msg,String path);
    void imageFailure(String msg);

}
