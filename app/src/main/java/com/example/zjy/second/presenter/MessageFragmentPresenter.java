package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.CommentBean;
import com.example.zjy.second.model.MessageFragmentModelImpl;
import com.example.zjy.second.view.MessageFragmentView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/20.
 */

public class MessageFragmentPresenter {

    private MessageFragmentView view;
    private MessageFragmentModelImpl messageFragmentModel;

    public MessageFragmentPresenter(MessageFragmentView view) {
        this.view = view;
        messageFragmentModel = new MessageFragmentModelImpl();
    }

    public void getComment(String token){
        messageFragmentModel.getComment(this,token);
    }

    public void setComment(List<CommentBean> commentBeen) {
        view.setMessage(commentBeen);
    }
}
