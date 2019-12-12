package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Farm_CouponsBean {


    /**
     * msg : success
     * code : 200
     * data : {"essay":{"id":"1","pic2":"/data/img/1910/5d9fed74893ee.mp4"},"coupon":[{"id":"5","man":"200","jian":"10","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"6","man":"200","jian":"5","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"5元优惠"},{"id":"7","man":"200","jian":"3","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"8","man":"1000","jian":"50","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"4","man":"300","jian":"15","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"9","man":"100","jian":"2","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"10","man":"100","jian":"3","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠"}],"weather":{"water":"0.00","wet":"16","hum":"49","light":"721"}}
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
         * essay : {"id":"1","pic2":"/data/img/1910/5d9fed74893ee.mp4"}
         * coupon : [{"id":"5","man":"200","jian":"10","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"6","man":"200","jian":"5","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"5元优惠"},{"id":"7","man":"200","jian":"3","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"8","man":"1000","jian":"50","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"4","man":"300","jian":"15","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"9","man":"100","jian":"2","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠"},{"id":"10","man":"100","jian":"3","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠"}]
         * weather : {"water":"0.00","wet":"16","hum":"49","light":"721"}
         */

        private EssayBean essay;
        private WeatherBean weather;
        private List<CouponBean> coupon;

        public EssayBean getEssay() {
            return essay;
        }

        public void setEssay(EssayBean essay) {
            this.essay = essay;
        }

        public WeatherBean getWeather() {
            return weather;
        }

        public void setWeather(WeatherBean weather) {
            this.weather = weather;
        }

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public static class EssayBean {
            /**
             * id : 1
             * pic2 : /data/img/1910/5d9fed74893ee.mp4
             */

            private String id;
            private String pic2;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPic2() {
                return pic2;
            }

            public void setPic2(String pic2) {
                this.pic2 = pic2;
            }
        }

        public static class WeatherBean {
            /**
             * water : 0.00
             * wet : 16
             * hum : 49
             * light : 721
             */

            private String water;
            private String wet;
            private String hum;
            private String light;

            public String getWater() {
                return water;
            }

            public void setWater(String water) {
                this.water = water;
            }

            public String getWet() {
                return wet;
            }

            public void setWet(String wet) {
                this.wet = wet;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getLight() {
                return light;
            }

            public void setLight(String light) {
                this.light = light;
            }
        }

        public static class CouponBean {
            /**
             * id : 5
             * man : 200
             * jian : 10
             * ctime : 1548864000
             * etime : 1602345600
             * nc_id : 1
             * title : 惠
             */

            private String id;
            private String man;
            private String jian;
            private String ctime;
            private String etime;
            private String nc_id;
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMan() {
                return man;
            }

            public void setMan(String man) {
                this.man = man;
            }

            public String getJian() {
                return jian;
            }

            public void setJian(String jian) {
                this.jian = jian;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getEtime() {
                return etime;
            }

            public void setEtime(String etime) {
                this.etime = etime;
            }

            public String getNc_id() {
                return nc_id;
            }

            public void setNc_id(String nc_id) {
                this.nc_id = nc_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
