package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class ProcessingBean {


    /**
     * msg : success
     * code : 200
     * data : {"banner":[{"id":"24","pic":"/data/img/1901/5c46bf50dff38.png","title":"农场深加工","link":"link"},{"id":"25","pic":"/data/img/1901/5c46bf50dff38.png","title":"农场深加工","link":"link"},{"id":"26","pic":"/data/img/1901/5c46bf50dff38.png","title":"农场深加工","link":"link"},{"id":"27","pic":"/data/img/1901/5c46bf50dff38.png","title":"农场深加工","link":"link"}],"archiveslist":[{"title":"果脯加工","id":"1"},{"title":"苹果干加工","id":"2"},{"title":"葡萄干加工","id":"3"},{"title":"罐头加工","id":"4"},{"title":"水果食品","id":"5"},{"title":"果脯果仁","id":"6"},{"title":"饮料","id":"7"},{"title":"生态加工","id":"8"}]}
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
        private List<ArchiveslistBean> archiveslist;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<ArchiveslistBean> getArchiveslist() {
            return archiveslist;
        }

        public void setArchiveslist(List<ArchiveslistBean> archiveslist) {
            this.archiveslist = archiveslist;
        }

        public static class BannerBean {
            /**
             * id : 24
             * pic : /data/img/1901/5c46bf50dff38.png
             * title : 农场深加工
             * link : link
             */

            private String id;
            private String pic;
            private String title;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class ArchiveslistBean {
            /**
             * title : 果脯加工
             * id : 1
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
}
