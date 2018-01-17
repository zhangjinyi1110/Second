package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.presenter.CollectFragmentPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/6.
 */

public class CollectFragmentModelImpl implements CollectFragmentModel {
    private APIService apiService;

    public CollectFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getGoods(final CollectFragmentPresenter presenter, String token) {
        final List<GoodsBean> list = new ArrayList<>();
        apiService.getCollects(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GoodsBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.setGoods(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("CollectFragmentModel", e.toString());
                    }

                    @Override
                    public void onNext(List<GoodsBean> goodsBeen) {
                        list.addAll(goodsBeen);
                    }
                });
    }

    @Override
    public void unsetCollects(final CollectFragmentPresenter presenter, String token,int id, final int position) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.unsetCollects(token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.returnValue(bean[0],position);
                    }

                    @Override
                    public void onError(Throwable e) {
                        bean[0].setCode(500);
                        presenter.returnValue(bean[0],position);
                        Log.i("CollectFragmentModel", e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }
}
