package com.bigpumpkin.app.ddng_android.bean;


public class User_Bean {


    /**
     * msg : success
     * code : 200
     * data : {"ctime":"1545375293","zt":"2","pic":"/data/img/1908/5d493469971ca.jpg","name":"朝阳张场主2","integral":"4615","date_birth":"0000-00-00"}
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
         * ctime : 1545375293
         * zt : 2
         * pic : /data/img/1908/5d493469971ca.jpg
         * name : 朝阳张场主2
         * integral : 4615
         * date_birth : 0000-00-00
         */

        private String ctime;
        private String zt;
        private String pic;
        private String name;
        private String integral;
        private String date_birth;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getDate_birth() {
            return date_birth;
        }

        public void setDate_birth(String date_birth) {
            this.date_birth = date_birth;
        }
    }
}
