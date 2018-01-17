package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.presenter.ClassifyFragmentPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/6.
 */

public class ClassifyFragmentModelImpl implements ClassifyFragmentModel {
    private APIService apiService;

    public ClassifyFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getGoods(final ClassifyFragmentPresenter presenter, int id) {
//        Log.i("ClassifyFragmentModel","id++++++++"+id);
        final List<GoodsBean> list = new ArrayList<>();
        if(id==0){
            apiService.getRecent(5)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<GoodsBean>>() {
                        @Override
                        public void onCompleted() {
                            presenter.setGoods(list);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("ClassifyFragmentModel",e.toString());
                        }

                        @Override
                        public void onNext(List<GoodsBean> goodsBeen) {
                            list.addAll(goodsBeen);
                        }
                    });
        }else{
            apiService.getAllInCategory(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<GoodsBean>>() {
                        @Override
                        public void onCompleted() {
                            presenter.setGoods(list);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("ClassifyFragmentModel",e.toString());
                        }

                        @Override
                        public void onNext(List<GoodsBean> goodsBeen) {
                            list.addAll(goodsBeen);
//                        Log.i("ClassifyFragmentModel",list.size()+"+++++");
                        }
                    });
        }
    }

    @Override
    public void collect(final ClassifyFragmentPresenter presenter, String token, int id) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.setCollects(token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.returnValue(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        bean[0].setCode(500);
                        presenter.returnValue(bean[0]);
                        Log.i("ClassifyFragmentModel",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });

    }
}
