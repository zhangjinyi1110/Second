package com.example.zjy.second.bean;

import java.util.List;

/**
 * Created by ZJY on 2017/11/24.
 */

public class SearchNameBean {
    /**
     * id : 22
     * title : ipa
     * description : 有七成新
     * type_way : 1
     * keywords : ipa
     * price : 888
     * tell : 123123123
     * url : ["http://119.23.237.193/hzl/Second/public/images/ep01.png","http://119.23.237.193/hzl/Second/public/images/ep02.png","http://119.23.237.193/hzl/Second/public/images/ep04.png"]
     * click : 2
     * category_id : 1
     * uid : 2
     */

    private int id;
    private String title;
    private String description;
    private int type_way;
    private String keywords;
    private int price;
    private int tell;
    private int click;
    private int category_id;
    private int uid;
    private List<String> url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType_way() {
        return type_way;
    }

    public void setType_way(int type_way) {
        this.type_way = type_way;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTell() {
        return tell;
    }

    public void setTell(int tell) {
        this.tell = tell;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
