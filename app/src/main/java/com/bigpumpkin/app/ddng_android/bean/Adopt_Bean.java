package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Adopt_Bean {


    /**
     * msg : success
     * code : 200
     * data : {"banner":[{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"pic":"/data/img/1901/5c46bf50dff38.png","link":"link"}],"journalismlen":[{"id":"1","title":"定制认养","pic":"/data/img/1910/5d9dbeac8c3a0.png"},{"id":"2","title":"拼单认养","pic":"/data/img/1910/5d9dbea4c2836.png"},{"id":"3","title":"团队认养","pic":"/data/img/1910/5d9dbe9b8eedb.png"},{"id":"4","title":"给娃认养","pic":"/data/img/1910/5d9dbe9171821.png"},{"id":"11","title":"果农说","pic":"/data/img/1910/5d9dbe8929aa9.png"},{"id":"12","title":"植物品类","pic":"/data/img/1910/5d9ee85924160.png"}],"area":[{"id":"4","title":"广西产区","pic":"/data/img/1910/5d9c33a28822f.jpg","link":"标识","type":"1","text":"简介"},{"id":"3","title":"广西产区","pic":"/data/img/1910/5d9c33ab4299a.jpg","link":"标识","type":"1","text":"简介"},{"id":"2","title":"草莓农场","pic":"/data/img/1910/5d9c33999e39e.jpg","link":"标识","type":"1","text":"简介"},{"id":"1","title":"广西产区","pic":"/data/img/1910/5d9dbe67db740.jpg","link":"标识","type":"1","text":"简介"}],"zero":[{"id":"13","title":"0元领植物","pic":"/data/img/1910/5d9e9939baadc.jpg","link":"标识","type":"3","text":"简介"},{"id":"12","title":"0元领植物","pic":"/data/img/1910/5d9e9930b2b9f.jpg","link":"标识","type":"3","text":"简介"},{"id":"11","title":"0元领植物","pic":"/data/img/1910/5d9e99268517c.jpg","link":"标识","type":"3","text":"简介"}],"pollution":[{"id":"8","title":"0污染农场","pic":"/data/img/1910/5d9e9d99703dd.jpg","link":"标识","text":"简介"},{"id":"7","title":"0污染农场","pic":"/data/img/1910/5d9e9e9b03ca9.jpg","link":"标识","text":"简介"},{"id":"6","title":"0污染农场","pic":"/data/img/1910/5d9e9d915af24.jpg","link":"标识","text":"简介"},{"id":"5","title":"0污染农场","pic":"/data/img/1910/5d9e9d868009c.jpg","link":"标识","text":"简介"}],"single":[{"id":"12","title":"大南瓜拼单12","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","gg_title":"20-30公斤","number_people":1},{"id":"11","title":"大南瓜拼单11","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","gg_title":"20-30公斤","number_people":1},{"id":"10","title":"大南瓜拼单10","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","gg_title":"20-30公斤","number_people":1},{"id":"9","title":"大南瓜拼单9","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","gg_title":"20-30公斤","number_people":1},{"id":"7","title":"大南瓜拼单7","img_pic":"/data/img/1811/5bfb9a1f70711.png","num":"4","amount":"44.00","gg_title":"20-30公斤","number_people":2}],"tea":[{"id":"23","title":"茶树认养","pic":"/data/img/1910/5d9eaa2e8021f.jpg","link":"标识","type":"4","text":"简介"},{"id":"22","title":"茶树认养","pic":"/data/img/1910/5d9eaa2e8021f.jpg","link":"标识","type":"4","text":"简介"},{"id":"17","title":"茶树认养","pic":"/data/img/1910/5d9eaa37635e0.jpg","link":"标识","type":"4","text":"简介"},{"id":"16","title":"茶树认养","pic":"/data/img/1910/5d9eaa2e8021f.jpg","link":"简介3","type":"4","text":"2"},{"id":"15","title":"茶树认养","pic":"/data/img/1910/5d9eaa266a996.jpg","link":"简介2","type":"4","text":"1"}],"crops":[{"id":"21","title":"农作物认养","pic":"/data/img/1910/5d9ed1af709e4.jpg","link":"标识","type":"5","text":"简介"},{"id":"20","title":"农作物认养","pic":"/data/img/1910/5d9ed1a6a38ea.jpg","link":"标识","type":"5","text":"简介"},{"id":"19","title":"农作物认养","pic":"/data/img/1910/5d9ed19e8a358.jpg","link":"标识","type":"5","text":"简介"},{"id":"18","title":"农作物认养2","pic":"/data/img/1910/5d9ed1965e807.jpg","link":"标识","type":"5","text":"简介"}]}
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
        private List<ZeroBean> zero;
        private List<PollutionBean> pollution;
        private List<SingleBean> single;
        private List<TeaBean> tea;
        private List<CropsBean> crops;

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

        public List<ZeroBean> getZero() {
            return zero;
        }

        public void setZero(List<ZeroBean> zero) {
            this.zero = zero;
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

        public List<TeaBean> getTea() {
            return tea;
        }

        public void setTea(List<TeaBean> tea) {
            this.tea = tea;
        }

        public List<CropsBean> getCrops() {
            return crops;
        }

        public void setCrops(List<CropsBean> crops) {
            this.crops = crops;
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
             * title : 定制认养
             * pic : /data/img/1910/5d9dbeac8c3a0.png
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
             * pic : /data/img/1910/5d9c33a28822f.jpg
             * link : 标识
             * type : 1
             * text : 简介
             */

            private String id;
            private String title;
            private String pic;
            private String link;
            private String type;
            private String desc;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
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

        public static class ZeroBean {
            /**
             * id : 13
             * title : 0元领植物
             * pic : /data/img/1910/5d9e9939baadc.jpg
             * link : 标识
             * type : 3
             * text : 简介
             */

            private String id;
            private String title;
            private String pic;
            private String link;
            private String type;
            private String desc;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
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

        public static class PollutionBean {
            /**
             * id : 8
             * title : 0污染农场
             * pic : /data/img/1910/5d9e9d99703dd.jpg
             * link : 标识
             * text : 简介
             */

            private String id;
            private String title;
            private String pic;
            private String link;
            private String desc;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
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
             * title : 大南瓜拼单12
             * img_pic : /data/img/1811/5bfb9a1f70711.png
             * num : 4
             * amount : 44.00
             * gg_title : 20-30公斤
             * number_people : 1
             */

            private String id;
            private String title;
            private String pic;
            private String chanliang;

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

            public String getChanliang() {
                return chanliang;
            }

            public void setChanliang(String chanliang) {
                this.chanliang = chanliang;
            }
        }

        public static class TeaBean {
            /**
             * id : 23
             * title : 茶树认养
             * pic : /data/img/1910/5d9eaa2e8021f.jpg
             * link : 标识
             * type : 4
             * text : 简介
             */

            private String id;
            private String title;
            private String pic;
            private String link;
            private String type;
            private String desc;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
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

        public static class CropsBean {
            /**
             * id : 21
             * title : 农作物认养
             * pic : /data/img/1910/5d9ed1af709e4.jpg
             * link : 标识
             * type : 5
             * text : 简介
             */

            private String id;
            private String title;
            private String pic;
            private String link;
            private String type;
            private String desc;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
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
    }
}
