package com.example.zjy.second.model;

import com.example.zjy.second.presenter.SearchItemPresenter;

/**
 * Created by ZJY on 2017/12/19.
 */

public interface SearchItemModel {

    void search(SearchItemPresenter presenter,String token,String title);

}
