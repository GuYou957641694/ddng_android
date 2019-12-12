package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class EvaluationDetailsBean {


    /**
     * msg : success
     * code : 200
     * data : [{"order_id":"1","title":"北京富士果树5","cp_id":"534","pic":"/data/img/1911/5dc0eba180231.jpg","gg_title":"40-50公斤","pz_title":"芒果","evaluation_dimension":[{"id":"5","title":"好吃不贵","lanmu":"2","px":"0"},{"id":"6","title":"品质好","lanmu":"2","px":"0"},{"id":"7","title":"口感好","lanmu":"2","px":"0"},{"id":"8","title":"服务号","lanmu":"2","px":"0"},{"id":"9","title":"物美价廉","lanmu":"2","px":"0"}]},{"order_id":"2","title":"越南富士9","cp_id":"538","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"90-100公斤","pz_title":"水蜜桃","evaluation_dimension":[{"id":"5","title":"好吃不贵","lanmu":"2","px":"0"},{"id":"6","title":"品质好","lanmu":"2","px":"0"},{"id":"7","title":"口感好","lanmu":"2","px":"0"},{"id":"8","title":"服务号","lanmu":"2","px":"0"},{"id":"9","title":"物美价廉","lanmu":"2","px":"0"}]}]
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
         * order_id : 1
         * title : 北京富士果树5
         * cp_id : 534
         * pic : /data/img/1911/5dc0eba180231.jpg
         * gg_title : 40-50公斤
         * pz_title : 芒果
         * evaluation_dimension : [{"id":"5","title":"好吃不贵","lanmu":"2","px":"0"},{"id":"6","title":"品质好","lanmu":"2","px":"0"},{"id":"7","title":"口感好","lanmu":"2","px":"0"},{"id":"8","title":"服务号","lanmu":"2","px":"0"},{"id":"9","title":"物美价廉","lanmu":"2","px":"0"}]
         */

        private String order_id;
        private String title;
        private String cp_id;
        private String pic;
        private String gg_title;
        private String pz_title;
        private List<EvaluationDimensionBean> evaluation_dimension;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCp_id() {
            return cp_id;
        }

        public void setCp_id(String cp_id) {
            this.cp_id = cp_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getGg_title() {
            return gg_title;
        }

        public void setGg_title(String gg_title) {
            this.gg_title = gg_title;
        }

        public String getPz_title() {
            return pz_title;
        }

        public void setPz_title(String pz_title) {
            this.pz_title = pz_title;
        }

        public List<EvaluationDimensionBean> getEvaluation_dimension() {
            return evaluation_dimension;
        }

        public void setEvaluation_dimension(List<EvaluationDimensionBean> evaluation_dimension) {
            this.evaluation_dimension = evaluation_dimension;
        }

        public static class EvaluationDimensionBean {
            /**
             * id : 5
             * title : 好吃不贵
             * lanmu : 2
             * px : 0
             */

            private String id;
            private String title;
            private String lanmu;
            private String px;

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

            public String getLanmu() {
                return lanmu;
            }

            public void setLanmu(String lanmu) {
                this.lanmu = lanmu;
            }

            public String getPx() {
                return px;
            }

            public void setPx(String px) {
                this.px = px;
            }
        }
    }
}
