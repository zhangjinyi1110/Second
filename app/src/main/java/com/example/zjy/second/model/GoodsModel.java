package com.example.zjy.second.model;

import android.view.View;

import com.example.zjy.second.presenter.ClassifyFragmentPresenter;
import com.example.zjy.second.presenter.GoodsPresenter;

/**
 * Created by ZJY on 2017/12/18.
 */

public interface GoodsModel {

    void getGoods(GoodsPresenter presenter,int id);
    void getCover(GoodsPresenter presenter,String url);
    void collect(GoodsPresenter presenter, String token, int id);
    void getComment(GoodsPresenter presenter,int id);
    void replyComment(GoodsPresenter presenter,String token,int id,String content,int goods_id);
    void addComment(GoodsPresenter presenter,String token,String content,int goods_id);
    void deleteComment(GoodsPresenter presenter,String token,int id,int goods_id,View position);

}
