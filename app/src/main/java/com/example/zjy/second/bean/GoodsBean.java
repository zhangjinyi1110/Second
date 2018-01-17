package com.example.zjy.second.bean;

import java.util.List;

/**
 * Created by ZJY on 2017/11/24.
 */

public class GoodsBean {
    /**
     * id : 6
     * title : 吉他
     * description : 牌子货吉他，值得一买
     * type_way : 1
     * keywords : 吉他
     * price : 140
     * tell : 1234567890
     * url : ["http://119.23.237.193/hzl/Second/public/images/ad06.jpg","http://119.23.237.193/hzl/Second/public/images/ad07.jpg","http://119.23.237.193/hzl/Second/public/images/ad08.jpg"]
     * click : 4
     * collect_num : 1
     * comment_num : 0
     * category_id : 2
     * uid : 1
     * user : {"id":1,"name":"admin","pic":"http://119.23.237.193/hzl/Second/public/images/5a39d0c3b3c48.jpg"}
     */

    private int id;
    private String title;
    private String description;
    private int type_way;
    private String keywords;
    private int price;
    private String tell;
    private int click;
    private int collect_num;
    private int comment_num;
    private int category_id;
    private int uid;
    private UserBean user;
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

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(int collect_num) {
        this.collect_num = collect_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
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
    /**
     * id : 1
     * title : 黑人牙膏
     * description : 黑人牙膏，黑掉你的牙齿
     * type_way : 1
     * keywords : 黑人牙膏
     * price : 11
     * tell : 110
     * url : ["http://119.23.237.193/hzl/Second/public/images/banner-1a.png","http://119.23.237.193/hzl/Second/public/images/banner-2a.png","http://119.23.237.193/hzl/Second/public/images/banner-3a.png"]
     * click : 38
     * category_id : 2
     * uid : 1
     * user : {"id":1,"name":"admin","pic":"http://119.23.237.193/hzl/Second/public/images/admin.jpg"}
     */

//    private int id;
//    private String title;
//    private String description;
//    private int type_way;
//    private String keywords;
//    private int price;
//    private String tell;
//    private int click;
//    private int category_id;
//    private int uid;
//    private UserBean user;
//    private List<String> url;
//    private int type;
//
//    public static final int TYPE_HEADER = 0;
//    public static final int TYPE_CONTEXT = 1;
//    public static final int TYPE_IMAGE = 2;
//    public static final int TYPE_OPTION = 3;
//    public static final int TYPE_REPLY = 4;
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public GoodsBean() {
//    }
//
//    public GoodsBean(String title, String description, int type_way, int price, String tell, int category_id, List<String> url) {
//        this.title = title;
//        this.description = description;
//        this.type_way = type_way;
//        this.price = price;
//        this.tell = tell;
//        this.category_id = category_id;
//        this.url = url;
//    }
//
//    public GoodsBean(String title, String description, int type_way, String tell, int category_id, List<String> url) {
//        this.title = title;
//        this.description = description;
//        this.type_way = type_way;
//        this.tell = tell;
//        this.category_id = category_id;
//        this.url = url;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getType_way() {
//        return type_way;
//    }
//
//    public void setType_way(int type_way) {
//        this.type_way = type_way;
//    }
//
//    public String getKeywords() {
//        return keywords;
//    }
//
//    public void setKeywords(String keywords) {
//        this.keywords = keywords;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public String getTell() {
//        return tell;
//    }
//
//    public void setTell(String tell) {
//        this.tell = tell;
//    }
//
//    public int getClick() {
//        return click;
//    }
//
//    public void setClick(int click) {
//        this.click = click;
//    }
//
//    public int getCategory_id() {
//        return category_id;
//    }
//
//    public void setCategory_id(int category_id) {
//        this.category_id = category_id;
//    }
//
//    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }
//
//    public UserBean getUser() {
//        return user;
//    }
//
//    public void setUser(UserBean user) {
//        this.user = user;
//    }
//
//    public List<String> getUrl() {
//        return url;
//    }
//
//    public void setUrl(List<String> url) {
//        this.url = url;
//    }
//
//    @Override
//    public int getItemType() {
//        return type;
//    }
//
//    public static class UserBean {
//        /**
//         * id : 1
//         * name : admin
//         * pic : http://119.23.237.193/hzl/Second/public/images/admin.jpg
//         */
//
//        private int id;
//        private String name;
//        private String pic;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getPic() {
//            return pic;
//        }
//
//        public void setPic(String pic) {
//            this.pic = pic;
//        }
//    }
}
