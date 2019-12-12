package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Ordinary {


    /**
     * msg : success
     * code : 200
     * data : {"name":"哈哈","tel":"15611836613","sheng":"上海市","shi":"市辖区","qu":"长宁区 ","address":"踏踏啊","logistics":{"time":"2019-11-29 20:06:39","ftime":"2019-11-29 20:06:39","context":"客户签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15611635156，投诉电话：010-52729888"},"farm_name":"北京朝阳农场1","farm_id":"1","list":[{"orderlist_id":"1","cp_id":"534","pic":"/data/img/1911/5dc0eba180231.jpg","gg_title":"40-50公斤","pz_title":"芒果","price":"75.35"},{"orderlist_id":"2","cp_id":"538","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"90-100公斤","pz_title":"水蜜桃","price":"94.00"}],"postage":"25.00","total_price":"169.35","price":"194.35","numbering":"2019120255282","ctime":"2019-12-02 13:46:32","pay_time":"2019-12-02 13:48:52","tracking_title":"圆通速递","tracking_number":"YT4242688631310"}
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
         * name : 哈哈
         * tel : 15611836613
         * sheng : 上海市
         * shi : 市辖区
         * qu : 长宁区
         * address : 踏踏啊
         * logistics : {"time":"2019-11-29 20:06:39","ftime":"2019-11-29 20:06:39","context":"客户签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15611635156，投诉电话：010-52729888"}
         * farm_name : 北京朝阳农场1
         * farm_id : 1
         * list : [{"orderlist_id":"1","cp_id":"534","pic":"/data/img/1911/5dc0eba180231.jpg","gg_title":"40-50公斤","pz_title":"芒果","price":"75.35"},{"orderlist_id":"2","cp_id":"538","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"90-100公斤","pz_title":"水蜜桃","price":"94.00"}]
         * postage : 25.00
         * total_price : 169.35
         * price : 194.35
         * numbering : 2019120255282
         * ctime : 2019-12-02 13:46:32
         * pay_time : 2019-12-02 13:48:52
         * tracking_title : 圆通速递
         * tracking_number : YT4242688631310
         */

        private String name;
        private String tel;
        private String sheng;
        private String shi;
        private String order_id;
        private String qu;
        private String address;
        private LogisticsBean logistics;
        private String farm_name;
        private String farm_id;
        private String postage;
        private String total_price;
        private String price;
        private String numbering;
        private String ctime;
        private String pay_time;
        private String receipt_time;
        private String tracking_title;
        private String tracking_number;
        private List<ListBean> list;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getReceipt_time() {
            return receipt_time;
        }

        public void setReceipt_time(String receipt_time) {
            this.receipt_time = receipt_time;
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

        public LogisticsBean getLogistics() {
            return logistics;
        }

        public void setLogistics(LogisticsBean logistics) {
            this.logistics = logistics;
        }

        public String getFarm_name() {
            return farm_name;
        }

        public void setFarm_name(String farm_name) {
            this.farm_name = farm_name;
        }

        public String getFarm_id() {
            return farm_id;
        }

        public void setFarm_id(String farm_id) {
            this.farm_id = farm_id;
        }

        public String getPostage() {
            return postage;
        }

        public void setPostage(String postage) {
            this.postage = postage;
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

        public String getNumbering() {
            return numbering;
        }

        public void setNumbering(String numbering) {
            this.numbering = numbering;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getTracking_title() {
            return tracking_title;
        }

        public void setTracking_title(String tracking_title) {
            this.tracking_title = tracking_title;
        }

        public String getTracking_number() {
            return tracking_number;
        }

        public void setTracking_number(String tracking_number) {
            this.tracking_number = tracking_number;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class LogisticsBean {
            /**
             * time : 2019-11-29 20:06:39
             * ftime : 2019-11-29 20:06:39
             * context : 客户签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15611635156，投诉电话：010-52729888
             */

            private String time;
            private String ftime;
            private String context;

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
        }

        public static class ListBean {
            /**
             * orderlist_id : 1
             * cp_id : 534
             * pic : /data/img/1911/5dc0eba180231.jpg
             * gg_title : 40-50公斤
             * pz_title : 芒果
             * price : 75.35
             */

            private String orderlist_id;
            private String cp_id;
            private String pic;
            private String gg_title;
            private String pz_title;
            private String price;
            private String title;
            private String num;
            private String lanmu;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getLanmu() {
                return lanmu;
            }

            public void setLanmu(String lanmu) {
                this.lanmu = lanmu;
            }

            public String getOrderlist_id() {
                return orderlist_id;
            }

            public void setOrderlist_id(String orderlist_id) {
                this.orderlist_id = orderlist_id;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
