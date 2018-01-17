package com.example.zjy.second.presenter;

import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;

import com.example.zjy.second.GoodsActivity;
import com.example.zjy.second.bean.CommentBean;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.model.GoodsModelImpl;
import com.example.zjy.second.view.GoodsView;

import java.util.List;

/**
 * Created by ZJY on 2017/12/18.
 */

public class GoodsPresenter {

    private GoodsView view;
    private GoodsModelImpl model;

    public GoodsPresenter(GoodsView view) {
        this.view = view;
        model = new GoodsModelImpl();
    }

    public void getGoods(int id){
        model.getGoods(this,id);
    }

    public void setDate(GoodsBean goodsBean){
        view.setDate(goodsBean);
    }

    public void getCover(String url){
        model.getCover(this,url);
    }

    public void setCover(Bitmap bitmap){
        view.setCover(blur(bitmap,23f));
    }

    private Bitmap blur(Bitmap bitmap, float radius) {
        Bitmap output = Bitmap.createBitmap(bitmap);
        RenderScript rs = RenderScript.create((GoodsActivity)view);// 构建一个RenderScript对象
        ScriptIntrinsicBlur gaussianBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)); // 创建高斯模糊脚本
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);//创建用于输入的脚本类型
        Allocation allOut = Allocation.createFromBitmap(rs, output); // 创建用于输出的脚本类型
        gaussianBlur.setRadius(radius); // 设置模糊半径，范围0f<radius<=25f
        gaussianBlur.setInput(allIn); // 设置输入脚本类型
        gaussianBlur.forEach(allOut); // 执行高斯模糊算法，并将结果填入输出脚本类型中
        allOut.copyTo(output); // 将输出内存编码为Bitmap，图片大小必须注意
        rs.destroy(); // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()
        return output;
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

    public void getComment(int id){
        view.showBar();
        model.getComment(this,id);
    }

    public void returnComment(List<CommentBean> list){

        view.comment(list);
        view.hideBar();
        Log.i("GoodsPresenter","hide+++");
    }

    public void reply(String token,int id,String content,int goods_id){
        view.showBar();
        model.replyComment(this,token,id,content,goods_id);
    }

    public void replyReturn(ReturnBean returnBean){
        switch(returnBean.getCode()){
            case 200:
                view.replySuccess(returnBean.getMsg());
                break;
            case 0:
                view.replyFailure(returnBean.getMsg());
                break;
            case 400:
                view.replyFailure(returnBean.getMsg());
                break;
            default:
                view.replyFailure("未知错误。");
                break;
        }
        view.hideBar();
    }

    public void addComment(String token,int goods_id,String content){
        view.showBar();
        model.addComment(this,token,content,goods_id);
    }

    public void commentReturn(ReturnBean returnBean){
        switch(returnBean.getCode()){
            case 200:
                view.commentSuccess(returnBean.getMsg());
                break;
            case 0:
                view.commentFailure(returnBean.getMsg());
                break;
            default:
                view.commentFailure("未知错误。");
                break;
        }
        view.hideBar();
    }

    public void deleteComment(String token,int id,int goods_id,View position){
        view.showBar();
        model.deleteComment(this,token,id,goods_id,position);
    }

    public void deleteReturn(ReturnBean returnBean,View position){
        switch(returnBean.getCode()){
            case 200:
                view.deleteSuccess(returnBean.getMsg(),position);
                break;
            case 0:
                view.deleteFailure(returnBean.getMsg());
                break;
            default:
                view.deleteFailure("未知错误。");
                break;
        }
        view.hideBar();
    }
}
