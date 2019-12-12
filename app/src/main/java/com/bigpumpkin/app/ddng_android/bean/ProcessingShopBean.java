package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class ProcessingShopBean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"11","title":"农场深加工11","pic":"/data/img/1901/5c3414897ed4f.png","chanliang":"100","fidname":"北京海淀农场2","fid":"2","price":"453.00","gg_id":"9"},{"id":"12","title":"农场深加工12","pic":"/data/img/1901/5c341411223c8.png","chanliang":"100","fidname":"北京海淀农场2","fid":"2","price":"453.00","gg_id":"9"},{"id":"13","title":"农场深加工13","pic":"/data/img/1901/5c3414897ed4f.png","chanliang":"100","fidname":"北京朝阳农场1","fid":"1","price":"473.00","gg_id":"9431"},{"id":"14","title":"农场深加工14","pic":"/data/img/1901/5c341411223c8.png","chanliang":"100","fidname":"北京朝阳农场1","fid":"1","price":"473.00","gg_id":"9431"},{"id":"15","title":"农场深加工15","pic":"/data/img/1811/5bfb9a1f70711.png","chanliang":"100","fidname":"北京朝阳农场1","fid":"1","price":"495.00","gg_id":"13"},{"id":"281","title":"农场深加工11","pic":"/data/img/1901/5c3414897ed4f.png","chanliang":"100","fidname":"北京海淀农场2","fid":"2","price":"498.00","gg_id":"7814"},{"id":"282","title":"农场深加工12","pic":"/data/img/1901/5c341411223c8.png","chanliang":"100","fidname":"北京海淀农场2","fid":"2","price":"498.00","gg_id":"7814"},{"id":"283","title":"农场深加工13","pic":"/data/img/1901/5c3414897ed4f.png","chanliang":"100","fidname":"北京朝阳农场1","fid":"1","price":"498.00","gg_id":"7814"}]
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
         * id : 11
         * title : 农场深加工11
         * pic : /data/img/1901/5c3414897ed4f.png
         * chanliang : 100
         * fidname : 北京海淀农场2
         * fid : 2
         * price : 453.00
         * gg_id : 9
         */

        private String id;
        private String title;
        private String pic;
        private String chanliang;
        private String fidname;
        private String fid;
        private String price;
        private String gg_id;

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

        public String getChanliang() {
            return chanliang;
        }

        public void setChanliang(String chanliang) {
            this.chanliang = chanliang;
        }

        public String getFidname() {
            return fidname;
        }

        public void setFidname(String fidname) {
            this.fidname = fidname;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
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
