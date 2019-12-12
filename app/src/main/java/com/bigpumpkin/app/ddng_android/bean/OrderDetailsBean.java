package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class OrderDetailsBean {


    /**
     * msg : 查看成功
     * code : 200
     * data : {"list":{"id":"340","fid":"354","zt":2,"numbering":"2019092379729","uid":"57","tel":"11236655888","sheng":"济南市","shi":"济南市","qu":"山东省","address":"佳通","youbian":"123","name":"贾东升","ctime":"1569219230","total_price":"0.01","welfare":"0.10","postage":"1.00","insurance":"0.00","coupon":"0.00","coupon_id":"0","price":"1.11","farm_name":"北京朝阳农场1","etime":"1569221030","farm_id":"1","rtime":null,"trade_no":null,"return_status":"1","pay_time":null,"confirm_time":null,"single":"2","message":"无留言","botton2":"1","details":[{"id":"436","fid":"340","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"3","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"payment_method":null,"button6":"1"}]}}
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
         * list : {"id":"340","fid":"354","zt":2,"numbering":"2019092379729","uid":"57","tel":"11236655888","sheng":"济南市","shi":"济南市","qu":"山东省","address":"佳通","youbian":"123","name":"贾东升","ctime":"1569219230","total_price":"0.01","welfare":"0.10","postage":"1.00","insurance":"0.00","coupon":"0.00","coupon_id":"0","price":"1.11","farm_name":"北京朝阳农场1","etime":"1569221030","farm_id":"1","rtime":null,"trade_no":null,"return_status":"1","pay_time":null,"confirm_time":null,"single":"2","message":"无留言","botton2":"1","details":[{"id":"436","fid":"340","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"3","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"payment_method":null,"button6":"1"}]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 340
             * fid : 354
             * zt : 2
             * numbering : 2019092379729
             * uid : 57
             * tel : 11236655888
             * sheng : 济南市
             * shi : 济南市
             * qu : 山东省
             * address : 佳通
             * youbian : 123
             * name : 贾东升
             * ctime : 1569219230
             * total_price : 0.01
             * welfare : 0.10
             * postage : 1.00
             * insurance : 0.00
             * coupon : 0.00
             * coupon_id : 0
             * price : 1.11
             * farm_name : 北京朝阳农场1
             * etime : 1569221030
             * farm_id : 1
             * rtime : null
             * trade_no : null
             * return_status : 1
             * pay_time : null
             * confirm_time : null
             * single : 2
             * message : 无留言
             * botton2 : 1
             * details : [{"id":"436","fid":"340","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"3","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"payment_method":null,"button6":"1"}]
             */

            private String id;
            private String fid;
            private int zt;
            private String numbering;
            private String uid;
            private String tel;
            private String sheng;
            private String shi;
            private String qu;
            private String address;
            private String youbian;
            private String name;
            private long ctime;
            private String total_price;
            private String welfare;
            private String postage;
            private String insurance;
            private String coupon;
            private String coupon_id;
            private String price;
            private String farm_name;
            private String etime;
            private String farm_id;
            private long rtime;
            private Object trade_no;
            private String return_status;
            private Object pay_time;
            private Object confirm_time;
            private String single;
            private String message;
            private String botton2;
            private List<DetailsBean> details;

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

            public int getZt() {
                return zt;
            }

            public void setZt(int zt) {
                this.zt = zt;
            }

            public String getNumbering() {
                return numbering;
            }

            public void setNumbering(String numbering) {
                this.numbering = numbering;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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

            public String getYoubian() {
                return youbian;
            }

            public void setYoubian(String youbian) {
                this.youbian = youbian;
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

            public String getWelfare() {
                return welfare;
            }

            public void setWelfare(String welfare) {
                this.welfare = welfare;
            }

            public String getPostage() {
                return postage;
            }

            public void setPostage(String postage) {
                this.postage = postage;
            }

            public String getInsurance() {
                return insurance;
            }

            public void setInsurance(String insurance) {
                this.insurance = insurance;
            }

            public String getCoupon() {
                return coupon;
            }

            public void setCoupon(String coupon) {
                this.coupon = coupon;
            }

            public String getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(String coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getFarm_name() {
                return farm_name;
            }

            public void setFarm_name(String farm_name) {
                this.farm_name = farm_name;
            }

            public String getEtime() {
                return etime;
            }

            public void setEtime(String etime) {
                this.etime = etime;
            }

            public String getFarm_id() {
                return farm_id;
            }

            public void setFarm_id(String farm_id) {
                this.farm_id = farm_id;
            }

            public long getRtime() {
                return rtime;
            }

            public void setRtime(long rtime) {
                this.rtime = rtime;
            }

            public Object getTrade_no() {
                return trade_no;
            }

            public void setTrade_no(Object trade_no) {
                this.trade_no = trade_no;
            }

            public String getReturn_status() {
                return return_status;
            }

            public void setReturn_status(String return_status) {
                this.return_status = return_status;
            }

            public Object getPay_time() {
                return pay_time;
            }

            public void setPay_time(Object pay_time) {
                this.pay_time = pay_time;
            }

            public Object getConfirm_time() {
                return confirm_time;
            }

            public void setConfirm_time(Object confirm_time) {
                this.confirm_time = confirm_time;
            }

            public String getSingle() {
                return single;
            }

            public void setSingle(String single) {
                this.single = single;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getBotton2() {
                return botton2;
            }

            public void setBotton2(String botton2) {
                this.botton2 = botton2;
            }

            public List<DetailsBean> getDetails() {
                return details;
            }

            public void setDetails(List<DetailsBean> details) {
                this.details = details;
            }

            public static class DetailsBean {
                /**
                 * id : 436
                 * fid : 340
                 * title : 香蕉果树
                 * num : 1
                 * cp_id : 4
                 * gg_id : 2
                 * pic : /data/img/1811/5bfb9a1f70711.png
                 * gg_title : 10-20公斤
                 * pz_title : 西梅
                 * price : 0.01
                 * nc_id : 1
                 * js_lx : 2
                 * lanmu : 1
                 * refund : 2
                 * bq_title : null
                 * t_name : null
                 * leave_message : null
                 * maintain : null
                 * numbering : null
                 * zt : 3
                 * single : 2
                 * tracking_id : null
                 * rtime : null
                 * confirm_time : null
                 * tracking_title : null
                 * payment_method : null
                 * button6 : 1
                 */

                private String id;
                private String fid;
                private String title;
                private String num;
                private String cp_id;
                private String gg_id;
                private String pic;
                private String gg_title;
                private String pz_title;
                private String price;
                private String nc_id;
                private String js_lx;
                private String lanmu;
                private String refund;
                private Object bq_title;
                private Object t_name;
                private Object leave_message;
                private Object maintain;
                private Object numbering;
                private String zt;
                private String single;
                private Object tracking_id;
                private Object rtime;
                private Object confirm_time;
                private Object tracking_title;
                private Object payment_method;
                private String button6;

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

                public String getCp_id() {
                    return cp_id;
                }

                public void setCp_id(String cp_id) {
                    this.cp_id = cp_id;
                }

                public String getGg_id() {
                    return gg_id;
                }

                public void setGg_id(String gg_id) {
                    this.gg_id = gg_id;
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

                public String getNc_id() {
                    return nc_id;
                }

                public void setNc_id(String nc_id) {
                    this.nc_id = nc_id;
                }

                public String getJs_lx() {
                    return js_lx;
                }

                public void setJs_lx(String js_lx) {
                    this.js_lx = js_lx;
                }

                public String getLanmu() {
                    return lanmu;
                }

                public void setLanmu(String lanmu) {
                    this.lanmu = lanmu;
                }

                public String getRefund() {
                    return refund;
                }

                public void setRefund(String refund) {
                    this.refund = refund;
                }

                public Object getBq_title() {
                    return bq_title;
                }

                public void setBq_title(Object bq_title) {
                    this.bq_title = bq_title;
                }

                public Object getT_name() {
                    return t_name;
                }

                public void setT_name(Object t_name) {
                    this.t_name = t_name;
                }

                public Object getLeave_message() {
                    return leave_message;
                }

                public void setLeave_message(Object leave_message) {
                    this.leave_message = leave_message;
                }

                public Object getMaintain() {
                    return maintain;
                }

                public void setMaintain(Object maintain) {
                    this.maintain = maintain;
                }

                public Object getNumbering() {
                    return numbering;
                }

                public void setNumbering(Object numbering) {
                    this.numbering = numbering;
                }

                public String getZt() {
                    return zt;
                }

                public void setZt(String zt) {
                    this.zt = zt;
                }

                public String getSingle() {
                    return single;
                }

                public void setSingle(String single) {
                    this.single = single;
                }

                public Object getTracking_id() {
                    return tracking_id;
                }

                public void setTracking_id(Object tracking_id) {
                    this.tracking_id = tracking_id;
                }

                public Object getRtime() {
                    return rtime;
                }

                public void setRtime(Object rtime) {
                    this.rtime = rtime;
                }

                public Object getConfirm_time() {
                    return confirm_time;
                }

                public void setConfirm_time(Object confirm_time) {
                    this.confirm_time = confirm_time;
                }

                public Object getTracking_title() {
                    return tracking_title;
                }

                public void setTracking_title(Object tracking_title) {
                    this.tracking_title = tracking_title;
                }

                public Object getPayment_method() {
                    return payment_method;
                }

                public void setPayment_method(Object payment_method) {
                    this.payment_method = payment_method;
                }

                public String getButton6() {
                    return button6;
                }

                public void setButton6(String button6) {
                    this.button6 = button6;
                }
            }
        }
    }
}
