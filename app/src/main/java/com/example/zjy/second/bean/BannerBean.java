package com.example.zjy.second.bean;

import java.util.List;

/**
 * Created by ZJY on 2017/11/29.
 */

public class BannerBean {
    /**
     * id : 1
     * name : 首页顶置
     * description : 首页轮播图
     * items : [{"goods_id":5,"goods":{"id":5,"title":"小埋","description":"卡哇伊的小埋，你值得拥有","type_way":1,"keywords":"小埋","price":123,"tell":123,"url":["http://119.23.237.193/hzl/Second/public/images/1@theme-head.png","http://119.23.237.193/hzl/Second/public/images/banner-1a.png","http://119.23.237.193/hzl/Second/public/images/banner-2a.png"],"click":0,"category_id":1,"uid":1}},{"goods_id":4,"goods":{"id":4,"title":"小白","description":"不是你的小白，是我的小白","type_way":1,"keywords":"小白","price":111,"tell":111,"url":["http://119.23.237.193/hzl/Second/public/images/banner-3a.png","http://119.23.237.193/hzl/Second/public/images/banner-1a.png","http://119.23.237.193/hzl/Second/public/images/banner-2a.png"],"click":0,"category_id":1,"uid":1}},{"goods_id":3,"goods":{"id":3,"title":"小兰","description":"柯南的小兰，够意思了吧","type_way":1,"keywords":"小兰","price":500,"tell":110,"url":["http://119.23.237.193/hzl/Second/public/images/banner-2a.png","http://119.23.237.193/hzl/Second/public/images/banner-1a.png","http://119.23.237.193/hzl/Second/public/images/banner-3a.png"],"click":2,"category_id":1,"uid":1}},{"goods_id":2,"goods":{"id":2,"title":"立白洗衣粉","description":"立白洗衣粉，洗掉你的黑炭头","type_way":2,"keywords":"立白洗衣粉","price":11,"tell":120,"url":["http://119.23.237.193/hzl/Second/public/images/2@theme.png","http://119.23.237.193/hzl/Second/public/images/banner-1a.png","http://119.23.237.193/hzl/Second/public/images/banner-2a.png"],"click":2,"category_id":2,"uid":1}},{"goods_id":1,"goods":{"id":1,"title":"黑人牙膏","description":"黑人牙膏，黑掉你的牙齿","type_way":1,"keywords":"黑人牙膏","price":11,"tell":110,"url":["http://119.23.237.193/hzl/Second/public/images/banner-1a.png","http://119.23.237.193/hzl/Second/public/images/banner-2a.png","http://119.23.237.193/hzl/Second/public/images/banner-3a.png"],"click":38,"category_id":2,"uid":1}}]
     */

    private int id;
    private String name;
    private String description;
    private List<ItemsBean> items;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * goods_id : 5
         * goods : {"id":5,"title":"小埋","description":"卡哇伊的小埋，你值得拥有","type_way":1,"keywords":"小埋","price":123,"tell":123,"url":["http://119.23.237.193/hzl/Second/public/images/1@theme-head.png","http://119.23.237.193/hzl/Second/public/images/banner-1a.png","http://119.23.237.193/hzl/Second/public/images/banner-2a.png"],"click":0,"category_id":1,"uid":1}
         */

        private int goods_id;
        private GoodsBean goods;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 5
             * title : 小埋
             * description : 卡哇伊的小埋，你值得拥有
             * type_way : 1
             * keywords : 小埋
             * price : 123
             * tell : 123
             * url : ["http://119.23.237.193/hzl/Second/public/images/1@theme-head.png","http://119.23.237.193/hzl/Second/public/images/banner-1a.png","http://119.23.237.193/hzl/Second/public/images/banner-2a.png"]
             * click : 0
             * category_id : 1
             * uid : 1
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
    }
}
