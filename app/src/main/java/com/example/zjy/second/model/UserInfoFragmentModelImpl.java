package com.example.zjy.second.model;

import android.content.Context;
import android.util.Log;

import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.bean.UserBean;
import com.example.zjy.second.presenter.UserInfoFragmentPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/13.
 */

public class UserInfoFragmentModelImpl implements UserInfoFragmentModel {
    private APIService apiService;

    public UserInfoFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getUserInfo(final UserInfoFragmentPresenter presenter, Context context) {
        final UserBean[] bean = {new UserBean()};
        //(String) SharedPreferencesUtils.getParam(context,"token","")
        apiService.getUser((String) SharedPreferencesUtils.getParam(context,"token",""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.setUserInfo(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("UserInfoFragmentModel",e.toString()+"getUserInfo");
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        bean[0] = userBean;
                    }
                });
    }

    @Override
    public void upUserInfo(final UserInfoFragmentPresenter presenter, Context context, UserBean userBean) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.upUser((String)SharedPreferencesUtils.getParam(context,"token",""),userBean.getName(),userBean.getAddress(),userBean.getWeChat(),userBean.getPhone(),userBean.getEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.returnValue(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("UserInfoFragmentModel",e.toString()+"upUserInfo");
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                        Log.i("UserInfoFragmentModel",returnBean.getCode()+"+++");
                    }
                });
    }

    @Override
    public void upImage(final UserInfoFragmentPresenter presenter, String token, final String path) {
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image",file.getName(),requestFile);
        Log.i("UserInfoFragmentModel",file.getName());
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.editPic(token,image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.setImage(bean[0],path);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("UserInfoFragmentModel",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }
}
