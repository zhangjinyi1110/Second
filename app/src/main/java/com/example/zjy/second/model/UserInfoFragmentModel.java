package com.example.zjy.second.model;

import android.content.Context;

import com.example.zjy.second.bean.UserBean;
import com.example.zjy.second.presenter.UserInfoFragmentPresenter;

import okhttp3.MultipartBody;

/**
 * Created by ZJY on 2017/12/13.
 */

public interface UserInfoFragmentModel {

    void getUserInfo(UserInfoFragmentPresenter presenter, Context context);
    void upUserInfo(UserInfoFragmentPresenter presenter, Context context, UserBean userBean);
    void upImage(UserInfoFragmentPresenter presenter, String token, String path);

}
