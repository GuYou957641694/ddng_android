package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Order_Bean {


    /**
     * msg : 成功
     * code : 200
     * data : {"list":[{"stime":"1555636231","id":"23","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"香蕉果树","cp_title":"香蕉果树"},{"stime":"1555582295","id":"22","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"香蕉果树","cp_title":"香蕉果树"},{"stime":"1555582289","id":"21","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"香蕉果树","cp_title":"香蕉果树"},{"stime":"1555578081","id":"19","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单19","cp_title":"北京富士果树5"},{"stime":"1555492274","id":"17","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单17","cp_title":"北京富士果树5"},{"stime":"1555486440","id":"15","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单15","cp_title":"北京富士果树5"},{"stime":"1553154559","id":"12","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单12","cp_title":"北京富士果树5"},{"stime":"1553075191","id":"11","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单11","cp_title":"北京富士果树5"},{"stime":"1553074783","id":"10","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单10","cp_title":"北京富士果树5"},{"stime":"1553074783","id":"9","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单9","cp_title":"北京富士果树5"},{"stime":"1553074783","id":"7","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单7","cp_title":"北京富士果树5"},{"stime":"1553074782","id":"4","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单4","cp_title":"北京富士果树5"},{"stime":"1553074782","id":"3","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单3","cp_title":"北京富士果树5"},{"stime":"1553074782","id":"2","etime":"1570869386","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","title":"大南瓜拼单2","cp_title":"北京富士果树5"}]}
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
             * stime : 1555636231
             * id : 23
             * etime : 1570869386
             * img_pic : /data/img/1811/5bfb9a1f70711.png
             * num : 4
             * amount : 44.00
             * title : 香蕉果树
             * cp_title : 香蕉果树
             */

            private String stime;
            private String id;
            private String etime;
            private String img_pic;
            private String num;
            private String amount;
            private String title;
            private String cp_title;

            public String getStime() {
                return stime;
            }

            public void setStime(String stime) {
                this.stime = stime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEtime() {
                return etime;
            }

            public void setEtime(String etime) {
                this.etime = etime;
            }

            public String getImg_pic() {
                return img_pic;
            }

            public void setImg_pic(String img_pic) {
                this.img_pic = img_pic;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCp_title() {
                return cp_title;
            }

            public void setCp_title(String cp_title) {
                this.cp_title = cp_title;
            }
        }
    }
}
