package com.example.zjy.second.presenter;

import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.bean.UserBean;
import com.example.zjy.second.fragment.UserInfoFragment;
import com.example.zjy.second.model.UserInfoFragmentModelImpl;
import com.example.zjy.second.view.UserInfoFragmentView;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ZJY on 2017/12/13.
 */

public class UserInfoFragmentPresenter {

    private UserInfoFragmentView view;
    private UserInfoFragmentModelImpl model;

    public UserInfoFragmentPresenter(UserInfoFragmentView view) {
        this.view = view;
        model = new UserInfoFragmentModelImpl();
    }

    public void getUserInfo(){
        model.getUserInfo(this,((UserInfoFragment)view).getContext());
    }

    public void setUserInfo(UserBean userInfo){
        view.setUserInfo(userInfo);
    }

    public void upUserInfo(UserBean userBean){
//        Pattern pattern = Pattern.compile("[0-9]*");
//        Matcher isNum = pattern.matcher(userBean.getPhone());
//        if(userBean.getPhone().length()==11 && isNum.matches()){
//            model.upUserInfo(this,((UserInfoFragment)view).getContext(),userBean);
//        }else if(userBean.getPhone().length()==0){
//            model.upUserInfo(this,((UserInfoFragment)view).getContext(),userBean);
//        }else{
//            view.check("请正确填写电话号码");
//            return;
//        }
        model.upUserInfo(this,((UserInfoFragment)view).getContext(),userBean);
    }

    public void returnValue(ReturnBean returnBean){
        switch(returnBean.getCode()){
            case 200:
                view.success(returnBean.getMsg());
                break;
            case 0:
                view.failure(returnBean.getMsg());
                break;
        }
    }

    public void upImage(String  token,String path){
        model.upImage(this,token,path);
    }

    public void setImage(ReturnBean returnBean,String path){
        switch(returnBean.getCode()){
            case 200:
                view.imageSuccess(returnBean.getMsg(),path);
                break;
            case 0:
                view.imageFailure(returnBean.getMsg());
                break;
            case 400:
                view.imageFailure(returnBean.getMsg());
                break;
        }
    }
}
