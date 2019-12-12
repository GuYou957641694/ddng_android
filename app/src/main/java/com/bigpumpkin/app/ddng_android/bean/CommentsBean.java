package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class CommentsBean {


    /**
     * msg : 查看成功
     * code : 200
     * data : {"farm":{"lanmu":"1","id":"4"},"evaluation_label":[{"id":"1","title":"物美价廉","lanmu":"1","px":"0"},{"id":"2","title":"服务态度好","lanmu":"1","px":"0"},{"id":"3","title":"品质好","lanmu":"1","px":"0"},{"id":"4","title":"很划算","lanmu":"1","px":"0"}]}
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
         * farm : {"lanmu":"1","id":"4"}
         * evaluation_label : [{"id":"1","title":"物美价廉","lanmu":"1","px":"0"},{"id":"2","title":"服务态度好","lanmu":"1","px":"0"},{"id":"3","title":"品质好","lanmu":"1","px":"0"},{"id":"4","title":"很划算","lanmu":"1","px":"0"}]
         */

        private FarmBean farm;
        private List<EvaluationLabelBean> evaluation_label;

        public FarmBean getFarm() {
            return farm;
        }

        public void setFarm(FarmBean farm) {
            this.farm = farm;
        }

        public List<EvaluationLabelBean> getEvaluation_label() {
            return evaluation_label;
        }

        public void setEvaluation_label(List<EvaluationLabelBean> evaluation_label) {
            this.evaluation_label = evaluation_label;
        }

        public static class FarmBean {
            /**
             * lanmu : 1
             * id : 4
             */

            private String lanmu;
            private String id;

            public String getLanmu() {
                return lanmu;
            }

            public void setLanmu(String lanmu) {
                this.lanmu = lanmu;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class EvaluationLabelBean {
            /**
             * id : 1
             * title : 物美价廉
             * lanmu : 1
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
