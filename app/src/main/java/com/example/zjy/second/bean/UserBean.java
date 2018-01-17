package com.example.zjy.second.bean;

/**
 * Created by ZJY on 2017/11/24.
 */

public class UserBean {
    /**
     * name : null
     * pic : http://119.23.237.193/hzl/Second/public/images/
     * address : null
     * WeChat : null
     * phone : null
     * email : null
     */

    private String name;
    private String pic;
    private String address;
    private String WeChat;
    private String phone;
    private String email;

    public UserBean() {
    }

    public UserBean(String name, String address, String weChat, String phone, String email) {
        this.name = name;
        this.address = address;
        WeChat = weChat;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeChat() {
        return WeChat;
    }

    public void setWeChat(String WeChat) {
        this.WeChat = WeChat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
