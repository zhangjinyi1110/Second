package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.presenter.SearchFragmentPresenter;
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

public class SearchFragmentModelImpl implements SearchFragmentModel {
    private APIService apiService;

    public SearchFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void setSearchTitle(final SearchFragmentPresenter presenter, String token, String title) {
        final List<SearchNameBean> list = new ArrayList<>();
        apiService.search(token,title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SearchNameBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.searchResult(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("SearchFragmentModel",e.toString());
                    }

                    @Override
                    public void onNext(List<SearchNameBean> goodsBeen) {
                        Log.i("SearchFragmentModel",goodsBeen.size()+"+++");
                        list.addAll(goodsBeen);
                    }
                });
    }
}
