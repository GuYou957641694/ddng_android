package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Plant_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"4","title":"香蕉果树","chanliang":"100","pic":"/data/img/1909/5d8b72357124c.png","price":"489.00"},{"id":"6","title":"荔枝果树","chanliang":"100","pic":"/data/img/1909/5d8b724a2cac5.png","price":"497.00"},{"id":"8","title":"芒果果树","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"},{"id":"10","title":"果树10","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"},{"id":"52","title":"香蕉果树","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"},{"id":"54","title":"荔枝果树","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"},{"id":"56","title":"芒果果树","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"},{"id":"58","title":"果树10","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"},{"id":"89","title":"香蕉果树","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"},{"id":"91","title":"荔枝果树","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"497.00"}]
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
         * title : 香蕉果树
         * chanliang : 100
         * pic : /data/img/1909/5d8b72357124c.png
         * price : 489.00
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
