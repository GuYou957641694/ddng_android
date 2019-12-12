package com.bigpumpkin.app.ddng_android.bean;

import com.google.gson.annotations.SerializedName;

public class CollagesBean {


    /**
     * msg : SUCCESS
     * code : 200
     * data : {"id":41,"class":"Initiate"}
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
         * id : 41
         * class : Initiate
         */

        private int id;
        @SerializedName("class")
        private String classX;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }
    }
}
