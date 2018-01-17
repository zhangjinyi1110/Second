package com.example.zjy.second.view;

import com.example.zjy.second.bean.BannerBean;
import com.example.zjy.second.bean.CategoryBean;

import java.util.List;

/**
 * Created by ZJY on 2017/11/29.
 */

public interface MainFragmentView {

    void setBanner(List<String> list);
    void setCategory(List<CategoryBean> list);
    void showBar();
    void hideBar();

}
