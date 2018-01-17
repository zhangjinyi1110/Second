package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.presenter.PublishFragmentPresenter;
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

public class PublishFragmentModelImpl implements PublishFragmentModel {
    private APIService apiService;

    public PublishFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getGoods(final PublishFragmentPresenter presenter,String token) {
        final List<GoodsBean> list = new ArrayList<>();
//        apiService.getAllInCategory(1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<GoodsBean>>() {
//                    @Override
//                    public void onCompleted() {
//                        presenter.setGoods(list);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("PublishFragment",e.toString());
//                    }
//
//                    @Override
//                    public void onNext(List<GoodsBean> goodsBeen) {
//                        list.addAll(goodsBeen);
//                        Log.i("PublishFragment",list.size()+"+++++");
//                    }
//                });
        apiService.getUpload(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GoodsBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.setGoods(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("PublishFragment",e.toString());
                    }

                    @Override
                    public void onNext(List<GoodsBean> goodsBeen) {
                        list.addAll(goodsBeen);
                        Log.i("PublishFragment",list.size()+"+++++");
                    }
                });
    }

    @Override
    public void remove(final PublishFragmentPresenter publishFragmentPresenter, String token, int id, final int position) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.delMyGoods(token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        publishFragmentPresenter.removeReturn(bean[0],position);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("PublishFragmentModel",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }
}
