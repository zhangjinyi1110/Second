package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.presenter.ResultFragmentPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/19.
 */

public class ResultFragmentModelImpl implements ResultFragmentModel {
    private APIService apiService;

    public ResultFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getResult(final ResultFragmentPresenter presenter, String token, String title) {
        final List<SearchNameBean> list = new ArrayList<>();
        apiService.search(token, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SearchNameBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.setDate(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ResultFragmentModel",e.toString());
                    }

                    @Override
                    public void onNext(List<SearchNameBean> searchNameBeen) {
                        list.addAll(searchNameBeen);
                    }
                });
    }
}
