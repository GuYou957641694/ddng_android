package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Wisdoms_AreasBean {

    /**
     * msg : success
     * code : 200
     * data : [{"id":"3","pic":"/data/img/1810/5bbc61824d0e2.png","title":"北京海淀农场3","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"6","pic":"/data/img/1810/5bbc61824d0e2.png","title":"北京海淀农场6","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"7","pic":"/data/img/1810/5bbc61824d0e2.png","title":"北京海淀农场7","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"8","pic":"/data/img/1810/5bbc61824d0e2.png","title":"北京海淀农场8","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"9","pic":"/data/img/1810/5bbc61824d0e2.png","title":"北京海淀农场9","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"10","pic":"/data/img/1810/5bbc61824d0e2.png","title":"北京海淀农场10","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"11","pic":"/data/img/1810/5bbc61824d0e2.png","title":"北京海淀农场11","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"12","pic":"/data/img/1810/5bbc61824d0e2.png","title":"北京海淀农场12","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"}]
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
         * pic : /data/img/1810/5bbc61824d0e2.png
         * title : 北京海淀农场3
         * dizhi : 大兴区广顺路58号（大兴农场）
         * des : 农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介
         */

        private String id;
        private String pic;
        private String title;
        private String dizhi;
        private String des;

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

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
