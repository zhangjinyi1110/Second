package com.example.zjy.second.model;

import com.example.zjy.second.presenter.SearchPresenter;

/**
 * Created by ZJY on 2017/12/19.
 */

public interface SearchModel {

    void search(SearchPresenter presenter,String token,String title);

}
