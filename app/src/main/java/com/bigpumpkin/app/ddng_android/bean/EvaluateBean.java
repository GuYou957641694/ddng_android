package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class EvaluateBean {


    /**
     * msg : success
     * code : 200
     * data : [{"order_id":"1","farm_id":"1","farm_name":"北京朝阳农场1","list":[{"cp_id":"534","title":"北京富士果树5","pz_title":"芒果","gg_title":"40-50公斤","pay_price":"86.47","num":"1"},{"cp_id":"538","title":"越南富士9","pz_title":"水蜜桃","gg_title":"90-100公斤","pay_price":"107.88","num":"1"}],"price":"194.35","num":2}]
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
         * farm_id : 1
         * farm_name : 北京朝阳农场1
         * list : [{"cp_id":"534","title":"北京富士果树5","pz_title":"芒果","gg_title":"40-50公斤","pay_price":"86.47","num":"1"},{"cp_id":"538","title":"越南富士9","pz_title":"水蜜桃","gg_title":"90-100公斤","pay_price":"107.88","num":"1"}]
         * price : 194.35
         * num : 2
         */

        private String order_id;
        private String farm_id;
        private String farm_name;
        private String price;
        private String num;
        private String Type;
        private List<ListBean> list;

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getFarm_id() {
            return farm_id;
        }

        public void setFarm_id(String farm_id) {
            this.farm_id = farm_id;
        }

        public String getFarm_name() {
            return farm_name;
        }

        public void setFarm_name(String farm_name) {
            this.farm_name = farm_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cp_id : 534
             * title : 北京富士果树5
             * pz_title : 芒果
             * gg_title : 40-50公斤
             * pay_price : 86.47
             * num : 1
             */

            private String pic;
            private String cp_id;
            private String title;
            private String pz_title;
            private String gg_title;
            private String pay_price;
            private String num;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getCp_id() {
                return cp_id;
            }

            public void setCp_id(String cp_id) {
                this.cp_id = cp_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPz_title() {
                return pz_title;
            }

            public void setPz_title(String pz_title) {
                this.pz_title = pz_title;
            }

            public String getGg_title() {
                return gg_title;
            }

            public void setGg_title(String gg_title) {
                this.gg_title = gg_title;
            }

            public String getPay_price() {
                return pay_price;
            }

            public void setPay_price(String pay_price) {
                this.pay_price = pay_price;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
