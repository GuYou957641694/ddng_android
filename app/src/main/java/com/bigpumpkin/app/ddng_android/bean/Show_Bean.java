package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Show_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"t_id":"4","text":"2019-06-01 00:00:00","t_time":"1559318400","img":[{"p_id":"79","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.png"},{"p_id":"80","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.gif"},{"p_id":"81","link":"/data/img/nc/11f45f15fa7f5871dc7efb8a10c462f8.png"},{"p_id":"82","link":"/data/img/nc/0a122dd62b5c360a9b0b44cdeb63a230.png"}]},{"t_id":"2","text":"2018-03-03 00:00:00","t_time":"1520006400","img":[{"p_id":"79","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.png"},{"p_id":"80","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.gif"},{"p_id":"81","link":"/data/img/nc/11f45f15fa7f5871dc7efb8a10c462f8.png"},{"p_id":"82","link":"/data/img/nc/0a122dd62b5c360a9b0b44cdeb63a230.png"}]},{"t_id":"3","text":"2018-02-01 00:00:00一","t_time":"1517414400","img":[{"p_id":"79","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.png"},{"p_id":"80","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.gif"},{"p_id":"81","link":"/data/img/nc/11f45f15fa7f5871dc7efb8a10c462f8.png"},{"p_id":"82","link":"/data/img/nc/0a122dd62b5c360a9b0b44cdeb63a230.png"}]},{"t_id":"1","text":"2018-01-01 00:00:00","t_time":"1514736000","img":[{"p_id":"79","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.png"},{"p_id":"80","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.gif"},{"p_id":"81","link":"/data/img/nc/11f45f15fa7f5871dc7efb8a10c462f8.png"},{"p_id":"82","link":"/data/img/nc/0a122dd62b5c360a9b0b44cdeb63a230.png"}]}]
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
         * t_id : 4
         * text : 2019-06-01 00:00:00
         * t_time : 1559318400
         * img : [{"p_id":"79","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.png"},{"p_id":"80","link":"/data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.gif"},{"p_id":"81","link":"/data/img/nc/11f45f15fa7f5871dc7efb8a10c462f8.png"},{"p_id":"82","link":"/data/img/nc/0a122dd62b5c360a9b0b44cdeb63a230.png"}]
         */

        private String t_id;
        private String text;
        private long t_time;
        private List<ImgBean> img;

        public String getT_id() {
            return t_id;
        }

        public void setT_id(String t_id) {
            this.t_id = t_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public long getT_time() {
            return t_time;
        }

        public void setT_time(long t_time) {
            this.t_time = t_time;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public static class ImgBean {
            /**
             * p_id : 79
             * link : /data/img/nc/c3aa6d917f6edfb89659d8061cd2b0790.16255000 1562145726.png
             */

            private String p_id;
            private String link;

            public String getP_id() {
                return p_id;
            }

            public void setP_id(String p_id) {
                this.p_id = p_id;
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
