package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.presenter.RegisterPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;

import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/13.
 */

public class RegisterModelImpl implements RegisterModel {
    private APIService apiService;

    public RegisterModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void register(final RegisterPresenter presenter, String username, final String password) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.register(MultipartBody.Part.createFormData("username",username),MultipartBody.Part.createFormData("password",password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.returnValue(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RegisterModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }
}
