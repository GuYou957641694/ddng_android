package com.bigpumpkin.app.ddng_android.bean;

public class VideoBean {


    /**
     * msg : success
     * code : 200
     * data : {"id":"26","des":"两亿斤柿子挂树上无人采摘，为百姓一份爱，同时收到一份爱的果两亿斤柿子挂树上无人采摘，为百姓一份爱，同时收到一份爱的果","image_pic":"/data/img/1910/5db932b4da0ea.jpg","video":"/data/img/1909/5d737225c906b.mp4","praise":"0","time":"0","name":"农场主2","pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/6B6icJWABurwTYtbj4BfMQVLFBKqiagekRABIB6jDjKg69AoyQFbKzsXgMplyUY1CZLppKd1I1OZ9MEOPZkjUEEA/132","fid":"9","praise_u":"2","attention":"2"}
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
         * id : 26
         * des : 两亿斤柿子挂树上无人采摘，为百姓一份爱，同时收到一份爱的果两亿斤柿子挂树上无人采摘，为百姓一份爱，同时收到一份爱的果
         * image_pic : /data/img/1910/5db932b4da0ea.jpg
         * video : /data/img/1909/5d737225c906b.mp4
         * praise : 0
         * time : 0
         * name : 农场主2
         * pic : http://thirdwx.qlogo.cn/mmopen/vi_32/6B6icJWABurwTYtbj4BfMQVLFBKqiagekRABIB6jDjKg69AoyQFbKzsXgMplyUY1CZLppKd1I1OZ9MEOPZkjUEEA/132
         * fid : 9
         * praise_u : 2
         * attention : 2
         */

        private String id;
        private String des;
        private String image_pic;
        private String video;
        private String praise;
        private String time;
        private String name;
        private String pic;
        private String fid;
        private String praise_u;
        private String attention;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getImage_pic() {
            return image_pic;
        }

        public void setImage_pic(String image_pic) {
            this.image_pic = image_pic;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getPraise_u() {
            return praise_u;
        }

        public void setPraise_u(String praise_u) {
            this.praise_u = praise_u;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }
    }
}
