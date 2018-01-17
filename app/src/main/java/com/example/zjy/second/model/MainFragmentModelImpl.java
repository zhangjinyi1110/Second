package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.BannerBean;
import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.presenter.MainFragmentPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/11/29.
 */

public class MainFragmentModelImpl implements MainFragmentModel {
    private APIService apiService;

    public MainFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getBanner(final MainFragmentPresenter presenter) {
        final List<String> list = new ArrayList<>();
//        APIService apiService = RetrofitManager.getAPI();
        apiService.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.setBanner(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MainFragmentModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        for(int i = 0 ; i<bannerBean.getItems().size() ; i++){
                            list.add(bannerBean.getItems().get(i).getGoods().getUrl().get(0));
                        }
                    }
                });
    }

    @Override
    public void getCategory(final MainFragmentPresenter presenter) {
        final List<CategoryBean> list = new ArrayList<>();
        apiService.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CategoryBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.setCategory(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MainFragmentModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(List<CategoryBean> categoryBeen) {
                        list.add(new CategoryBean(0,"商品推荐"));
                        for(int i = 0 ; i<categoryBeen.size() ; i++){
                            list.add(categoryBeen.get(i));
//                            Log.i("MainFragmentModelImpl",list.get(i).getName());
                        }
                    }
                });
    }
}
