package com.example.zjy.second.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.zjy.second.bean.CommentBean;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.presenter.ClassifyFragmentPresenter;
import com.example.zjy.second.presenter.GoodsPresenter;
import com.example.zjy.second.retrofit.APIService;
import com.example.zjy.second.retrofit.RetrofitManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZJY on 2017/12/18.
 */

public class GoodsModelImpl implements GoodsModel {
    private APIService apiService;

    public GoodsModelImpl() {
        apiService = RetrofitManager.getAPI();
    }

    @Override
    public void getGoods(final GoodsPresenter presenter, int id) {
        final GoodsBean[] bean = {new GoodsBean()};
        apiService.getGoods(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GoodsBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.setDate(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("GoodsModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(List<GoodsBean> goodsBeen) {
                        bean[0] = goodsBeen.get(0);
                    }
                });
    }

    @Override
    public void getCover(final GoodsPresenter presenter, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = getBitMBitmap(url);
                presenter.setCover(bitmap);
            }
        }).start();
    }

    @Override
    public void collect(final GoodsPresenter presenter, String token, int id) {
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
                        Log.i("ClassifyFragmentModel",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }

    @Override
    public void getComment(final GoodsPresenter presenter, int id) {
        final List<CommentBean> list = new ArrayList<>();
        apiService.selectComment(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CommentBean>>() {
                    @Override
                    public void onCompleted() {
                        presenter.returnComment(list);
                    }

                    @Override
                    public void onError(Throwable e) {
//                        if(list.size()==0){
                            presenter.returnComment(list);
//                        }
                        Log.i("GoodsModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(List<CommentBean> commentBeen) {
                        list.addAll(commentBeen);
                    }
                });
    }

    @Override
    public void replyComment(final GoodsPresenter presenter, String token, int id, String content, int goods_id) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.reclyComment(token,id,content,goods_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.replyReturn(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("GoodsModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }

    @Override
    public void addComment(final GoodsPresenter presenter, String token, String content, int goods_id) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.addComment(token,content,goods_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.commentReturn(bean[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("GoodsModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }

    @Override
    public void deleteComment(final GoodsPresenter presenter, String token, int id, int goods_id, final View position) {
        final ReturnBean[] bean = {new ReturnBean()};
        apiService.deleteComment(token,id,goods_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnBean>() {
                    @Override
                    public void onCompleted() {
                        presenter.deleteReturn(bean[0],position);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("GoodsModelImpl",e.toString());
                    }

                    @Override
                    public void onNext(ReturnBean returnBean) {
                        bean[0] = returnBean;
                    }
                });
    }

    private Bitmap getBitMBitmap(String urlPath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlPath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
            // TODO Auto-generated catch block
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
