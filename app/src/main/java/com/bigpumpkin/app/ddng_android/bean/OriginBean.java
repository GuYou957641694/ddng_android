package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class OriginBean {


    /**
     * msg : success
     * code : 200
     * data : {"area":{"text":"简介","pic":"/data/img/1910/5d9c33999e39e.jpg"},"shop":[{"id":"6","title":"荔枝果树","pic":"/data/img/1910/5da92e25775bb.jpg","price":"1.00","chanliang":"100"},{"id":"8","title":"芒果果树","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"},{"id":"10","title":"果树10","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"},{"id":"274","title":"香蕉果树","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"},{"id":"276","title":"荔枝果树","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"},{"id":"533","title":"香蕉果树","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"}]}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * area : {"text":"简介","pic":"/data/img/1910/5d9c33999e39e.jpg"}
         * shop : [{"id":"6","title":"荔枝果树","pic":"/data/img/1910/5da92e25775bb.jpg","price":"1.00","chanliang":"100"},{"id":"8","title":"芒果果树","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"},{"id":"10","title":"果树10","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"},{"id":"274","title":"香蕉果树","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"},{"id":"276","title":"荔枝果树","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"},{"id":"533","title":"香蕉果树","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","chanliang":"100"}]
         */

        private AreaBean area;
        private List<ShopBean> shop;

        public AreaBean getArea() {
            return area;
        }

        public void setArea(AreaBean area) {
            this.area = area;
        }

        public List<ShopBean> getShop() {
            return shop;
        }

        public void setShop(List<ShopBean> shop) {
            this.shop = shop;
        }

        public static class AreaBean {
            /**
             * text : 简介
             * pic : /data/img/1910/5d9c33999e39e.jpg
             */

            private String text;
            private String pic;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class ShopBean {
            /**
             * id : 6
             * title : 荔枝果树
             * pic : /data/img/1910/5da92e25775bb.jpg
             * price : 1.00
             * chanliang : 100
             */

            private String id;
            private String title;
            private String pic;
            private String price;
            private String chanliang;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getChanliang() {
                return chanliang;
            }

            public void setChanliang(String chanliang) {
                this.chanliang = chanliang;
            }
        }
    }
}
