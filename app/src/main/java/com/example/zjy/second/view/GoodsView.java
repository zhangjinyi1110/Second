package com.example.zjy.second.view;

import android.graphics.Bitmap;
import android.view.View;

import com.example.zjy.second.bean.CommentBean;
import com.example.zjy.second.bean.GoodsBean;

import java.util.List;

/**
 * Created by ZJY on 2017/12/18.
 */

public interface GoodsView {

    void setDate(GoodsBean goodsBean);
    void setCover(Bitmap bitmap);
    void collectSuccess(String msg);
    void collectFailure(String msg);
    void comment(List<CommentBean> list);
    void replySuccess(String msg);
    void replyFailure(String msg);
    void commentSuccess(String msg);
    void commentFailure(String msg);
    void deleteSuccess(String msg,View position);
    void deleteFailure(String msg);
    void showBar();
    void hideBar();

}
