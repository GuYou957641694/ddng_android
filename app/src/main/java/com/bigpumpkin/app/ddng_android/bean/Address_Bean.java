package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Address_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"3","uid":"2","tel":"15611036613","isindex":"1","sheng":"天津市","sheng_code":"120000","shi":"市辖区","shi_code":"120100","qu":"北辰区 ","qu_code":"120113","address":"哈哈","youbian":"0","name":"贾东升"}]
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
         * id : 3
         * uid : 2
         * tel : 15611036613
         * isindex : 1
         * sheng : 天津市
         * sheng_code : 120000
         * shi : 市辖区
         * shi_code : 120100
         * qu : 北辰区
         * qu_code : 120113
         * address : 哈哈
         * youbian : 0
         * name : 贾东升
         */

        private String id;
        private String uid;
        private String tel;
        private int isindex;
        private String sheng;
        private String sheng_code;
        private String shi;
        private String shi_code;
        private String qu;
        private String qu_code;
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

        public String getSheng_code() {
            return sheng_code;
        }

        public void setSheng_code(String sheng_code) {
            this.sheng_code = sheng_code;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getShi_code() {
            return shi_code;
        }

        public void setShi_code(String shi_code) {
            this.shi_code = shi_code;
        }

        public String getQu() {
            return qu;
        }

        public void setQu(String qu) {
            this.qu = qu;
        }

        public String getQu_code() {
            return qu_code;
        }

        public void setQu_code(String qu_code) {
            this.qu_code = qu_code;
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
