package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Save_More_Bean {


    /**
     * msg : 成功
     * code : 200
     * data : {"list":[{"id":"15","title":"家禽测试","pic":"/data/img/1810/5bbc61824d0e2.png","sponsor":"农场协会","dizhi":"中国北京市大兴区瀛海镇德茂地铁站B1东北口东北100米","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"13","title":"北京海淀农场13","pic":"/data/img/1810/5bbc61824d0e2.png","sponsor":"农场协会","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"},{"id":"12","title":"北京海淀农场12","pic":"/data/img/1810/5bbc61824d0e2.png","sponsor":"农场协会","dizhi":"大兴区广顺路58号（大兴农场）","des":"农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 15
             * title : 家禽测试
             * pic : /data/img/1810/5bbc61824d0e2.png
             * sponsor : 农场协会
             * dizhi : 中国北京市大兴区瀛海镇德茂地铁站B1东北口东北100米
             * des : 农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介农场简介
             */

            private String id;
            private String title;
            private String pic;
            private String sponsor;
            private String dizhi;
            private String des;

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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getSponsor() {
                return sponsor;
            }

            public void setSponsor(String sponsor) {
                this.sponsor = sponsor;
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
}
