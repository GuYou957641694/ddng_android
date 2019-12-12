package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class MilkMoreBean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"987","pic":"/data/img/1910/5dba5298e07be.jpg","title":"奶制品  1     ","chanliang":"111","price":"1.00"},{"id":"988","pic":"/data/img/1910/5dba5298e07be.jpg","title":"奶制品  1     ","chanliang":"111","price":"1.00"},{"id":"990","pic":"/data/img/1910/5dba5298e07be.jpg","title":"奶制品  1     ","chanliang":"111","price":"1.00"},{"id":"991","pic":"/data/img/1910/5dba5298e07be.jpg","title":"奶制品  1     ","chanliang":"111","price":"1.00"},{"id":"992","pic":"/data/img/1910/5dba5298e07be.jpg","title":"奶制品  1     ","chanliang":"111","price":"1.00"},{"id":"993","pic":"/data/img/1910/5dba5298e07be.jpg","title":"奶制品  1     ","chanliang":"111","price":"1.00"}]
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
         * id : 987
         * pic : /data/img/1910/5dba5298e07be.jpg
         * title : 奶制品  1
         * chanliang : 111
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
