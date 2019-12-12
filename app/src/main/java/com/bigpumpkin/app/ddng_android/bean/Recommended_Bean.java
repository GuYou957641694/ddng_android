package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Recommended_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"title":"手工制品","id":"8"},{"title":"粮食","id":"1"},{"title":"干果","id":"2"},{"title":"熟食","id":"3"},{"title":"地熟","id":"4"},{"title":"零食","id":"5"},{"title":"小吃","id":"6"},{"title":"特产","id":"7"}]
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
         * title : 手工制品
         * id : 8
         */

        private String title;
        private String id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
