package com.example.zjy.second.model;

import com.example.zjy.second.presenter.CollectFragmentPresenter;

/**
 * Created by ZJY on 2017/12/6.
 */

public interface CollectFragmentModel {

    void getGoods(CollectFragmentPresenter presenter,String token);
    void unsetCollects(CollectFragmentPresenter presenter,String token,int id,int position);

}
