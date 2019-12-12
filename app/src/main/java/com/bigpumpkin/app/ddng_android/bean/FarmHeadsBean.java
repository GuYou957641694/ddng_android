package com.bigpumpkin.app.ddng_android.bean;

public class FarmHeadsBean {


    /**
     * msg : success
     * code : 200
     * data : {"Atten":"2","essay":{"id":"8","title":"北京海淀农场8","dizhi":"大兴区广顺路58号（大兴农场）","longitude":"39.6779180000","latitude":"116.2367730000","pic3":"/data/img/1901/5c330ff1c9804.png"},"grade":"4"}
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
         * Atten : 2
         * essay : {"id":"8","title":"北京海淀农场8","dizhi":"大兴区广顺路58号（大兴农场）","longitude":"39.6779180000","latitude":"116.2367730000","pic3":"/data/img/1901/5c330ff1c9804.png"}
         * grade : 4
         */

        private String Atten;
        private EssayBean essay;
        private String grade;

        public String getAtten() {
            return Atten;
        }

        public void setAtten(String Atten) {
            this.Atten = Atten;
        }

        public EssayBean getEssay() {
            return essay;
        }

        public void setEssay(EssayBean essay) {
            this.essay = essay;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public static class EssayBean {
            /**
             * id : 8
             * title : 北京海淀农场8
             * dizhi : 大兴区广顺路58号（大兴农场）
             * longitude : 39.6779180000
             * latitude : 116.2367730000
             * pic3 : /data/img/1901/5c330ff1c9804.png
             */

            private String id;
            private String title;
            private String dizhi;
            private String longitude;
            private String latitude;
            private String pic3;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDizhi() {
                return dizhi;
            }

            public void setDizhi(String dizhi) {
                this.dizhi = dizhi;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getPic3() {
                return pic3;
            }

            public void setPic3(String pic3) {
                this.pic3 = pic3;
            }
        }
    }
}
