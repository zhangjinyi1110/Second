package com.example.zjy.second.model;

import android.content.Context;
import android.util.Log;

import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.bean.UserBean;
import com.example.zjy.second.presenter.MainPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/11/27.
 */

public class MainModelImpl implements MainModel {
    private APIService apiService;

    public MainModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void init(final MainPresenter presenter, Context context) {
        final UserBean[] bean = {new UserBean()};
        apiService.getUser((String)SharedPreferencesUtils.getParam(context,"token",""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.setDate(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MainModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        bean[0] = userBean;
                    }
                });
    }

    @Override
    public void exit(final MainPresenter presenter, String token) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.logout(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.exitReturn(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MainModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }
}
