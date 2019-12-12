package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Fruit_Bean {

    /**
     * msg : success
     * code : 200
     * data : [{"id":"3","title":"北京富士苹果3","chanliang":"100","pic":"/data/img/1901/5c341411223c8.png","price":"415.00"},{"id":"5","title":"北京富士果树5","chanliang":"100","pic":"/data/img/1901/5c3414897ed4f.png","price":"415.00"},{"id":"51","title":"北京富士苹果3","chanliang":"100","pic":"/data/img/1901/5c341411223c8.png","price":"415.00"},{"id":"53","title":"北京富士果树5","chanliang":"100","pic":"/data/img/1901/5c3414897ed4f.png","price":"463.00"},{"id":"88","title":"北京富士苹果3","chanliang":"100","pic":"/data/img/1901/5c341411223c8.png","price":"463.00"},{"id":"90","title":"北京富士果树5","chanliang":"100","pic":"/data/img/1901/5c3414897ed4f.png","price":"463.00"}]
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
         * id : 3
         * title : 北京富士苹果3
         * chanliang : 100
         * pic : /data/img/1901/5c341411223c8.png
         * price : 415.00
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
