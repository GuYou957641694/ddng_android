package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class PendingReceiptDetailssBean {


    /**
     * msg : success
     * code : 200
     * data : {"name":"用户名测试","tel":"15865816355","sheng":"山东","shi":"菏泽","qu":"郓城","address":"详细地址测试","farm_id":"2","farm_name":"北京海淀农场2","trade_no":"2019112233583","numbering":"2019112233583","pay_time":"1574412252","ctime":"1574412174","list":[{"id":"316","orderlist_id":"5","title":"越南富士9","gg_title":"80-90公斤","pz_title":"山楂","price":"1.00","lanmu":"2","num":"1"}],"logistics":{"time":"2019-11-26 00:02:51","ftime":"2019-11-26 00:02:51","context":"浙江杭州航空部-已装袋发往-北京转运中心","code":"200"},"Statu":3,"More":1,"total_price":"1.00","price":"1.00"}
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
         * name : 用户名测试
         * tel : 15865816355
         * sheng : 山东
         * shi : 菏泽
         * qu : 郓城
         * address : 详细地址测试
         * farm_id : 2
         * farm_name : 北京海淀农场2
         * trade_no : 2019112233583
         * numbering : 2019112233583
         * pay_time : 1574412252
         * ctime : 1574412174
         * list : [{"id":"316","orderlist_id":"5","title":"越南富士9","gg_title":"80-90公斤","pz_title":"山楂","price":"1.00","lanmu":"2","num":"1"}]
         * logistics : {"time":"2019-11-26 00:02:51","ftime":"2019-11-26 00:02:51","context":"浙江杭州航空部-已装袋发往-北京转运中心","code":"200"}
         * Statu : 3
         * More : 1
         * total_price : 1.00
         * price : 1.00
         */

        private String name;
        private String tel;
        private String sheng;
        private String shi;
        private String qu;
        private String address;
        private String farm_id;
        private String farm_name;
        private String trade_no;
        private String numbering;
        private String pay_time;
        private long ctime;
        private LogisticsBean logistics;
        private int Statu;
        private int More;
        private String total_price;
        private String order_id;
        private String price;
        private String welfare;
        private String insurance;
        private String postage;
        private String coupon;
        private String tracking_number;
        private String tracking_title;
        private List<ListBean> list;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getTracking_number() {
            return tracking_number;
        }

        public void setTracking_number(String tracking_number) {
            this.tracking_number = tracking_number;
        }

        public String getTracking_title() {
            return tracking_title;
        }

        public void setTracking_title(String tracking_title) {
            this.tracking_title = tracking_title;
        }

        public String getPostage() {
            return postage;
        }

        public void setPostage(String postage) {
            this.postage = postage;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public String getWelfare() {
            return welfare;
        }

        public void setWelfare(String welfare) {
            this.welfare = welfare;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getNumbering() {
            return numbering;
        }

        public void setNumbering(String numbering) {
            this.numbering = numbering;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public LogisticsBean getLogistics() {
            return logistics;
        }

        public void setLogistics(LogisticsBean logistics) {
            this.logistics = logistics;
        }

        public int getStatu() {
            return Statu;
        }

        public void setStatu(int Statu) {
            this.Statu = Statu;
        }

        public int getMore() {
            return More;
        }

        public void setMore(int More) {
            this.More = More;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class LogisticsBean {
            /**
             * time : 2019-11-26 00:02:51
             * ftime : 2019-11-26 00:02:51
             * context : 浙江杭州航空部-已装袋发往-北京转运中心
             * code : 200
             */

            private String time;
            private String ftime;
            private String context;
            private String code;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }

        public static class ListBean {
            /**
             * id : 316
             * orderlist_id : 5
             * title : 越南富士9
             * gg_title : 80-90公斤
             * pz_title : 山楂
             * price : 1.00
             * lanmu : 2
             * num : 1
             */

            private String id;
            private String orderlist_id;
            private String title;
            private String gg_title;
            private String pz_title;
            private String price;
            private String lanmu;
            private String num;
            private String pic;
            private String maintain;
            private String  After;

            public String getAfter() {
                return After;
            }

            public void setAfter(String after) {
                After = after;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getMaintain() {
                return maintain;
            }

            public void setMaintain(String maintain) {
                this.maintain = maintain;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrderlist_id() {
                return orderlist_id;
            }

            public void setOrderlist_id(String orderlist_id) {
                this.orderlist_id = orderlist_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getLanmu() {
                return lanmu;
            }

            public void setLanmu(String lanmu) {
                this.lanmu = lanmu;
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
