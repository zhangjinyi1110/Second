package com.example.zjy.second.model;

import android.content.Context;
import android.util.Log;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.presenter.PublishPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/14.
 */

public class PublishModelImpl implements PublishModel {
    private APIService apiService;

    public PublishModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void upload(final PublishPresenter publishPresenter, String token, String title, String content, int price, String tell, int type_way, int category_id, List<MultipartBody.Part> part) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.upload(token,part,title,content,price,type_way,category_id,tell)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        publishPresenter.returnValue(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("PublishModel",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                        Log.i("PublishModel",returnBean.getMsg()+"+++");
                    }
                });
    }
}
