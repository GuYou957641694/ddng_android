package com.bigpumpkin.app.ddng_android.bean;

public class SingleBean {


    /**
     * msg : success
     * code : 200
     * data : {"name":"董先生","tel":"13146463762","sheng":"北京","shi":"北京市","qu":"朝阳区","address":"百子湾后现代城5A1006室","numbering":"201904191042201555641740","ctime":"1555641640","amount":"44.00","nc_title":"北京朝阳农场1","title":"香蕉果树","gg_title":"50-60公斤","img_pic":"/data/img/1811/5bfb9a1f70711.png","price":"100.00","cp_id":"4","farm_name":"北京朝阳农场1","pic":"/data/img/1811/5bfb9a1f70711.png","postage":"1.00","refund":"7天无理由退货退款","leave_message":"1970-01-01 08:00:00"}
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
         * name : 董先生
         * tel : 13146463762
         * sheng : 北京
         * shi : 北京市
         * qu : 朝阳区
         * address : 百子湾后现代城5A1006室
         * numbering : 201904191042201555641740
         * ctime : 1555641640
         * amount : 44.00
         * nc_title : 北京朝阳农场1
         * title : 香蕉果树
         * gg_title : 50-60公斤
         * img_pic : /data/img/1811/5bfb9a1f70711.png
         * price : 100.00
         * cp_id : 4
         * farm_name : 北京朝阳农场1
         * pic : /data/img/1811/5bfb9a1f70711.png
         * postage : 1.00
         * refund : 7天无理由退货退款
         * leave_message : 1970-01-01 08:00:00
         */

        private String name;
        private String tel;
        private String sheng;
        private String shi;
        private String qu;
        private String address;
        private String numbering;
        private long ctime;
        private String amount;
        private String nc_title;
        private String title;
        private String gg_title;
        private String img_pic;
        private String price;
        private String cp_id;
        private String farm_name;
        private String pic;
        private String postage;
        private String refund;
        private String leave_message;

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

        public String getNumbering() {
            return numbering;
        }

        public void setNumbering(String numbering) {
            this.numbering = numbering;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getNc_title() {
            return nc_title;
        }

        public void setNc_title(String nc_title) {
            this.nc_title = nc_title;
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

        public String getImg_pic() {
            return img_pic;
        }

        public void setImg_pic(String img_pic) {
            this.img_pic = img_pic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCp_id() {
            return cp_id;
        }

        public void setCp_id(String cp_id) {
            this.cp_id = cp_id;
        }

        public String getFarm_name() {
            return farm_name;
        }

        public void setFarm_name(String farm_name) {
            this.farm_name = farm_name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPostage() {
            return postage;
        }

        public void setPostage(String postage) {
            this.postage = postage;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getLeave_message() {
            return leave_message;
        }

        public void setLeave_message(String leave_message) {
            this.leave_message = leave_message;
        }
    }
}
