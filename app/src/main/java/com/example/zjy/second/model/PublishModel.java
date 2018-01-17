package com.example.zjy.second.model;

import android.content.Context;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.PublishPresenter;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by ZJY on 2017/12/14.
 */

public interface PublishModel {

    void upload(PublishPresenter publishPresenter,String token,String title,String content,int price,String tell,
                int type_way,int category_id,List<MultipartBody.Part> part);

}
