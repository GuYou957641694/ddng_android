package com.bigpumpkin.app.ddng_android.bean;

import com.google.gson.annotations.SerializedName;

public class WxPayBean {

    /**
     * msg : success
     * code : 200
     * data : {"appid":"wx0f147d4225f8491a","noncestr":"G59iwDV0J9fpvs35AUP9bmxc09Ejh684","package":"Sign=WXPay","partnerid":"1532379221","prepayid":"wx171619097411829ac1d6baaa1710602300","timestamp":"1568708349","sign":"13F9F7FB3D631FAB0370C15D153AC297"}
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
         * appid : wx0f147d4225f8491a
         * noncestr : G59iwDV0J9fpvs35AUP9bmxc09Ejh684
         * package : Sign=WXPay
         * partnerid : 1532379221
         * prepayid : wx171619097411829ac1d6baaa1710602300
         * timestamp : 1568708349
         * sign : 13F9F7FB3D631FAB0370C15D153AC297
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
