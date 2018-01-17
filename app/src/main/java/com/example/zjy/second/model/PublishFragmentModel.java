package com.example.zjy.second.model;

import com.example.zjy.second.presenter.PublishFragmentPresenter;

/**
 * Created by ZJY on 2017/12/6.
 */

public interface PublishFragmentModel {

    void getGoods(PublishFragmentPresenter presenter,String token);
    void remove(PublishFragmentPresenter publishFragmentPresenter,String token,int id,int position);

}
