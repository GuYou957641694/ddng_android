package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Plants_AdoptBean {


    /**
     * msg : success
     * code : 200
     * data : {"banner":[{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"}],"journalismlen":[{"id":"1","title":"百年树","pic":"/data/img/1909/5d8caa017388d.jpg"},{"id":"2","title":"定制认养","pic":"/data/img/1909/5d8ca9f7a4148.jpg"},{"id":"3","title":"单认养","pic":"/data/img/1909/5d8ca9ed32049.jpg"},{"id":"4","title":"团队认养","pic":"/data/img/1909/5d8ca79681855.jpg"}],"area":[{"id":"4","title":"广西产区","pic":"/data/img/1909/5d8ca2c49f62e.jpg","link":"标识","type":"1"},{"id":"3","title":"广西产区","pic":"/data/img/1909/5d8ca2bc2a013.jpg","link":"标识","type":"1"},{"id":"2","title":"草莓农场","pic":"/data/img/1909/5d8ca2b1482e9.jpg","link":"标识","type":"1"},{"id":"1","title":"广西产区","pic":"/data/img/1909/5d8c716bcc923.jpg","link":"标识","type":"1"}],"publicity":[{"id":"1","title":"植物认养","pic":"/data/img/1909/5d6e536f2da56.png","link":"link","enable":"1"},{"id":"7","title":"植物认养","pic":"/data/img/1909/5d6e536f2da56.png","link":"link","enable":"1"},{"id":"8","title":"植物认养","pic":"/data/img/1909/5d6e536f2da56.png","link":"link","enable":"1"}],"pollution":[{"id":"10","title":"草莓农场","pic":"pic","link":"标识"},{"id":"9","title":"草莓农场","pic":"pic","link":"标识"},{"id":"8","title":"草莓农场","pic":"pic","link":"标识"},{"id":"7","title":"草莓农场","pic":"pic","link":"标识"},{"id":"6","title":"草莓农场","pic":"/data/img/1909/5d8c70a9033c5.jpg","link":"标识"},{"id":"5","title":"草莓农场","pic":"/data/img/1909/5d8c71615e15e.jpg","link":"标识"}],"single":[{"id":"12","title":"20-30公斤","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","number_people":1},{"id":"11","title":"20-30公斤","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","number_people":1},{"id":"10","title":"20-30公斤","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","number_people":1},{"id":"9","title":"20-30公斤","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","number_people":1},{"id":"7","title":"20-30公斤","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","number_people":2}]}
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
        private List<BannerBean> banner;
        private List<JournalismlenBean> journalismlen;
        private List<AreaBean> area;
        private List<PublicityBean> publicity;
        private List<PollutionBean> pollution;
        private List<SingleBean> single;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<JournalismlenBean> getJournalismlen() {
            return journalismlen;
        }

        public void setJournalismlen(List<JournalismlenBean> journalismlen) {
            this.journalismlen = journalismlen;
        }

        public List<AreaBean> getArea() {
            return area;
        }

        public void setArea(List<AreaBean> area) {
            this.area = area;
        }

        public List<PublicityBean> getPublicity() {
            return publicity;
        }

        public void setPublicity(List<PublicityBean> publicity) {
            this.publicity = publicity;
        }

        public List<PollutionBean> getPollution() {
            return pollution;
        }

        public void setPollution(List<PollutionBean> pollution) {
            this.pollution = pollution;
        }

        public List<SingleBean> getSingle() {
            return single;
        }

        public void setSingle(List<SingleBean> single) {
            this.single = single;
        }

        public static class BannerBean {
            /**
             * pic : /data/img/1901/5c46bf50dff38.png
             * link : link
             */
            private String pic;
            private String link;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class JournalismlenBean {
            /**
             * id : 1
             * title : 百年树
             * pic : /data/img/1909/5d8caa017388d.jpg
             */

            private String id;
            private String title;
            private String pic;

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
        }

        public static class AreaBean {
            /**
             * id : 4
             * title : 广西产区
             * pic : /data/img/1909/5d8ca2c49f62e.jpg
             * link : 标识
             * type : 1
             */

            private String id;
            private String title;
            private String pic;
            private String link;
            private String type;

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

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class PublicityBean {
            /**
             * id : 1
             * title : 植物认养
             * pic : /data/img/1909/5d6e536f2da56.png
             * link : link
             * enable : 1
             */

            private String id;
            private String title;
            private String pic;
            private String link;
            private String enable;

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

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getEnable() {
                return enable;
            }

            public void setEnable(String enable) {
                this.enable = enable;
            }
        }

        public static class PollutionBean {
            /**
             * id : 10
             * title : 草莓农场
             * pic : pic
             * link : 标识
             */

            private String id;
            private String title;
            private String pic;
            private String link;

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

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class SingleBean {
            /**
             * id : 12
             * title : 20-30公斤
             * img_pic : /data/img/1811/5bfb9a1f70711.png
             * num : 4
             * amount : 44.00
             * number_people : 1
             */

            private String id;
            private String title;
            private String gg_title;
            private String img_pic;
            private String num;
            private String amount;
            private int number_people;

            public String getGg_title() {
                return gg_title;
            }

            public void setGg_title(String gg_title) {
                this.gg_title = gg_title;
            }

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

            public int getNumber_people() {
                return number_people;
            }

            public void setNumber_people(int number_people) {
                this.number_people = number_people;
            }
        }
    }
}
