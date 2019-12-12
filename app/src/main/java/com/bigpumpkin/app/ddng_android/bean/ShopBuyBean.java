package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class ShopBuyBean {


    /**
     * msg : success
     * code : 200
     * data : {"address":{"id":"43","uid":"1","tel":"15611836613","isindex":"1","sheng":"上海市","sheng_code":"310000","shi":"市辖区","shi_code":"310100","qu":"长宁区 ","qu_code":"310105","address":"踏踏啊","youbian":"0","name":"哈哈"},"shop":[{"f_id":"1","f_title":"北京朝阳农场1","insurance_enable_e":1,"s_list":[{"v_id":"2","v_title":"西梅","v_fid":"4","sp_id":"2","sp_title":"10-20公斤","price":3,"weight":"0","s_id":"4","s_fid":"1","s_pic":"/data/img/1910/5da92e55cb821.jpg","s_title":"香蕉果树","s_lanmu":"1","welfare":0,"insurance":0.06,"s_postage":"4","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"2","maintain":"0","s_price":6.06},{"v_id":"5","v_title":"砂糖桔","v_fid":"7","sp_id":"5","sp_title":"40-50公斤","price":"323.00","weight":"0","s_id":"7","s_fid":"1","s_pic":"/data/img/1901/5c341411223c8.png","s_title":"北京富士果树7","s_lanmu":"2","s_postage":"6","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"3","s_price":969},{"v_id":"2","v_title":"西梅","v_fid":"4","sp_id":"7538","sp_title":"20-30公斤","price":407.6,"weight":"0","s_id":"4","s_fid":"1","s_pic":"/data/img/1910/5da92e55cb821.jpg","s_title":"香蕉果树","s_lanmu":"1","welfare":0.41,"insurance":8.15,"s_postage":"4","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"1","maintain":"0","s_price":416.16}],"postage__price":66,"f_num_price":1351.22,"welfare":0.41,"f_num_insurance":8.21,"member_coupon":[{"id":"20","man":"500","jian":"40"},{"id":"18","man":"100","jian":"3"}],"member_coupon_Use":{"id":"20","man":"500","jian":"40"}},{"f_id":"3","f_title":"北京海淀农场3","insurance_enable_e":1,"s_list":[{"v_id":"4","v_title":"菠萝","v_fid":"6","sp_id":"4","sp_title":"60-70公斤","price":10,"weight":"5","s_id":"6","s_fid":"3","s_pic":"/data/img/1910/5da92e25775bb.jpg","s_title":"荔枝果树","s_lanmu":"1","welfare":0.01,"insurance":0.2,"s_postage":"4","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"3","maintain":"0","s_price":30.21}],"postage__price":6,"f_num_price":30.21,"welfare":0.01,"f_num_insurance":0.2}],"total_Postage":72,"pay_price":1453.43}
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
         * address : {"id":"43","uid":"1","tel":"15611836613","isindex":"1","sheng":"上海市","sheng_code":"310000","shi":"市辖区","shi_code":"310100","qu":"长宁区 ","qu_code":"310105","address":"踏踏啊","youbian":"0","name":"哈哈"}
         * shop : [{"f_id":"1","f_title":"北京朝阳农场1","insurance_enable_e":1,"s_list":[{"v_id":"2","v_title":"西梅","v_fid":"4","sp_id":"2","sp_title":"10-20公斤","price":3,"weight":"0","s_id":"4","s_fid":"1","s_pic":"/data/img/1910/5da92e55cb821.jpg","s_title":"香蕉果树","s_lanmu":"1","welfare":0,"insurance":0.06,"s_postage":"4","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"2","maintain":"0","s_price":6.06},{"v_id":"5","v_title":"砂糖桔","v_fid":"7","sp_id":"5","sp_title":"40-50公斤","price":"323.00","weight":"0","s_id":"7","s_fid":"1","s_pic":"/data/img/1901/5c341411223c8.png","s_title":"北京富士果树7","s_lanmu":"2","s_postage":"6","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"3","s_price":969},{"v_id":"2","v_title":"西梅","v_fid":"4","sp_id":"7538","sp_title":"20-30公斤","price":407.6,"weight":"0","s_id":"4","s_fid":"1","s_pic":"/data/img/1910/5da92e55cb821.jpg","s_title":"香蕉果树","s_lanmu":"1","welfare":0.41,"insurance":8.15,"s_postage":"4","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"1","maintain":"0","s_price":416.16}],"postage__price":66,"f_num_price":1351.22,"welfare":0.41,"f_num_insurance":8.21,"member_coupon":[{"id":"20","man":"500","jian":"40"},{"id":"18","man":"100","jian":"3"}],"member_coupon_Use":{"id":"20","man":"500","jian":"40"}},{"f_id":"3","f_title":"北京海淀农场3","insurance_enable_e":1,"s_list":[{"v_id":"4","v_title":"菠萝","v_fid":"6","sp_id":"4","sp_title":"60-70公斤","price":10,"weight":"5","s_id":"6","s_fid":"3","s_pic":"/data/img/1910/5da92e25775bb.jpg","s_title":"荔枝果树","s_lanmu":"1","welfare":0.01,"insurance":0.2,"s_postage":"4","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"3","maintain":"0","s_price":30.21}],"postage__price":6,"f_num_price":30.21,"welfare":0.01,"f_num_insurance":0.2}]
         * total_Postage : 72
         * pay_price : 1453.43
         */

        private AddressBean address;
        private String total_Postage;
        private String pay_price;
        private List<ShopBean> shop;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public String getTotal_Postage() {
            return total_Postage;
        }

        public void setTotal_Postage(String total_Postage) {
            this.total_Postage = total_Postage;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public List<ShopBean> getShop() {
            return shop;
        }

        public void setShop(List<ShopBean> shop) {
            this.shop = shop;
        }

        public static class AddressBean {
            /**
             * id : 43
             * uid : 1
             * tel : 15611836613
             * isindex : 1
             * sheng : 上海市
             * sheng_code : 310000
             * shi : 市辖区
             * shi_code : 310100
             * qu : 长宁区
             * qu_code : 310105
             * address : 踏踏啊
             * youbian : 0
             * name : 哈哈
             */

            private String id;
            private String uid;
            private String tel;
            private String isindex;
            private String sheng;
            private String sheng_code;
            private String shi;
            private String shi_code;
            private String qu;
            private String qu_code;
            private String address;
            private String youbian;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getIsindex() {
                return isindex;
            }

            public void setIsindex(String isindex) {
                this.isindex = isindex;
            }

            public String getSheng() {
                return sheng;
            }

            public void setSheng(String sheng) {
                this.sheng = sheng;
            }

            public String getSheng_code() {
                return sheng_code;
            }

            public void setSheng_code(String sheng_code) {
                this.sheng_code = sheng_code;
            }

            public String getShi() {
                return shi;
            }

            public void setShi(String shi) {
                this.shi = shi;
            }

            public String getShi_code() {
                return shi_code;
            }

            public void setShi_code(String shi_code) {
                this.shi_code = shi_code;
            }

            public String getQu() {
                return qu;
            }

            public void setQu(String qu) {
                this.qu = qu;
            }

            public String getQu_code() {
                return qu_code;
            }

            public void setQu_code(String qu_code) {
                this.qu_code = qu_code;
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
        }

        public static class ShopBean {
            /**
             * f_id : 1
             * f_title : 北京朝阳农场1
             * insurance_enable_e : 1
             * s_list : [{"v_id":"2","v_title":"西梅","v_fid":"4","sp_id":"2","sp_title":"10-20公斤","price":3,"weight":"0","s_id":"4","s_fid":"1","s_pic":"/data/img/1910/5da92e55cb821.jpg","s_title":"香蕉果树","s_lanmu":"1","welfare":0,"insurance":0.06,"s_postage":"4","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"2","maintain":"0","s_price":6.06},{"v_id":"5","v_title":"砂糖桔","v_fid":"7","sp_id":"5","sp_title":"40-50公斤","price":"323.00","weight":"0","s_id":"7","s_fid":"1","s_pic":"/data/img/1901/5c341411223c8.png","s_title":"北京富士果树7","s_lanmu":"2","s_postage":"6","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"3","s_price":969},{"v_id":"2","v_title":"西梅","v_fid":"4","sp_id":"7538","sp_title":"20-30公斤","price":407.6,"weight":"0","s_id":"4","s_fid":"1","s_pic":"/data/img/1910/5da92e55cb821.jpg","s_title":"香蕉果树","s_lanmu":"1","welfare":0.41,"insurance":8.15,"s_postage":"4","refund":{"id":"2","title":"7天无理由退货退款"},"c_num":"1","maintain":"0","s_price":416.16}]
             * postage__price : 66
             * f_num_price : 1351.22
             * welfare : 0.41
             * f_num_insurance : 8.21
             * member_coupon : [{"id":"20","man":"500","jian":"40"},{"id":"18","man":"100","jian":"3"}]
             * member_coupon_Use : {"id":"20","man":"500","jian":"40"}
             */

            private String f_id;
            private String f_title;
            private int insurance_enable_e;
            private String postage__price;
            private String f_num_price;
            private String welfare;
            private String f_num_insurance;
            private MemberCouponUseBean member_coupon_Use;
            private boolean insurance;
            private List<SListBean> s_list;
            private List<MemberCouponBean> member_coupon;

            public boolean isInsurance() {
                return insurance;
            }

            public void setInsurance(boolean insurance) {
                this.insurance = insurance;
            }

            public String getF_id() {
                return f_id;
            }

            public void setF_id(String f_id) {
                this.f_id = f_id;
            }

            public String getF_title() {
                return f_title;
            }

            public void setF_title(String f_title) {
                this.f_title = f_title;
            }

            public int getInsurance_enable_e() {
                return insurance_enable_e;
            }

            public void setInsurance_enable_e(int insurance_enable_e) {
                this.insurance_enable_e = insurance_enable_e;
            }

            public String getPostage__price() {
                return postage__price;
            }

            public void setPostage__price(String postage__price) {
                this.postage__price = postage__price;
            }

            public String getF_num_price() {
                return f_num_price;
            }

            public void setF_num_price(String f_num_price) {
                this.f_num_price = f_num_price;
            }

            public String getWelfare() {
                return welfare;
            }

            public void setWelfare(String welfare) {
                this.welfare = welfare;
            }

            public String getF_num_insurance() {
                return f_num_insurance;
            }

            public void setF_num_insurance(String f_num_insurance) {
                this.f_num_insurance = f_num_insurance;
            }

            public MemberCouponUseBean getMember_coupon_Use() {
                return member_coupon_Use;
            }

            public void setMember_coupon_Use(MemberCouponUseBean member_coupon_Use) {
                this.member_coupon_Use = member_coupon_Use;
            }

            public List<SListBean> getS_list() {
                return s_list;
            }

            public void setS_list(List<SListBean> s_list) {
                this.s_list = s_list;
            }

            public List<MemberCouponBean> getMember_coupon() {
                return member_coupon;
            }

            public void setMember_coupon(List<MemberCouponBean> member_coupon) {
                this.member_coupon = member_coupon;
            }

            public static class MemberCouponUseBean {
                /**
                 * id : 20
                 * man : 500
                 * jian : 40
                 */

                private String id;
                private String man;
                private String jian;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMan() {
                    return man;
                }

                public void setMan(String man) {
                    this.man = man;
                }

                public String getJian() {
                    return jian;
                }

                public void setJian(String jian) {
                    this.jian = jian;
                }
            }

            public static class SListBean {
                /**
                 * v_id : 2
                 * v_title : 西梅
                 * v_fid : 4
                 * sp_id : 2
                 * sp_title : 10-20公斤
                 * price : 3
                 * weight : 0
                 * s_id : 4
                 * s_fid : 1
                 * s_pic : /data/img/1910/5da92e55cb821.jpg
                 * s_title : 香蕉果树
                 * s_lanmu : 1
                 * welfare : 0
                 * insurance : 0.06
                 * s_postage : 4
                 * refund : {"id":"2","title":"7天无理由退货退款"}
                 * c_num : 2
                 * maintain : 0
                 * s_price : 6.06
                 */

                private String v_id;
                private String v_title;
                private String v_fid;
                private String sp_id;
                private String sp_title;
                private String price;
                private String weight;
                private String s_id;
                private String s_fid;
                private String s_pic;
                private String s_title;
                private String s_lanmu;
                private String welfare;
                private double insurance;
                private String s_postage;
                private RefundBean refund;
                private String c_num;
                private String maintain;
                private String s_price;

                public String getV_id() {
                    return v_id;
                }

                public void setV_id(String v_id) {
                    this.v_id = v_id;
                }

                public String getV_title() {
                    return v_title;
                }

                public void setV_title(String v_title) {
                    this.v_title = v_title;
                }

                public String getV_fid() {
                    return v_fid;
                }

                public void setV_fid(String v_fid) {
                    this.v_fid = v_fid;
                }

                public String getSp_id() {
                    return sp_id;
                }

                public void setSp_id(String sp_id) {
                    this.sp_id = sp_id;
                }

                public String getSp_title() {
                    return sp_title;
                }

                public void setSp_title(String sp_title) {
                    this.sp_title = sp_title;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public String getS_id() {
                    return s_id;
                }

                public void setS_id(String s_id) {
                    this.s_id = s_id;
                }

                public String getS_fid() {
                    return s_fid;
                }

                public void setS_fid(String s_fid) {
                    this.s_fid = s_fid;
                }

                public String getS_pic() {
                    return s_pic;
                }

                public void setS_pic(String s_pic) {
                    this.s_pic = s_pic;
                }

                public String getS_title() {
                    return s_title;
                }

                public void setS_title(String s_title) {
                    this.s_title = s_title;
                }

                public String getS_lanmu() {
                    return s_lanmu;
                }

                public void setS_lanmu(String s_lanmu) {
                    this.s_lanmu = s_lanmu;
                }

                public String getWelfare() {
                    return welfare;
                }

                public void setWelfare(String welfare) {
                    this.welfare = welfare;
                }

                public double getInsurance() {
                    return insurance;
                }

                public void setInsurance(double insurance) {
                    this.insurance = insurance;
                }

                public String getS_postage() {
                    return s_postage;
                }

                public void setS_postage(String s_postage) {
                    this.s_postage = s_postage;
                }

                public RefundBean getRefund() {
                    return refund;
                }

                public void setRefund(RefundBean refund) {
                    this.refund = refund;
                }

                public String getC_num() {
                    return c_num;
                }

                public void setC_num(String c_num) {
                    this.c_num = c_num;
                }

                public String getMaintain() {
                    return maintain;
                }

                public void setMaintain(String maintain) {
                    this.maintain = maintain;
                }

                public String getS_price() {
                    return s_price;
                }

                public void setS_price(String s_price) {
                    this.s_price = s_price;
                }

                public static class RefundBean {
                    /**
                     * id : 2
                     * title : 7天无理由退货退款
                     */

                    private String id;
                    private String title;

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
                }
            }

            public static class MemberCouponBean {
                /**
                 * id : 20
                 * man : 500
                 * jian : 40
                 */

                private String id;
                private String man;
                private String jian;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMan() {
                    return man;
                }

                public void setMan(String man) {
                    this.man = man;
                }

                public String getJian() {
                    return jian;
                }

                public void setJian(String jian) {
                    this.jian = jian;
                }
            }
        }
    }
}
