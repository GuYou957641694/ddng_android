package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class FrmaPeopleBean {

    /**
     * msg : success
     * code : 200
     * data : [{"id":"3","u_name":"看我认养2","video":"/data/img/1909/5d737225c906b.mp4","image_pic":"/data/img/1909/5d7378950a955.png","userl":"看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2"}]
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
         * id : 3
         * u_name : 看我认养2
         * video : /data/img/1909/5d737225c906b.mp4
         * image_pic : /data/img/1909/5d7378950a955.png
         * userl : 看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2看我认养2
         */

        private String id;
        private String u_name;
        private String video;
        private String image_pic;
        private String userl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getImage_pic() {
            return image_pic;
        }

        public void setImage_pic(String image_pic) {
            this.image_pic = image_pic;
        }

        public String getUserl() {
            return userl;
        }

        public void setUserl(String userl) {
            this.userl = userl;
        }
    }
}
