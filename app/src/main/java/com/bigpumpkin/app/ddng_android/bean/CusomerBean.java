package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class CusomerBean {


    /**
     * msg : success
     * code : 200
     * data : [{"order_id":"1","cp_id":"242","gg_title":"60-70公斤","pz_title":"沙果","pay_price":"2.00"}]
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
         * order_id : 1
         * cp_id : 242
         * gg_title : 60-70公斤
         * pz_title : 沙果
         * pay_price : 2.00
         */

        private String title;
        private String order_id;
        private String cp_id;
        private String gg_title;
        private String pz_title;
        private String pay_price;
        private String pic;

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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getCp_id() {
            return cp_id;
        }

        public void setCp_id(String cp_id) {
            this.cp_id = cp_id;
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

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }
    }
}
