package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Store_SearchBean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"1","title":"北京朝阳农场1","pic3":"/data/img/1910/5da6bf70272e7.jpg","shop":[{"id":"4","title":"香蕉果树","zhonglei":"苹果","sales_volume":"9","pic":"/data/img/1910/5da6866f5f67e.jpg","chandi":"山东","price":"1.00"},{"id":"6","title":"荔枝果树","zhonglei":"苹果","sales_volume":"8","pic":"/data/img/1910/5da6865d392c7.jpg","chandi":"北京","price":"1.00"},{"id":"8","title":"芒果果树","zhonglei":"苹果","sales_volume":"11","pic":"/data/img/1811/5bfb9a1f70711.png","chandi":"北京","price":"1.00"}]},{"id":"2","title":"北京海淀农场2","pic3":"/data/img/1901/5c330ff1c9804.png","shop":null},{"id":"3","title":"北京海淀农场3","pic3":"/data/img/1901/5c331001dd58d.png","shop":null},{"id":"6","title":"北京海淀农场6","pic3":"/data/img/1901/5c33100d056a0.png","shop":null}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 北京朝阳农场1
         * pic3 : /data/img/1910/5da6bf70272e7.jpg
         * shop : [{"id":"4","title":"香蕉果树","zhonglei":"苹果","sales_volume":"9","pic":"/data/img/1910/5da6866f5f67e.jpg","chandi":"山东","price":"1.00"},{"id":"6","title":"荔枝果树","zhonglei":"苹果","sales_volume":"8","pic":"/data/img/1910/5da6865d392c7.jpg","chandi":"北京","price":"1.00"},{"id":"8","title":"芒果果树","zhonglei":"苹果","sales_volume":"11","pic":"/data/img/1811/5bfb9a1f70711.png","chandi":"北京","price":"1.00"}]
         */

        private String id;
        private String title;
        private String pic3;
        private List<ShopBean> shop;

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

        public String getPic3() {
            return pic3;
        }

        public void setPic3(String pic3) {
            this.pic3 = pic3;
        }

        public List<ShopBean> getShop() {
            return shop;
        }

        public void setShop(List<ShopBean> shop) {
            this.shop = shop;
        }

        public static class ShopBean {
            /**
             * id : 4
             * title : 香蕉果树
             * zhonglei : 苹果
             * sales_volume : 9
             * pic : /data/img/1910/5da6866f5f67e.jpg
             * chandi : 山东
             * price : 1.00
             */

            private String id;
            private String title;
            private String zhonglei;
            private String sales_volume;
            private String pic;
            private String chandi;
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

            public String getZhonglei() {
                return zhonglei;
            }

            public void setZhonglei(String zhonglei) {
                this.zhonglei = zhonglei;
            }

            public String getSales_volume() {
                return sales_volume;
            }

            public void setSales_volume(String sales_volume) {
                this.sales_volume = sales_volume;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getChandi() {
                return chandi;
            }

            public void setChandi(String chandi) {
                this.chandi = chandi;
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
