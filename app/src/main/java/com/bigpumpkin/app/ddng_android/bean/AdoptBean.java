package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class AdoptBean {


    /**
     * msg : 200
     * code : success
     * data : [{"id":"948","title":"家禽 测试","pic":"/data/img/1909/5d731893e7dcc.gif","price":"3.00","gg_id":"3","chanliang":"100-50"},{"id":"962","title":"家禽 测试","pic":"/data/img/1910/5d96d659edfc8.png","price":"1.00","gg_id":"3","chanliang":"100-50"},{"id":"964","title":"家禽 测试","pic":"/data/img/1910/5d96d659edfc8.png","price":"1.00","gg_id":"3","chanliang":"100-50"},{"id":"965","title":"家禽 测试","pic":"/data/img/1910/5d96d659edfc8.png","price":"1.00","gg_id":"3","chanliang":"100-50"},{"id":"966","title":"家禽 测试","pic":"/data/img/1910/5d96d659edfc8.png","price":"1.00","gg_id":"3","chanliang":"100-50"},{"id":"967","title":"家禽 测试","pic":"/data/img/1910/5d96d659edfc8.png","price":"1.00","gg_id":"3","chanliang":"100-50"}]
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
         * id : 948
         * title : 家禽 测试
         * pic : /data/img/1909/5d731893e7dcc.gif
         * price : 3.00
         * gg_id : 3
         * chanliang : 100-50
         */

        private String id;
        private String title;
        private String pic;
        private String price;
        private String gg_id;
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

        public String getGg_id() {
            return gg_id;
        }

        public void setGg_id(String gg_id) {
            this.gg_id = gg_id;
        }

        public String getChanliang() {
            return chanliang;
        }

        public void setChanliang(String chanliang) {
            this.chanliang = chanliang;
        }
    }
}
