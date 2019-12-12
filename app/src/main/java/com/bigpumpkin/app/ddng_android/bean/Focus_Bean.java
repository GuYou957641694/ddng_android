package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Focus_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"id":"2","pic3":"/data/img/1901/5c330ff1c9804.png","title":"北京海淀农场2","dizhi":"大兴区广顺路58号（大兴农场）"}]
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
         * id : 2
         * pic3 : /data/img/1901/5c330ff1c9804.png
         * title : 北京海淀农场2
         * dizhi : 大兴区广顺路58号（大兴农场）
         */

        private String id;
        private String pic3;
        private String title;
        private String dizhi;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic3() {
            return pic3;
        }

        public void setPic3(String pic3) {
            this.pic3 = pic3;
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
    }
}
