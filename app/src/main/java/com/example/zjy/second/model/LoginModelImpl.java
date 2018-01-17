package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.presenter.LoginPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;

import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/13.
 */

public class LoginModelImpl implements LoginModel {
    private APIService apiService;

    public LoginModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void login(final LoginPresenter presenter, String username, String password) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.login(MultipartBody.Part.createFormData("username",username),MultipartBody.Part.createFormData("password",password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.returnValue(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LoginModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
//                        Log.i("LoginModelImpl",returnBean.get_$0().getToken()+"+++++");
                    }
                });
    }
}
