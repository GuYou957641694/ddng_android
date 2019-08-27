package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Grow_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"1","fidname":"北京朝阳农场1","pz_title":"越南红富士1","price":"100.00","pic":"/data/img/1811/5bfb9a1f70711.png","ctime":"2019-08-3","etime":"2019-08-18","type":"拼","type_v":"single_tree"},{"id":"2","fidname":"北京朝阳农场1","pz_title":"越南红富士1","price":"100.00","pic":"/data/img/1811/5bfb9a1f70711.png","ctime":"2019-08-3","etime":"2019-08-18","type":"拼","type_v":"single_tree"},{"id":"4","fidname":"北京朝阳农场1","pz_title":"越南红富士1","price":"100.00","pic":"/data/img/1901/5c3414897ed4f.png","ctime":"2019-08-12","etime":"2019-08-16","type":"拼","type_v":"single_tree"}]
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
         * fidname : 北京朝阳农场1
         * pz_title : 越南红富士1
         * price : 100.00
         * pic : /data/img/1811/5bfb9a1f70711.png
         * ctime : 2019-08-3
         * etime : 2019-08-18
         * type : 拼
         * type_v : single_tree
         */

        private String id;
        private String fidname;
        private String pz_title;
        private String price;
        private String pic;
        private String ctime;
        private String etime;
        private String type;
        private String type_v;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFidname() {
            return fidname;
        }

        public void setFidname(String fidname) {
            this.fidname = fidname;
        }

        public String getPz_title() {
            return pz_title;
        }

        public void setPz_title(String pz_title) {
            this.pz_title = pz_title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_v() {
            return type_v;
        }

        public void setType_v(String type_v) {
            this.type_v = type_v;
        }
    }
}
