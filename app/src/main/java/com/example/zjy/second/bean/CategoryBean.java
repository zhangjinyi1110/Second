package com.example.zjy.second.bean;

/**
 * Created by ZJY on 2017/11/29.
 */

public class CategoryBean {
    /**
     * id : 1
     * name : 电子产品
     */

    private int id;
    private String name;

    public CategoryBean() {
    }

    public CategoryBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
