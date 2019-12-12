package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Farm_index_productBean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"538","title":"越南富士9","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","gg_id":"3","sales_volume":"25"},{"id":"260","title":"生产者说27","chanliang":"100","pic":"/data/img/1901/5c3414897ed4f.png","price":"1.00","gg_id":"3","sales_volume":"356"},{"id":"519","title":"生产者说27","chanliang":"100","pic":"/data/img/1901/5c3414897ed4f.png","price":"1.00","gg_id":"3","sales_volume":"356"},{"id":"9","title":"越南富士9","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","gg_id":"3","sales_volume":"25"},{"id":"279","title":"越南富士9","chanliang":"100","pic":"/data/img/1811/5bfb9a1f70711.png","price":"1.00","gg_id":"3","sales_volume":"25"},{"id":"27","title":"生产者说27","chanliang":"100","pic":"/data/img/1901/5c3414897ed4f.png","price":"1.00","gg_id":"3","sales_volume":"356"}]
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
         * id : 538
         * title : 越南富士9
         * chanliang : 100
         * pic : /data/img/1811/5bfb9a1f70711.png
         * price : 1.00
         * gg_id : 3
         * sales_volume : 25
         */

        private String id;
        private String title;
        private String chanliang;
        private String pic;
        private String price;
        private String gg_id;
        private String sales_volume;

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

        public String getGg_id() {
            return gg_id;
        }

        public void setGg_id(String gg_id) {
            this.gg_id = gg_id;
        }

        public String getSales_volume() {
            return sales_volume;
        }

        public void setSales_volume(String sales_volume) {
            this.sales_volume = sales_volume;
        }
    }
}
