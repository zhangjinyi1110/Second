package com.example.zjy.second.view;

import com.example.zjy.second.bean.GoodsBean;

import java.util.List;

/**
 * Created by ZJY on 2017/12/6.
 */

public interface CollectFragmentView {

    void setGoods(List<GoodsBean> list);
    void success(String msg,int position);
    void failure(String msg);

}
