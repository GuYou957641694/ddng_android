package com.bigpumpkin.app.ddng_android.bean;

public class Logs_Bean {
    /**
     * msg : 登录成功
     * code : 200
     * data : {"appid":"e015137bc5bdda26daefd4eebc468d3e","appsecret":"99f10e52c620589b828bbfa1d9feafeb"}
     */

    private String msg;
    private String code;
    private Log_Bean.DataBean data;

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

    public Log_Bean.DataBean getData() {
        return data;
    }

    public void setData(Log_Bean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appid : e015137bc5bdda26daefd4eebc468d3e
         * appsecret : 99f10e52c620589b828bbfa1d9feafeb
         */

        private String appid;
        private String appsecret;
        private String id;
        private String pic;
        private String name;
        private String sex;
        private String type;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
