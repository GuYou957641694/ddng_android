package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class DetailassGoodBean {


    /**
     * msg : 查看成功
     * code : 200
     * data : {"list":{"id":"171","fid":"177","zt":"1","numbering":"2019091222134","uid":"46","tel":"15611036613","sheng":"南昌市","shi":"南昌市","qu":"江西省","address":"百子湾路","youbian":"123","name":"贾东升","ctime":"1568257899","total_price":"0.02","welfare":"0.10","postage":null,"insurance":"0.00","coupon":"0.00","coupon_id":"0","price":"0.12","farm_name":"北京朝阳农场1","etime":"1568259699","farm_id":"1","rtime":null,"trade_no":null,"return_status":"1","pay_time":"1568257900","confirm_time":null,"single":"2","details":[{"id":"185","fid":"171","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"1","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"botton4":"1","logistics":"暂无发货"},{"id":"184","fid":"171","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"1","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"botton4":"1","logistics":"暂无发货"}]}}
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
         * list : {"id":"171","fid":"177","zt":"1","numbering":"2019091222134","uid":"46","tel":"15611036613","sheng":"南昌市","shi":"南昌市","qu":"江西省","address":"百子湾路","youbian":"123","name":"贾东升","ctime":"1568257899","total_price":"0.02","welfare":"0.10","postage":null,"insurance":"0.00","coupon":"0.00","coupon_id":"0","price":"0.12","farm_name":"北京朝阳农场1","etime":"1568259699","farm_id":"1","rtime":null,"trade_no":null,"return_status":"1","pay_time":"1568257900","confirm_time":null,"single":"2","details":[{"id":"185","fid":"171","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"1","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"botton4":"1","logistics":"暂无发货"},{"id":"184","fid":"171","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"1","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"botton4":"1","logistics":"暂无发货"}]}
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
             * id : 171
             * fid : 177
             * zt : 1
             * numbering : 2019091222134
             * uid : 46
             * tel : 15611036613
             * sheng : 南昌市
             * shi : 南昌市
             * qu : 江西省
             * address : 百子湾路
             * youbian : 123
             * name : 贾东升
             * ctime : 1568257899
             * total_price : 0.02
             * welfare : 0.10
             * postage : null
             * insurance : 0.00
             * coupon : 0.00
             * coupon_id : 0
             * price : 0.12
             * farm_name : 北京朝阳农场1
             * etime : 1568259699
             * farm_id : 1
             * rtime : null
             * trade_no : null
             * return_status : 1
             * pay_time : 1568257900
             * confirm_time : null
             * single : 2
             * details : [{"id":"185","fid":"171","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"1","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"botton4":"1","logistics":"暂无发货"},{"id":"184","fid":"171","title":"香蕉果树","num":"1","cp_id":"4","gg_id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"10-20公斤","pz_title":"西梅","price":"0.01","nc_id":"1","js_lx":"2","lanmu":"1","refund":"2","bq_title":null,"t_name":null,"leave_message":null,"maintain":null,"numbering":null,"zt":"1","single":"2","tracking_id":null,"rtime":null,"confirm_time":null,"tracking_title":null,"botton4":"1","logistics":"暂无发货"}]
             */

            private String id;
            private String fid;
            private String zt;
            private String numbering;
            private String uid;
            private String tel;
            private String sheng;
            private String shi;
            private String qu;
            private String address;
            private String youbian;
            private String name;
            private String ctime;
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
            private String rtime;
            private String trade_no;
            private String return_status;
            private long pay_time;
            private String confirm_time;
            private String single;
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

            public String getZt() {
                return zt;
            }

            public void setZt(String zt) {
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

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
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

            public String getRtime() {
                return rtime;
            }

            public void setRtime(String rtime) {
                this.rtime = rtime;
            }

            public String getTrade_no() {
                return trade_no;
            }

            public void setTrade_no(String trade_no) {
                this.trade_no = trade_no;
            }

            public String getReturn_status() {
                return return_status;
            }

            public void setReturn_status(String return_status) {
                this.return_status = return_status;
            }

            public long getPay_time() {
                return pay_time;
            }

            public void setPay_time(long pay_time) {
                this.pay_time = pay_time;
            }

            public String getConfirm_time() {
                return confirm_time;
            }

            public void setConfirm_time(String confirm_time) {
                this.confirm_time = confirm_time;
            }

            public String getSingle() {
                return single;
            }

            public void setSingle(String single) {
                this.single = single;
            }

            public List<DetailsBean> getDetails() {
                return details;
            }

            public void setDetails(List<DetailsBean> details) {
                this.details = details;
            }

            public static class DetailsBean {
                /**
                 * id : 185
                 * fid : 171
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
                 * zt : 1
                 * single : 2
                 * tracking_id : null
                 * rtime : null
                 * confirm_time : null
                 * tracking_title : null
                 * botton4 : 1
                 * logistics : 暂无发货
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
                private String bq_title;
                private String t_name;
                private String leave_message;
                private String maintain;
                private String numbering;
                private String zt;
                private String single;
                private String tracking_id;
                private String rtime;
                private String confirm_time;
                private String tracking_title;
                private String botton4;
                private String logistics;

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

                public String getBq_title() {
                    return bq_title;
                }

                public void setBq_title(String bq_title) {
                    this.bq_title = bq_title;
                }

                public String getT_name() {
                    return t_name;
                }

                public void setT_name(String t_name) {
                    this.t_name = t_name;
                }

                public String getLeave_message() {
                    return leave_message;
                }

                public void setLeave_message(String leave_message) {
                    this.leave_message = leave_message;
                }

                public String getMaintain() {
                    return maintain;
                }

                public void setMaintain(String maintain) {
                    this.maintain = maintain;
                }

                public String getNumbering() {
                    return numbering;
                }

                public void setNumbering(String numbering) {
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

                public String getTracking_id() {
                    return tracking_id;
                }

                public void setTracking_id(String tracking_id) {
                    this.tracking_id = tracking_id;
                }

                public String getRtime() {
                    return rtime;
                }

                public void setRtime(String rtime) {
                    this.rtime = rtime;
                }

                public String getConfirm_time() {
                    return confirm_time;
                }

                public void setConfirm_time(String confirm_time) {
                    this.confirm_time = confirm_time;
                }

                public String getTracking_title() {
                    return tracking_title;
                }

                public void setTracking_title(String tracking_title) {
                    this.tracking_title = tracking_title;
                }

                public String getBotton4() {
                    return botton4;
                }

                public void setBotton4(String botton4) {
                    this.botton4 = botton4;
                }

                public String getLogistics() {
                    return logistics;
                }

                public void setLogistics(String logistics) {
                    this.logistics = logistics;
                }
            }
        }
    }
}
