package com.example.zjy.second.view;

import com.example.zjy.second.bean.GoodsBean;

import java.util.List;

/**
 * Created by ZJY on 2017/12/6.
 */

public interface ClassifyFragmentView {

    void setGoods(List<GoodsBean> list);
    void collectSuccess(String msg);
    void collectFailure(String msg);

}
