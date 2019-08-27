package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Focus_Bean {


    /**
     * msg : 成功
     * code : 200
     * data : [{"title":"北京海淀农场3","dizhi":"大兴区广顺路58号（大兴农场）","id":"3","pic":"/data/img/1810/5bbc61824d0e2.png","vaid":"2"}]
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
         * title : 北京海淀农场3
         * dizhi : 大兴区广顺路58号（大兴农场）
         * id : 3
         * pic : /data/img/1810/5bbc61824d0e2.png
         * vaid : 2
         */

        private String title;
        private String dizhi;
        private String id;
        private String pic;
        private String vaid;

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

        public String getVaid() {
            return vaid;
        }

        public void setVaid(String vaid) {
            this.vaid = vaid;
        }
    }
}
