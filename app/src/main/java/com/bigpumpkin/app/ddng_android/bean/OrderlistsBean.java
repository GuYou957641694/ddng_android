package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class OrderlistsBean {

    /**
     * msg : success
     * code : 200
     * data : {"farm_id":"1","farm_name":"北京朝阳农场1","order_id":"5","order_etime":"1574414692","tel":"15506410555","sheng":"山东","shi":"菏泽","qu":"郓城","address":"详细地址测试","name":"用户名测试","ctime":"1574328292","total_price":"1379.64","message":"0","single":"2","insurance":"8.21","postage":"0.00","welfare":"0.41","orderlist":[{"title":"香蕉果树","pic":"/data/img/1910/5da92e55cb821.jpg","price":"3.00","num":"1","pz_title":"西梅","gg_title":"10-20公斤","maintain":"0","lanmu":"1"},{"title":"香蕉果树","pic":"/data/img/1910/5da92e55cb821.jpg","price":"407.64","num":"1","pz_title":"西梅","gg_title":"20-30公斤","maintain":"测试1测试1测试1测试1测试1","lanmu":"1"},{"title":"北京富士果树7","pic":"/data/img/1901/5c341411223c8.png","price":"323.00","num":"3","pz_title":"砂糖桔","gg_title":"40-50公斤","maintain":"0","lanmu":"2"}],"show":1}
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
         * farm_id : 1
         * farm_name : 北京朝阳农场1
         * order_id : 5
         * order_etime : 1574414692
         * tel : 15506410555
         * sheng : 山东
         * shi : 菏泽
         * qu : 郓城
         * address : 详细地址测试
         * name : 用户名测试
         * ctime : 1574328292
         * total_price : 1379.64
         * message : 0
         * single : 2
         * insurance : 8.21
         * postage : 0.00
         * welfare : 0.41
         * orderlist : [{"title":"香蕉果树","pic":"/data/img/1910/5da92e55cb821.jpg","price":"3.00","num":"1","pz_title":"西梅","gg_title":"10-20公斤","maintain":"0","lanmu":"1"},{"title":"香蕉果树","pic":"/data/img/1910/5da92e55cb821.jpg","price":"407.64","num":"1","pz_title":"西梅","gg_title":"20-30公斤","maintain":"测试1测试1测试1测试1测试1","lanmu":"1"},{"title":"北京富士果树7","pic":"/data/img/1901/5c341411223c8.png","price":"323.00","num":"3","pz_title":"砂糖桔","gg_title":"40-50公斤","maintain":"0","lanmu":"2"}]
         * show : 1
         */

        private String farm_id;
        private String farm_name;
        private String order_id;
        private String order_etime;
        private String tel;
        private String sheng;
        private String shi;
        private String qu;
        private String address;
        private String name;
        private long ctime;
        private String total_price;
        private String price;
        private String message;
        private String single;
        private String insurance;
        private String postage;
        private String welfare;
        private String show;
        private String numbering;
        private String jian;
        private List<OrderlistBean> orderlist;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getJian() {
            return jian;
        }

        public void setJian(String jian) {
            this.jian = jian;
        }

        public String getNumbering() {
            return numbering;
        }

        public void setNumbering(String numbering) {
            this.numbering = numbering;
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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_etime() {
            return order_etime;
        }

        public void setOrder_etime(String order_etime) {
            this.order_etime = order_etime;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getSheng() {
            return sheng;
        }

        public void setSheng(String sheng) {
            this.sheng = sheng;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getQu() {
            return qu;
        }

        public void setQu(String qu) {
            this.qu = qu;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSingle() {
            return single;
        }

        public void setSingle(String single) {
            this.single = single;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public String getPostage() {
            return postage;
        }

        public void setPostage(String postage) {
            this.postage = postage;
        }

        public String getWelfare() {
            return welfare;
        }

        public void setWelfare(String welfare) {
            this.welfare = welfare;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }

        public List<OrderlistBean> getOrderlist() {
            return orderlist;
        }

        public void setOrderlist(List<OrderlistBean> orderlist) {
            this.orderlist = orderlist;
        }

        public static class OrderlistBean {
            /**
             * title : 香蕉果树
             * pic : /data/img/1910/5da92e55cb821.jpg
             * price : 3.00
             * num : 1
             * pz_title : 西梅
             * gg_title : 10-20公斤
             * maintain : 0
             * lanmu : 1
             */

            private String title;
            private String pic;
            private String price;
            private String num;
            private String pz_title;
            private String gg_title;
            private String maintain;
            private String lanmu;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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

            public String getMaintain() {
                return maintain;
            }

            public void setMaintain(String maintain) {
                this.maintain = maintain;
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
