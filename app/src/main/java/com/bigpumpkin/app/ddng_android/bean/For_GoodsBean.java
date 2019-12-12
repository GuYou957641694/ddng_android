package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class For_GoodsBean {


    /**
     * msg : success
     * code : 200
     * data : [{"order_id":"23","farm_id":"2","farm_name":"北京海淀农场2","total_price":"2.00","sum":2,"list":[{"cp_id":"3","pic":"/data/img/1901/5c341411223c8.png","pz_title":"香瓜","gg_title":"30-40公斤","pay_price":"1.00","num":"1","id":"40","lanmu":"2"},{"cp_id":"316","pic":"/data/img/1910/5dba98b56f518.jpg","pz_title":"山楂","gg_title":"80-90公斤","pay_price":"1.00","num":"1","id":"41","lanmu":"2"}],"Statu":2},{"order_id":"3","farm_id":"2","farm_name":"北京海淀农场2","total_price":"1.00","sum":1,"list":[{"cp_id":"316","pic":"/data/img/1910/5dba98b56f518.jpg","pz_title":"山楂","gg_title":"80-90公斤","pay_price":"1.00","num":"1","id":"5","lanmu":"2"}],"Statu":3}]
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
         * order_id : 23
         * farm_id : 2
         * farm_name : 北京海淀农场2
         * total_price : 2.00
         * sum : 2
         * list : [{"cp_id":"3","pic":"/data/img/1901/5c341411223c8.png","pz_title":"香瓜","gg_title":"30-40公斤","pay_price":"1.00","num":"1","id":"40","lanmu":"2"},{"cp_id":"316","pic":"/data/img/1910/5dba98b56f518.jpg","pz_title":"山楂","gg_title":"80-90公斤","pay_price":"1.00","num":"1","id":"41","lanmu":"2"}]
         * Statu : 2
         */

        private String order_id;
        private String farm_id;
        private String farm_name;
        private String total_price;
        private String sum;
        private int Statu;
        private int View_Logistics;
        private List<ListBean> list;

        public int getView_Logistics() {
            return View_Logistics;
        }

        public void setView_Logistics(int view_Logistics) {
            View_Logistics = view_Logistics;
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

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public int getStatu() {
            return Statu;
        }

        public void setStatu(int Statu) {
            this.Statu = Statu;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cp_id : 3
             * pic : /data/img/1901/5c341411223c8.png
             * pz_title : 香瓜
             * gg_title : 30-40公斤
             * pay_price : 1.00
             * num : 1
             * id : 40
             * lanmu : 2
             */

            private String cp_id;
            private String pic;
            private String pz_title;
            private String gg_title;
            private String pay_price;
            private String num;
            private String id;
            private String lanmu;
            private String title;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLanmu() {
                return lanmu;
            }

            public void setLanmu(String lanmu) {
                this.lanmu = lanmu;
            }
        }
    }
}
