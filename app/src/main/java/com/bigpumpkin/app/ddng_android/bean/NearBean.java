package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class NearBean {

    /**
     * msg : success
     * code : 200
     * data : [{"id":"15","title":"家禽测试","longitude":"39.7562790000","latitude":"116.4522780000","dizhi":"中国北京市大兴区瀛海镇德茂地铁站B1东北口东北100米","pic":"/data/img/1810/5bbc61824d0e2.png","km":10356.262},{"id":"1","title":"北京朝阳农场1","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"2","title":"北京海淀农场2","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"3","title":"北京海淀农场3","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"6","title":"北京海淀农场6","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"7","title":"北京海淀农场7","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"8","title":"北京海淀农场8","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"9","title":"北京海淀农场9","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"10","title":"北京海淀农场10","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"11","title":"北京海淀农场11","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"12","title":"北京海淀农场12","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516},{"id":"13","title":"北京海淀农场13","longitude":"39.6779180000","latitude":"116.2367730000","dizhi":"大兴区广顺路58号（大兴农场）","pic":"/data/img/1810/5bbc61824d0e2.png","km":10341.516}]
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
         * id : 15
         * title : 家禽测试
         * longitude : 39.7562790000
         * latitude : 116.4522780000
         * dizhi : 中国北京市大兴区瀛海镇德茂地铁站B1东北口东北100米
         * pic : /data/img/1810/5bbc61824d0e2.png
         * km : 10356.262
         */

        private String id;
        private String title;
        private String longitude;
        private String latitude;
        private String dizhi;
        private String pic;
        private double km;

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

        public String getDizhi() {
            return dizhi;
        }

        public void setDizhi(String dizhi) {
            this.dizhi = dizhi;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public double getKm() {
            return km;
        }

        public void setKm(double km) {
            this.km = km;
        }
    }
}
