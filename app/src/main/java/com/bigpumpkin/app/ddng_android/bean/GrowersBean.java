package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class GrowersBean {


    /**
     * msg : success
     * code : 200
     * data : [{"pic":"https://tfs.alipayobjects.com/images/partner/TB1G4jqbX5LDuNjme7TXXatkpXa","name":"农场主1","image_pic":"/data/img/1909/5d7378950a955.png","video":"/data/img/1910/5da9726693db3.jpg","des":"测试","id":"3","fid":"1","attention":"2"},{"pic":"https://tfs.alipayobjects.com/images/partner/TB1G4jqbX5LDuNjme7TXXatkpXa","name":"农场主2","image_pic":"/data/img/1910/5daa9afa9ba17.jpg","video":"/data/img/1910/5daa9afa9ba17.mp4","des":"测试5","id":"25","fid":"1","attention":"2"}]
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
         * pic : https://tfs.alipayobjects.com/images/partner/TB1G4jqbX5LDuNjme7TXXatkpXa
         * name : 农场主1
         * image_pic : /data/img/1909/5d7378950a955.png
         * video : /data/img/1910/5da9726693db3.jpg
         * des : 测试
         * id : 3
         * fid : 1
         * attention : 2
         */

        private String pic;
        private String name;
        private String image_pic;
        private String video;
        private String des;
        private String id;
        private String fid;
        private String attention;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }
    }
}
