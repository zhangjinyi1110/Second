package com.example.zjy.second.bean;

/**
 * Created by ZJY on 2017/12/20.
 */

public class CommentBean {
    /**
     * id : 1
     * content : 123
     * re_content : null
     * uid : 1
     * re_id : 1
     * parent_id : null
     * goods_id : 6
     * create_time : 2017-12-21 16:48:19
     * reply_time : null
     * user : {"id":1,"name":"admin","pic":"http://119.23.237.193/hzl/Second/public/images/5a39d0c3b3c48.jpg"}
     */

    private int id;
    private String content;
    private String re_content;
    private int uid;
    private int re_id;
    private int parent_id;
    private int goods_id;
    private String create_time;
    private String reply_time;
    private UserBean user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRe_content() {
        return re_content;
    }

    public void setRe_content(String re_content) {
        this.re_content = re_content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRe_id() {
        return re_id;
    }

    public void setRe_id(int re_id) {
        this.re_id = re_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 1
         * name : admin
         * pic : http://119.23.237.193/hzl/Second/public/images/5a39d0c3b3c48.jpg
         */

        private int id;
        private String name;
        private String pic;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
