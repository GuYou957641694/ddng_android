package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class MeatBean {

    /**
     * msg : success
     * code : 200
     * data : {"shop":[{"id":"948","title":"家禽 测试","chanliang":"100-50","pic":"/data/img/1909/5d731893e7dcc.gif","fidname":"家禽测试","price":"10.00"},{"id":"962","title":"家禽 测试","chanliang":"100-50","pic":"/data/img/1909/5d731893e7dcc.gif","fidname":"家禽测试","price":"10.00"},{"id":"964","title":"家禽 测试","chanliang":"100-50","pic":"/data/img/1909/5d731893e7dcc.gif","fidname":"家禽测试","price":"10.00"},{"id":"965","title":"家禽 测试","chanliang":"100-50","pic":"/data/img/1909/5d731893e7dcc.gif","fidname":"家禽测试","price":"10.00"},{"id":"966","title":"家禽 测试","chanliang":"100-50","pic":"/data/img/1909/5d731893e7dcc.gif","fidname":"家禽测试","price":"10.00"},{"id":"967","title":"家禽 测试","chanliang":"100-50","pic":"/data/img/1909/5d731893e7dcc.gif","fidname":"家禽测试","price":"10.00"}]}
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
        private List<ShopBean> shop;

        public List<ShopBean> getShop() {
            return shop;
        }

        public void setShop(List<ShopBean> shop) {
            this.shop = shop;
        }

        public static class ShopBean {
            /**
             * id : 948
             * title : 家禽 测试
             * chanliang : 100-50
             * pic : /data/img/1909/5d731893e7dcc.gif
             * fidname : 家禽测试
             * price : 10.00
             */

            private String id;
            private String title;
            private String chanliang;
            private String pic;
            private String fidname;
            private String price;

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

            public String getChanliang() {
                return chanliang;
            }

            public void setChanliang(String chanliang) {
                this.chanliang = chanliang;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getFidname() {
                return fidname;
            }

            public void setFidname(String fidname) {
                this.fidname = fidname;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }

}
