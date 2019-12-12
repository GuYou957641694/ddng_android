package com.bigpumpkin.app.ddng_android.bean;

public class GenerateOrdersBean {


    /**
     * msg : 成功
     * code : 200
     * data : {"number":"201909171586746","price":216.4}
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
         * number : 201909171586746
         * price : 216.4
         */

        private String number;
        private String price;
        private String boy;


        public String getBoy() {
            return boy;
        }

        public void setBoy(String boy) {
            this.boy = boy;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
