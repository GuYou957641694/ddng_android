package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Address_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"1","uid":"1","tel":"13146463762","isindex":"1","sheng":"北京","shi":"北京市","qu":"朝阳区","address":"百子湾后现代城5A1006室","youbian":"10000","name":"董先生"},{"id":"3","uid":"1","tel":"13146463762","isindex":"2","sheng":"北京","shi":"北京市","qu":"朝阳区","address":"百子湾后现代城5A1006室","youbian":"10000","name":"董先生"}]
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
         * uid : 1
         * tel : 13146463762
         * isindex : 1
         * sheng : 北京
         * shi : 北京市
         * qu : 朝阳区
         * address : 百子湾后现代城5A1006室
         * youbian : 10000
         * name : 董先生
         */

        private String id;
        private String uid;
        private String tel;
        private int isindex;
        private String sheng;
        private String shi;
        private String qu;
        private String address;
        private String youbian;
        private String name;

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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getIsindex() {
            return isindex;
        }

        public void setIsindex(int isindex) {
            this.isindex = isindex;
        }

        public String getSheng() {
            return sheng;
        }

        public void setSheng(String sheng) {
            this.sheng = sheng;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getQu() {
            return qu;
        }

        public void setQu(String qu) {
            this.qu = qu;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getYoubian() {
            return youbian;
        }

        public void setYoubian(String youbian) {
            this.youbian = youbian;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
