package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Collections_Bean {

    /**
     * msg : 成功
     * code : 200
     * data : [{"sp_id":"3","id":"3","uid":"1","s_id":"3","pic":"/data/img/1901/5c341411223c8.png1","gg_title":"100-200斤1","pz_title":"泰国草莓1","price":"100.00","title":"北京富士苹果3","zt":"1"},{"sp_id":"8","id":"8","uid":"1","s_id":"8","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"100-200斤","pz_title":"海南岛芒果","price":"100.00","title":"芒果果树","zt":"1"},{"sp_id":"49","id":"49","uid":"1","s_id":"49","pic":"/data/img/1904/5cab02cb69d0e.png","gg_title":"60-70公斤","pz_title":"青柠","price":"42.00","title":"圣女果","zt":"1"},{"sp_id":"5","id":"5","uid":"1","s_id":"5","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"30-40公斤","pz_title":"蓝莓","price":"41.00","title":"北京富士果树5","zt":"1"},{"sp_id":"539","id":"539","uid":"1","s_id":"539","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"80-90公斤","pz_title":"猕猴桃","price":"60.00","title":"果树10","zt":"1"},{"sp_id":"242","id":"242","uid":"1","s_id":"242","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"80-90公斤","pz_title":"君迁子","price":"18.00","title":"越南富士9","zt":"1"},{"sp_id":"390","id":"390","uid":"1","s_id":"390","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"90-100公斤","pz_title":"枇杷","price":"16.00","title":"越南富士9","zt":"1"}]
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
         * sp_id : 3
         * id : 3
         * uid : 1
         * s_id : 3
         * pic : /data/img/1901/5c341411223c8.png1
         * gg_title : 100-200斤1
         * pz_title : 泰国草莓1
         * price : 100.00
         * title : 北京富士苹果3
         * zt : 1
         */

        private String sp_id;
        private String id;
        private String uid;
        private String s_id;
        private String pic;
        private String gg_title;
        private String pz_title;
        private String price;
        private String title;
        private String zt;

        public String getSp_id() {
            return sp_id;
        }

        public void setSp_id(String sp_id) {
            this.sp_id = sp_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getS_id() {
            return s_id;
        }

        public void setS_id(String s_id) {
            this.s_id = s_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getGg_title() {
            return gg_title;
        }

        public void setGg_title(String gg_title) {
            this.gg_title = gg_title;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }
    }
}
