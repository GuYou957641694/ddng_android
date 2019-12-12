package com.bigpumpkin.app.ddng_android.bean;

public class NewBuyNowOrdersBean {


    /**
     * msg : success
     * code : 200
     * data : {"orde_id":2}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orde_id : 2
         */

        private String orde_id;

        public String getOrde_id() {
            return orde_id;
        }

        public void setOrde_id(String orde_id) {
            this.orde_id = orde_id;
        }
    }
}
