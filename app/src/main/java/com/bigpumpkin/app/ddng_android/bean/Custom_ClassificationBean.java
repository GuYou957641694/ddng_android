package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Custom_ClassificationBean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"4","pic":"/data/img/1910/5da6866f5f67e.jpg","title":"香蕉果树","chanliang":"100","price":"1.00"},{"id":"6","pic":"/data/img/1910/5da6865d392c7.jpg","title":"荔枝果树","chanliang":"100","price":"1.00"},{"id":"8","pic":"/data/img/1811/5bfb9a1f70711.png","title":"芒果果树","chanliang":"100","price":"1.00"},{"id":"10","pic":"/data/img/1811/5bfb9a1f70711.png","title":"果树10","chanliang":"100","price":"1.00"},{"id":"274","pic":"/data/img/1811/5bfb9a1f70711.png","title":"香蕉果树","chanliang":"100","price":"1.00"},{"id":"276","pic":"/data/img/1811/5bfb9a1f70711.png","title":"荔枝果树","chanliang":"100","price":"1.00"},{"id":"533","pic":"/data/img/1811/5bfb9a1f70711.png","title":"香蕉果树","chanliang":"100","price":"1.00"},{"id":"278","pic":"/data/img/1811/5bfb9a1f70711.png","title":"芒果果树","chanliang":"100","price":"1.00"}]
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
         * id : 4
         * pic : /data/img/1910/5da6866f5f67e.jpg
         * title : 香蕉果树
         * chanliang : 100
         * price : 1.00
         */

        private String id;
        private String pic;
        private String title;
        private String chanliang;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
