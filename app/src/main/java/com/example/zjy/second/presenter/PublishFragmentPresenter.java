package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.model.PublishFragmentModelImpl;
import com.example.zjy.second.view.PublishFragmentView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/6.
 */

public class PublishFragmentPresenter {

    private PublishFragmentView view;
    private PublishFragmentModelImpl model;

    public PublishFragmentPresenter(PublishFragmentView view) {
        this.view = view;
        model = new PublishFragmentModelImpl();
    }

    public void getGoods(String token){
        model.getGoods(this,token);
    }

    public void setGoods(List<GoodsBean> list){
        view.setGoods(list);
    }

    public void remove(String token,int id,int position){
        model.remove(this,token,id,position);
    }

    public void removeReturn(ReturnBean returnBean,int position){
        switch(returnBean.getCode()){
            case 200:
                view.removeSuccess(returnBean.getMsg(),position);
                break;
            case 0:
                view.removeFailure(returnBean.getMsg());
                break;
        }
    }
}
