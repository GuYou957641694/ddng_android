package com.bigpumpkin.app.ddng_android.bean;

import java.io.Serializable;
import java.util.List;

public class PoultryBean implements Serializable {


    /**
     * msg : success
     * code : 200
     * data : {"banner":[{"id":"5","pic":"/data/img/1910/5d95c19f07b13.jpg","link":"link"},{"id":"11","pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"id":"12","pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"id":"13","pic":"/data/img/1901/5c46bf50dff38.png","link":"link"}],"spf":[{"id":"11","title":"兔","pic":"/data/img/1910/5d95a4ab91894.png"},{"id":"7","title":"鸡","pic":"/data/img/1910/5d95a4b6d2f7f.png"},{"id":"14","title":"1","pic":"/data/img/1910/5d95a4a417a24.png"},{"id":"15","title":"2","pic":"/data/img/1910/5d95a49ace6a1.png"},{"id":"16","title":"3","pic":"/data/img/1910/5d95a4930f213.png"},{"id":"17","title":"44","pic":"/data/img/1910/5d95a48b0ce8a.png"},{"id":"18","title":"5","pic":"/data/img/1910/5d95a4830a360.png"},{"id":"19","title":"6","pic":"/data/img/1910/5d95a479e854f.png"}],"poultry_index":[{"id":"1","title":"特色养殖","pic":"/data/img/1909/5d8c716bcc923.jpg","link":"标识","desc":"简介"},{"id":"2","title":"特色养殖","pic":"/data/img/1909/5d8ca2b1482e9.jpg","link":"标识","desc":"简介"},{"id":"3","title":"特色养殖","pic":"/data/img/1909/5d8ca2bc2a013.jpg","link":"标识","desc":"简介"},{"id":"4","title":"特色养殖","pic":"/data/img/1909/5d8ca2c49f62e.jpg","link":"标识","desc":"简介"},{"id":"5","title":"特色养殖","pic":"/data/img/1909/5d8da302045f2.jpg","link":"标识","desc":"简介"},{"id":"6","title":"特色养殖","pic":"/data/img/1909/5d8da2f62036c.jpg","link":"标识","desc":"简介"}],"farmer_list":[{"id":"4","uid":"1","image_pic":"/data/img/1909/5d7378950a955.png","name":"农场主1"},{"id":"26","uid":"1","image_pic":"/data/img/1909/5d7378950a955.png","name":"农场主1"},{"id":"27","uid":"1","image_pic":"/data/img/1909/5d7378950a955.png","name":"农场主1"}],"publicity":{"id":"4","title":"家禽认养","pic":"/data/img/1909/5d6e536f2da56.png"},"poultry_index2":[{"id":"7","title":"奶制品","pic":"/data/img/1909/5d8da308cd408.jpg","link":"标识"},{"id":"8","title":"奶制品","pic":"/data/img/1909/5d8da311e3c27.jpg","link":"标识"}],"egg":[{"id":"952","title":"鸭梨蛋","chanliang":"0","pic":"/data/img/1910/5d96d659edfc8.png","chandi":"北京","price":"1.00"},{"id":"953","title":"鸭梨蛋","chanliang":"0","pic":"/data/img/1910/5d96d659edfc8.png","chandi":"北京","price":"1.00"},{"id":"954","title":"鸭梨蛋","chanliang":"0","pic":"/data/img/1910/5d96d659edfc8.png","chandi":"北京","price":"1.00"}]}
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
         * banner : [{"id":"5","pic":"/data/img/1910/5d95c19f07b13.jpg","link":"link"},{"id":"11","pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"id":"12","pic":"/data/img/1901/5c46bf50dff38.png","link":"link"},{"id":"13","pic":"/data/img/1901/5c46bf50dff38.png","link":"link"}]
         * spf : [{"id":"11","title":"兔","pic":"/data/img/1910/5d95a4ab91894.png"},{"id":"7","title":"鸡","pic":"/data/img/1910/5d95a4b6d2f7f.png"},{"id":"14","title":"1","pic":"/data/img/1910/5d95a4a417a24.png"},{"id":"15","title":"2","pic":"/data/img/1910/5d95a49ace6a1.png"},{"id":"16","title":"3","pic":"/data/img/1910/5d95a4930f213.png"},{"id":"17","title":"44","pic":"/data/img/1910/5d95a48b0ce8a.png"},{"id":"18","title":"5","pic":"/data/img/1910/5d95a4830a360.png"},{"id":"19","title":"6","pic":"/data/img/1910/5d95a479e854f.png"}]
         * poultry_index : [{"id":"1","title":"特色养殖","pic":"/data/img/1909/5d8c716bcc923.jpg","link":"标识","desc":"简介"},{"id":"2","title":"特色养殖","pic":"/data/img/1909/5d8ca2b1482e9.jpg","link":"标识","desc":"简介"},{"id":"3","title":"特色养殖","pic":"/data/img/1909/5d8ca2bc2a013.jpg","link":"标识","desc":"简介"},{"id":"4","title":"特色养殖","pic":"/data/img/1909/5d8ca2c49f62e.jpg","link":"标识","desc":"简介"},{"id":"5","title":"特色养殖","pic":"/data/img/1909/5d8da302045f2.jpg","link":"标识","desc":"简介"},{"id":"6","title":"特色养殖","pic":"/data/img/1909/5d8da2f62036c.jpg","link":"标识","desc":"简介"}]
         * farmer_list : [{"id":"4","uid":"1","image_pic":"/data/img/1909/5d7378950a955.png","name":"农场主1"},{"id":"26","uid":"1","image_pic":"/data/img/1909/5d7378950a955.png","name":"农场主1"},{"id":"27","uid":"1","image_pic":"/data/img/1909/5d7378950a955.png","name":"农场主1"}]
         * publicity : {"id":"4","title":"家禽认养","pic":"/data/img/1909/5d6e536f2da56.png"}
         * poultry_index2 : [{"id":"7","title":"奶制品","pic":"/data/img/1909/5d8da308cd408.jpg","link":"标识"},{"id":"8","title":"奶制品","pic":"/data/img/1909/5d8da311e3c27.jpg","link":"标识"}]
         * egg : [{"id":"952","title":"鸭梨蛋","chanliang":"0","pic":"/data/img/1910/5d96d659edfc8.png","chandi":"北京","price":"1.00"},{"id":"953","title":"鸭梨蛋","chanliang":"0","pic":"/data/img/1910/5d96d659edfc8.png","chandi":"北京","price":"1.00"},{"id":"954","title":"鸭梨蛋","chanliang":"0","pic":"/data/img/1910/5d96d659edfc8.png","chandi":"北京","price":"1.00"}]
         */

