package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class SearchBean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"4","title":"香蕉果树","zhonglei":"苹果","sales_volume":"9","pic":"/data/img/1910/5da6866f5f67e.jpg","chandi":"山东","price":"1.00","chanliang":"100"},{"id":"6","title":"荔枝果树","zhonglei":"苹果","sales_volume":"8","pic":"/data/img/1910/5da6865d392c7.jpg","chandi":"北京","price":"1.00","chanliang":"100"},{"id":"8","title":"芒果果树","zhonglei":"苹果","sales_volume":"11","pic":"/data/img/1811/5bfb9a1f70711.png","chandi":"北京","price":"1.00","chanliang":"100"},{"id":"10","title":"果树10","zhonglei":"苹果","sales_volume":"33","pic":"/data/img/1811/5bfb9a1f70711.png","chandi":"北京","price":"1.00","chanliang":"100"},{"id":"274","title":"香蕉果树","zhonglei":"苹果","sales_volume":"9","pic":"/data/img/1811/5bfb9a1f70711.png","chandi":"北京","price":"1.00","chanliang":"100"},{"id":"276","title":"荔枝果树","zhonglei":"苹果","sales_volume":"8","pic":"/data/img/1811/5bfb9a1f70711.png","chandi":"北京","price":"1.00","chanliang":"100"},{"id":"533","title":"香蕉果树","zhonglei":"苹果","sales_volume":"9","pic":"/data/img/1811/5bfb9a1f70711.png","chandi":"北京","price":"1.00","chanliang":"100"},{"id":"278","title":"芒果果树","zhonglei":"苹果","sales_volume":"11","pic":"/data/img/1811/5bfb9a1f70711.png","chandi":"北京","price":"1.00","chanliang":"100"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;
    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

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
         * zhonglei : 苹果
         * sales_volume : 9
         * pic : /data/img/1910/5da6866f5f67e.jpg
         * chandi : 山东
         * price : 1.00
         * chanliang : 100
         */

        private String id;
        private String title;
        private String zhonglei;
        private String sales_volume;
        private String pic;
        private String chandi;
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

        public String getChanliang() {
            return chanliang;
        }

        public void setChanliang(String chanliang) {
            this.chanliang = chanliang;
        }
    }
}
