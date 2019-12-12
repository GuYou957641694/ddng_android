package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Farm_Recommended_Bean {


    /**
     * msg : success
     * code : 200
     * data : {"shop":[{"id":"9","title":"越南富士9","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"495.00"},{"id":"5","title":"北京富士果树5","chanliang":"100","pic":"/data/img/1901/5c3414897ed4f.png","price":"495.00"},{"id":"6","title":"荔枝果树","chanliang":"100","pic":"/data/img/1909/5d8b724a2cac5.png","price":"497.00"},{"id":"7","title":"北京富士果树7","chanliang":"100","pic":"/data/img/1901/5c341411223c8.png","price":"497.00"},{"id":"8","title":"芒果果树","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"},{"id":"10","title":"果树10","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"}]}
     */

    private String msg;
    private String code;
    private Farm_Recommend_Bean.DataBean data;

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

    public Farm_Recommend_Bean.DataBean getData() {
        return data;
    }

    public void setData(Farm_Recommend_Bean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<Farm_Recommend_Bean.DataBean.ShopBean> shop;

        public List<Farm_Recommend_Bean.DataBean.ShopBean> getShop() {
            return shop;
        }

        public void setShop(List<Farm_Recommend_Bean.DataBean.ShopBean> shop) {
            this.shop = shop;
        }

        public static class ShopBean {
            /**
             * id : 9
             * title : 越南富士9
             * chanliang : 100
             * pic : /data/img/1811/5bfb9a1f70711.png
             * price : 495.00
             */

            private String id;
            private String title;
            private String chanliang;
            private String pic;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }

}
