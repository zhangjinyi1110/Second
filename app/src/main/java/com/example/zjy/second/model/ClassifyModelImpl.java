package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.presenter.ClassifyPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/12.
 */

public class ClassifyModelImpl implements ClassifyModel {
    private APIService apiService;

    public ClassifyModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getDate(final ClassifyPresenter presenter) {
        final List<CategoryBean> list = new ArrayList<>();
        apiService.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CategoryBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.setDate(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ClassifyModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(List<CategoryBean> categoryBeen) {
                        list.addAll(categoryBeen);
                    }
                });
    }
}
