package com.example.zjy.second.presenter;

import android.util.Log;

import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.model.ClassifyFragmentModelImpl;
import com.example.zjy.second.view.ClassifyFragmentView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/6.
 */

public class ClassifyFragmentPresenter {

    private ClassifyFragmentView view;
    private ClassifyFragmentModelImpl model;

    public ClassifyFragmentPresenter(ClassifyFragmentView view) {
        this.view = view;
        model = new ClassifyFragmentModelImpl();
    }

    public void getGoods(int id){
//        Log.i("ClassifyFragment","getGoods++++++++"+id);
        model.getGoods(this,id);
    }

    public void setGoods(List<GoodsBean> list){
        view.setGoods(list);
    }

    public void collect(String token,int id){
        model.collect(this,token,id);
    }

    public void returnValue(ReturnBean returnBean){
        switch(returnBean.getCode()){
            case 200:
                view.collectSuccess(returnBean.getMsg());
                break;
            case 0:
                view.collectFailure(returnBean.getMsg());
                break;
            case 404:
                view.collectFailure(returnBean.getMsg());
                break;
            default:
                view.collectFailure("未知错误。");
                break;
        }
    }
}
