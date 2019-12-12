package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class NewBuyNowBean {


    /**
     * msg : success
     * code : 200
     * data : {"address":{"id":"44","uid":"2","tel":"15668844455","isindex":"1","sheng":"天津市","sheng_code":"120000","shi":"市辖区","shi_code":"120100","qu":"宝坻区 ","qu_code":"120115","address":"通","youbian":"0","name":"挑食"},"shop":{"welfare":23,"Insurance":457,"essay_name":"北京朝阳农场1","essay_id":"1","id":"4","title":"香蕉果树","pic":"/data/img/1910/5da92e55cb821.jpg","price":457.15,"variety":"黑枣","variety_id":"1886","sp_title":"20-30公斤","sp_id":"9422","sum":"50","postage_price":0,"pay_price":23322.5},"member_coupon":[{"id":"16","title":"惠","man":"300","jian":"15","attr":"2","nc_id":"1"},{"id":"13","title":"惠","man":"200","jian":"10","attr":"2","nc_id":"1"},{"id":"14","title":"5元优惠","man":"200","jian":"5","attr":"2","nc_id":"1"},{"id":"12","title":"惠","man":"100","jian":"3","attr":"2","nc_id":"1"},{"id":"15","title":"惠","man":"200","jian":"3","attr":"2","nc_id":"1"},{"id":"11","title":"惠","man":"100","jian":"2","attr":"2","nc_id":"1"},{"id":"17","title":"惠","man":"100","jian":"2","attr":"2","nc_id":"1"}]}
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
         * address : {"id":"44","uid":"2","tel":"15668844455","isindex":"1","sheng":"天津市","sheng_code":"120000","shi":"市辖区","shi_code":"120100","qu":"宝坻区 ","qu_code":"120115","address":"通","youbian":"0","name":"挑食"}
         * shop : {"welfare":23,"Insurance":457,"essay_name":"北京朝阳农场1","essay_id":"1","id":"4","title":"香蕉果树","pic":"/data/img/1910/5da92e55cb821.jpg","price":457.15,"variety":"黑枣","variety_id":"1886","sp_title":"20-30公斤","sp_id":"9422","sum":"50","postage_price":0,"pay_price":23322.5}
         * member_coupon : [{"id":"16","title":"惠","man":"300","jian":"15","attr":"2","nc_id":"1"},{"id":"13","title":"惠","man":"200","jian":"10","attr":"2","nc_id":"1"},{"id":"14","title":"5元优惠","man":"200","jian":"5","attr":"2","nc_id":"1"},{"id":"12","title":"惠","man":"100","jian":"3","attr":"2","nc_id":"1"},{"id":"15","title":"惠","man":"200","jian":"3","attr":"2","nc_id":"1"},{"id":"11","title":"惠","man":"100","jian":"2","attr":"2","nc_id":"1"},{"id":"17","title":"惠","man":"100","jian":"2","attr":"2","nc_id":"1"}]
         */

        private AddressBean address;
        private ShopBean shop;
        private List<MemberCouponBean> member_coupon;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public List<MemberCouponBean> getMember_coupon() {
            return member_coupon;
        }

        public void setMember_coupon(List<MemberCouponBean> member_coupon) {
            this.member_coupon = member_coupon;
        }

        public static class AddressBean {
            /**
             * id : 44
             * uid : 2
             * tel : 15668844455
             * isindex : 1
             * sheng : 天津市
             * sheng_code : 120000
             * shi : 市辖区
             * shi_code : 120100
             * qu : 宝坻区
             * qu_code : 120115
             * address : 通
             * youbian : 0
             * name : 挑食
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
             * welfare : 23
             * Insurance : 457
             * essay_name : 北京朝阳农场1
             * essay_id : 1
             * id : 4
             * title : 香蕉果树
             * pic : /data/img/1910/5da92e55cb821.jpg
             * price : 457.15
             * variety : 黑枣
             * variety_id : 1886
             * sp_title : 20-30公斤
             * sp_id : 9422
             * sum : 50
             * postage_price : 0
             * pay_price : 23322.5
             */

            private String welfare;
            private String Insurance;
            private String essay_name;
            private String essay_id;
            private String id;
            private String title;
            private String pic;
            private String price;
            private String variety;
            private String variety_id;
            private String sp_title;
            private String sp_id;
            private String sum;
            private String postage_price;
            private String jian;
            private String pay_price;
            private String lanmu;

            public String getLanmu() {
                return lanmu;
            }

            public void setLanmu(String lanmu) {
                this.lanmu = lanmu;
            }

            public String getJian() {
                return jian;
            }

            public void setJian(String jian) {
                this.jian = jian;
            }

            public String getWelfare() {
                return welfare;
            }

            public void setWelfare(String welfare) {
                this.welfare = welfare;
            }

            public String getInsurance() {
                return Insurance;
            }

            public void setInsurance(String Insurance) {
                this.Insurance = Insurance;
            }

            public String getEssay_name() {
                return essay_name;
            }

            public void setEssay_name(String essay_name) {
                this.essay_name = essay_name;
            }

            public String getEssay_id() {
                return essay_id;
            }

            public void setEssay_id(String essay_id) {
                this.essay_id = essay_id;
            }

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

            public String getVariety() {
                return variety;
            }

            public void setVariety(String variety) {
                this.variety = variety;
            }

            public String getVariety_id() {
                return variety_id;
            }

            public void setVariety_id(String variety_id) {
                this.variety_id = variety_id;
            }

            public String getSp_title() {
                return sp_title;
            }

            public void setSp_title(String sp_title) {
                this.sp_title = sp_title;
            }

            public String getSp_id() {
                return sp_id;
            }

            public void setSp_id(String sp_id) {
                this.sp_id = sp_id;
            }

            public String getSum() {
                return sum;
            }

            public void setSum(String sum) {
                this.sum = sum;
            }

            public String getPostage_price() {
                return postage_price;
            }

            public void setPostage_price(String postage_price) {
                this.postage_price = postage_price;
            }

            public String getPay_price() {
                return pay_price;
            }

            public void setPay_price(String pay_price) {
                this.pay_price = pay_price;
            }
        }

        public static class MemberCouponBean {
            /**
             * id : 16
             * title : 惠
             * man : 300
             * jian : 15
             * attr : 2
             * nc_id : 1
             */

            private String id;
            private String title;
            private String man;
            private String jian;
            private String attr;
            private String nc_id;

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

            public String getAttr() {
                return attr;
            }

            public void setAttr(String attr) {
                this.attr = attr;
            }

            public String getNc_id() {
                return nc_id;
            }

            public void setNc_id(String nc_id) {
                this.nc_id = nc_id;
            }
        }
    }
}
