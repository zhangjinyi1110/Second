package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.model.SynthesizeFragmentModelImpl;
import com.example.zjy.second.view.SynthesizeFragmentView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/19.
 */

public class SynthesizeFragmentPresenter {

    private SynthesizeFragmentView view;
    private SynthesizeFragmentModelImpl model;

    public SynthesizeFragmentPresenter(SynthesizeFragmentView view) {
        this.view = view;
        model = new SynthesizeFragmentModelImpl();
    }

    public void getCategory() {
        model.getCategory(this);
    }

    public void setCategory(List<CategoryBean> list) {
        view.setCategory(list);
    }
}