        private PublicityBean publicity;
        private List<BannerBean> banner;
        private List<SpfBean> spf;
        private List<PoultryIndexBean> poultry_index;
        private List<FarmerListBean> farmer_list;
        private List<PoultryIndex2Bean> poultry_index2;

        public PublicityBean getPublicity() {
            return publicity;
        }

        public void setPublicity(PublicityBean publicity) {
            this.publicity = publicity;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<SpfBean> getSpf() {
            return spf;
        }

        public void setSpf(List<SpfBean> spf) {
            this.spf = spf;
        }

        public List<PoultryIndexBean> getPoultry_index() {
            return poultry_index;
        }

        public void setPoultry_index(List<PoultryIndexBean> poultry_index) {
            this.poultry_index = poultry_index;
        }

        public List<FarmerListBean> getFarmer_list() {
            return farmer_list;
        }

        public void setFarmer_list(List<FarmerListBean> farmer_list) {
            this.farmer_list = farmer_list;
        }

        public List<PoultryIndex2Bean> getPoultry_index2() {
            return poultry_index2;
        }

        public void setPoultry_index2(List<PoultryIndex2Bean> poultry_index2) {
            this.poultry_index2 = poultry_index2;
        }


        public static class PublicityBean {
            /**
             * id : 4
             * title : 家禽认养
             * pic : /data/img/1909/5d6e536f2da56.png
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

        public static class BannerBean {
            /**
             * id : 5
             * pic : /data/img/1910/5d95c19f07b13.jpg
             * link : link
             */

            private String id;
            private String pic;
            private String link;

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

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class SpfBean  implements Serializable {
            /**
             * id : 11
             * title : 兔
             * pic : /data/img/1910/5d95a4ab91894.png
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

        public static class PoultryIndexBean {
            /**
             * id : 1
             * title : 特色养殖
             * pic : /data/img/1909/5d8c716bcc923.jpg
             * link : 标识
             * desc : 简介
             */

            private String id;
            private String title;
            private String pic;
            private String link;
            private String desc;

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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }

        public static class FarmerListBean {
            /**
             * id : 4
             * uid : 1
             * image_pic : /data/img/1909/5d7378950a955.png
             * name : 农场主1
             */

            private String id;
            private String uid;
            private String image_pic;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getImage_pic() {
                return image_pic;
            }

            public void setImage_pic(String image_pic) {
                this.image_pic = image_pic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class PoultryIndex2Bean {
            /**
             * id : 7
             * title : 奶制品
             * pic : /data/img/1909/5d8da308cd408.jpg
             * link : 标识
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


    }
}
