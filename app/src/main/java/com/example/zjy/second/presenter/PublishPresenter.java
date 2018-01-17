package com.example.zjy.second.presenter;

import android.util.Log;

import com.example.zjy.second.PublishActivity;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.model.PublishModelImpl;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.PublishView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ZJY on 2017/12/14.
 */

public class PublishPresenter {

    private PublishView view;
    private PublishModelImpl model;

    public PublishPresenter(PublishView view) {
        this.view = view;
        model = new PublishModelImpl();
    }

    public void upload(GoodsBean goodsBean){
        List<MultipartBody.Part> list = new ArrayList<>();
        for(int i = 0 ; i<goodsBean.getUrl().size() ; i++){
            File file = new File(goodsBean.getUrl().get(i));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part image = MultipartBody.Part.createFormData("image[]",file.getName(),requestFile);
            list.add(image);
        }
        String token = (String) SharedPreferencesUtils.getParam((PublishActivity)view,"token","");
        if(goodsBean.getType_way()==1){
            model.upload(this,token,goodsBean.getTitle(),goodsBean.getDescription(),goodsBean.getPrice(),goodsBean.getTell(),goodsBean.getType_way(),goodsBean.getCategory_id(),list);
            Log.i("PublishPresenter","upload1");
        }else{
            goodsBean.setPrice(0);
            model.upload(this,token,goodsBean.getTitle(),goodsBean.getDescription(),goodsBean.getPrice(),goodsBean.getTell(),goodsBean.getType_way(),goodsBean.getCategory_id(),list);
            Log.i("PublishPresenter","upload2");
        }
        Log.i("PublishPresenter","upload3");
    }

    public void returnValue(ReturnBean returnBean){
        switch(returnBean.getCode()){
            case 200:
                view.success(returnBean.getMsg());
                break;
            case 0:
                view.failure(returnBean.getMsg());
                break;
            default:view.failure(returnBean.getMsg());
                break;
        }
    }

    private List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("multipartFiles", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }
}
