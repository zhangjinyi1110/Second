package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.presenter.SynthesizeFragmentPresenter;
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

public class SynthesizeFragmentModelImpl implements SynthesizeFragmentModel {
    private APIService apiService;

    public SynthesizeFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getCategory(final SynthesizeFragmentPresenter presenter) {
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
                        list.add(new CategoryBean(0,"人  气"));
                        for(int i = 0 ; i<categoryBeen.size() ; i++){
                            list.add(categoryBeen.get(i));
//                            Log.i("MainFragmentModelImpl",list.get(i).getName());
                        }
                    }
                });
    }
}
