package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

//待付款
public class PendingPay_Bean {


    /**
     * msg : success
     * code : 200
     * data : [{"farm_id":"1","farm_name":"北京朝阳农场1","list":[{"id":"2","fid":"2","num":"1","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","pz_title":"西梅","gg_id":"2","pay_price":"2.97","cp_id":"4","gg_title":"10-20公斤"},{"id":"3","fid":"2","num":"1","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","pz_title":"西梅","gg_id":"7538","pay_price":"404.38","cp_id":"4","gg_title":"20-30公斤"},{"id":"4","fid":"2","num":"3","pic":"/data/img/1901/5c341411223c8.png","title":"北京富士果树7","pz_title":"砂糖桔","gg_id":"5","pay_price":"940.91","cp_id":"7","gg_title":"40-50公斤"}],"num":5,"num_price":"1348.26"},{"farm_id":"1","farm_name":"北京朝阳农场1","list":[{"id":"5","fid":"3","num":"1","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","pz_title":"西梅","gg_id":"2","pay_price":"2.97","cp_id":"4","gg_title":"10-20公斤"},{"id":"6","fid":"3","num":"1","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","pz_title":"西梅","gg_id":"7538","pay_price":"404.38","cp_id":"4","gg_title":"20-30公斤"},{"id":"7","fid":"3","num":"3","pic":"/data/img/1901/5c341411223c8.png","title":"北京富士果树7","pz_title":"砂糖桔","gg_id":"5","pay_price":"940.91","cp_id":"7","gg_title":"40-50公斤"}],"num":5,"num_price":"1348.26"},{"farm_id":"3","farm_name":"北京海淀农场3","list":[{"id":"8","fid":"4","num":"1","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","pz_title":"菠萝","gg_id":"4","pay_price":"10.21","cp_id":"6","gg_title":"60-70公斤"},{"id":"9","fid":"4","num":"1","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","pz_title":"菠萝","gg_id":"4","pay_price":"10.21","cp_id":"6","gg_title":"60-70公斤"},{"id":"10","fid":"4","num":"1","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","pz_title":"菠萝","gg_id":"4","pay_price":"10.21","cp_id":"6","gg_title":"60-70公斤"}],"num":3,"num_price":"30.63"},{"farm_id":"1","farm_name":"北京朝阳农场1","list":[{"id":"11","fid":"5","num":"1","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","pz_title":"西梅","gg_id":"2","pay_price":"2.97","cp_id":"4","gg_title":"10-20公斤"},{"id":"12","fid":"5","num":"1","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","pz_title":"西梅","gg_id":"7538","pay_price":"404.38","cp_id":"4","gg_title":"20-30公斤"},{"id":"13","fid":"5","num":"3","pic":"/data/img/1901/5c341411223c8.png","title":"北京富士果树7","pz_title":"砂糖桔","gg_id":"5","pay_price":"940.91","cp_id":"7","gg_title":"40-50公斤"}],"num":5,"num_price":"1348.26"},{"farm_id":"3","farm_name":"北京海淀农场3","list":[{"id":"14","fid":"6","num":"1","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","pz_title":"菠萝","gg_id":"4","pay_price":"10.21","cp_id":"6","gg_title":"60-70公斤"},{"id":"15","fid":"6","num":"1","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","pz_title":"菠萝","gg_id":"4","pay_price":"10.21","cp_id":"6","gg_title":"60-70公斤"},{"id":"16","fid":"6","num":"1","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","pz_title":"菠萝","gg_id":"4","pay_price":"10.21","cp_id":"6","gg_title":"60-70公斤"}],"num":3,"num_price":"30.63"},{"farm_id":"2","farm_name":"北京海淀农场2","list":[{"id":"17","fid":"7","num":"1","pic":"/data/img/1910/5dba98b56f518.jpg","title":"越南富士9","pz_title":"山楂","gg_id":"313","pay_price":"1.00","cp_id":"316","gg_title":"80-90公斤"}],"num":1,"num_price":"1.00"}]
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
         * farm_id : 1
         * farm_name : 北京朝阳农场1
         * list : [{"id":"2","fid":"2","num":"1","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","pz_title":"西梅","gg_id":"2","pay_price":"2.97","cp_id":"4","gg_title":"10-20公斤"},{"id":"3","fid":"2","num":"1","pic":"/data/img/1910/5da92e55cb821.jpg","title":"香蕉果树","pz_title":"西梅","gg_id":"7538","pay_price":"404.38","cp_id":"4","gg_title":"20-30公斤"},{"id":"4","fid":"2","num":"3","pic":"/data/img/1901/5c341411223c8.png","title":"北京富士果树7","pz_title":"砂糖桔","gg_id":"5","pay_price":"940.91","cp_id":"7","gg_title":"40-50公斤"}]
         * num : 5
         * num_price : 1348.26
         */

        private String farm_id;
        private String farm_name;
        private String num;
        private String num_price;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private List<ListBean> list;

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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getNum_price() {
            return num_price;
        }

        public void setNum_price(String num_price) {
            this.num_price = num_price;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * fid : 2
             * num : 1
             * pic : /data/img/1910/5da92e55cb821.jpg
             * title : 香蕉果树
             * pz_title : 西梅
             * gg_id : 2
             * pay_price : 2.97
             * cp_id : 4
             * gg_title : 10-20公斤
             */

            private String id;
            private String fid;
            private String num;
            private String pic;
            private String title;
            private String pz_title;
            private String gg_id;
            private String price;
            private String cp_id;
            private String gg_title;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
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

            public String getPz_title() {
                return pz_title;
            }

            public void setPz_title(String pz_title) {
                this.pz_title = pz_title;
            }

            public String getGg_id() {
                return gg_id;
            }

            public void setGg_id(String gg_id) {
                this.gg_id = gg_id;
            }


            public String getCp_id() {
                return cp_id;
            }

            public void setCp_id(String cp_id) {
                this.cp_id = cp_id;
            }

            public String getGg_title() {
                return gg_title;
            }

            public void setGg_title(String gg_title) {
                this.gg_title = gg_title;
            }
        }
    }
}
