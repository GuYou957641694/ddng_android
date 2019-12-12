package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Commodity_Recommended_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"95","pic":"/data/img/1811/5bfb9a1f70711.png","title":"果树10","price":"1.00","gg_id":"3"},{"id":"10","pic":"/data/img/1811/5bfb9a1f70711.png","title":"果树10","price":"1.00","gg_id":"3"},{"id":"8","pic":"/data/img/1811/5bfb9a1f70711.png","title":"芒果果树","price":"1.00","gg_id":"3"},{"id":"6","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","price":"1.00","gg_id":"11321"},{"id":"58","pic":"/data/img/1811/5bfb9a1f70711.png","title":"果树10","price":"1.00","gg_id":"3"},{"id":"4","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","price":"1.00","gg_id":"11320"}]
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
         * id : 95
         * pic : /data/img/1811/5bfb9a1f70711.png
         * title : 果树10
         * price : 1.00
         * gg_id : 3
         */

        private String id;
        private String pic;
        private String title;
        private String price;
        private String gg_id;
        private String chanliang;

        public String getChanliang() {
            return chanliang;
        }

        public void setChanliang(String chanliang) {
            this.chanliang = chanliang;
        }

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
    }
}
