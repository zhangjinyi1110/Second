package com.example.zjy.second.model;

import android.util.Log;

import com.example.zjy.second.bean.CommentBean;
import com.example.zjy.second.presenter.MessageFragmentPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/20.
 */

public class MessageFragmentModelImpl implements MessageFragmentModel {
    private APIService apiService;

    public MessageFragmentModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getComment(final MessageFragmentPresenter presenter, String token) {
        final List<CommentBean> commentBeen = new ArrayList<>();
        apiService.getcomment(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CommentBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.setComment(commentBeen);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MessageFragmentModel",e.toString());
                    }

                    @Override
                    public void onNext(List<CommentBean> list) {
                        commentBeen.addAll(list);
                        for(int i = 0 ; i<commentBeen.size() ; i++){
                            Log.i("Message",commentBeen.get(i).getUser().getName()+i+commentBeen.size());
                        }
                    }
                });
    }
}
