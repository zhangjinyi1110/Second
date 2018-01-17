package com.example.zjy.second.presenter;

import android.util.Log;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.model.CollectFragmentModelImpl;
import com.example.zjy.second.view.CollectFragmentView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/6.
 */

public class CollectFragmentPresenter {

    private CollectFragmentView view;
    private CollectFragmentModelImpl model;

    public CollectFragmentPresenter(CollectFragmentView view) {
        this.view = view;
        model = new CollectFragmentModelImpl();
    }

    public void getGoods(String token){
        model.getGoods(this,token);
    }

    public void setGoods(List<GoodsBean> list){
        view.setGoods(list);
    }

    public void unsetCollects(String token,int id,int position){
        model.unsetCollects(this,token,id,position);
    }

    public void returnValue(ReturnBean returnBean,int position) {
        switch(returnBean.getCode()){
            case 200:
                view.success(returnBean.getMsg(),position);
                break;
            case 404:
                view.failure(returnBean.getMsg());
                break;
            default:
                view.failure("未知错误。");
                break;
        }
    }

    public void value(){
        view.failure("asdf");
    }
}
