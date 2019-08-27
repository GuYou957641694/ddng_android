package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Settlement_Bean {


    /**
     * msg : 成功
     * code : 200
     * data : {"address":{"id":"4","uid":"1","tel":"8555","isindex":"1","sheng":"沈阳市","shi":"沈阳市","qu":"辽宁省","address":"输了","youbian":"123","name":"后天"},"list":[{"title":"北京朝阳农场1","id":"7","nc_id":"1","details":[{"id":"10","fid":"7","cp_id":"461","gg_id":"9878","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"70-80公斤","pz_title":"黄心猕猴桃","title":"荔枝果树","num":"3","price":"421.00","maintain":["有机肥","要颜值","常规三分之一","需要","需要","果脯",""],"lanmu":"1","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","insurance":"0.00","welfare":"0.30"}],"total_price":1263,"jian":{"id":"1","uid":"1","j_id":"1","zt":"1","man":"500","jian":"5","ctime":"1550937600","etime":"1572019200","nc_id":null,"title":"首页全场优惠卷","attr":"1"}},{"title":"北京海淀农场9","id":"8","nc_id":"9","details":[{"id":"12","fid":"8","cp_id":"763","gg_id":"7353","pic":"/data/img/1901/5c3c00da33508.png","gg_title":"60-70公斤","pz_title":"云莓","title":"水果套餐36","num":"4","price":"81.00","maintain":null,"lanmu":"7","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","welfare":"0.40"}],"total_price":324,"jian":{"id":"3","uid":"1","j_id":"3","zt":"1","man":"5","jian":"5","ctime":"1550937600","etime":"1700086399","nc_id":null,"title":"5元优惠","attr":"1"}}],"shopping_all_price":"1,577.70"}
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
         * address : {"id":"4","uid":"1","tel":"8555","isindex":"1","sheng":"沈阳市","shi":"沈阳市","qu":"辽宁省","address":"输了","youbian":"123","name":"后天"}
         * list : [{"title":"北京朝阳农场1","id":"7","nc_id":"1","details":[{"id":"10","fid":"7","cp_id":"461","gg_id":"9878","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"70-80公斤","pz_title":"黄心猕猴桃","title":"荔枝果树","num":"3","price":"421.00","maintain":["有机肥","要颜值","常规三分之一","需要","需要","果脯",""],"lanmu":"1","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","insurance":"0.00","welfare":"0.30"}],"total_price":1263,"jian":{"id":"1","uid":"1","j_id":"1","zt":"1","man":"500","jian":"5","ctime":"1550937600","etime":"1572019200","nc_id":null,"title":"首页全场优惠卷","attr":"1"}},{"title":"北京海淀农场9","id":"8","nc_id":"9","details":[{"id":"12","fid":"8","cp_id":"763","gg_id":"7353","pic":"/data/img/1901/5c3c00da33508.png","gg_title":"60-70公斤","pz_title":"云莓","title":"水果套餐36","num":"4","price":"81.00","maintain":null,"lanmu":"7","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","welfare":"0.40"}],"total_price":324,"jian":{"id":"3","uid":"1","j_id":"3","zt":"1","man":"5","jian":"5","ctime":"1550937600","etime":"1700086399","nc_id":null,"title":"5元优惠","attr":"1"}}]
         * shopping_all_price : 1,577.70
         */

        private AddressBean address;
        private String shopping_all_price;
        private List<ListBean> list;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public String getShopping_all_price() {
            return shopping_all_price;
        }

        public void setShopping_all_price(String shopping_all_price) {
            this.shopping_all_price = shopping_all_price;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AddressBean {
            /**
             * id : 4
             * uid : 1
             * tel : 8555
             * isindex : 1
             * sheng : 沈阳市
             * shi : 沈阳市
             * qu : 辽宁省
             * address : 输了
             * youbian : 123
             * name : 后天
             */

            private String id;
            private String uid;
            private String tel;
            private String isindex;
            private String sheng;
            private String shi;
            private String qu;
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
        }

        public static class ListBean {
            /**
             * title : 北京朝阳农场1
             * id : 7
             * nc_id : 1
             * details : [{"id":"10","fid":"7","cp_id":"461","gg_id":"9878","pic":"/data/img/1811/5bfb9a1f70711.png","gg_title":"70-80公斤","pz_title":"黄心猕猴桃","title":"荔枝果树","num":"3","price":"421.00","maintain":["有机肥","要颜值","常规三分之一","需要","需要","果脯",""],"lanmu":"1","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","insurance":"0.00","welfare":"0.30"}]
             * total_price : 1263
             * jian : {"id":"1","uid":"1","j_id":"1","zt":"1","man":"500","jian":"5","ctime":"1550937600","etime":"1572019200","nc_id":null,"title":"首页全场优惠卷","attr":"1"}
             */

            private String title;
            private String id;
            private String nc_id;
            private int total_price;
            private JianBean jian;
            private List<DetailsBean> details;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNc_id() {
                return nc_id;
            }

            public void setNc_id(String nc_id) {
                this.nc_id = nc_id;
            }

            public int getTotal_price() {
                return total_price;
            }

            public void setTotal_price(int total_price) {
                this.total_price = total_price;
            }

            public JianBean getJian() {
                return jian;
            }

            public void setJian(JianBean jian) {
                this.jian = jian;
            }

            public List<DetailsBean> getDetails() {
                return details;
            }

            public void setDetails(List<DetailsBean> details) {
                this.details = details;
            }

            public static class JianBean {
                /**
                 * id : 1
                 * uid : 1
                 * j_id : 1
                 * zt : 1
                 * man : 500
                 * jian : 5
                 * ctime : 1550937600
                 * etime : 1572019200
                 * nc_id : null
                 * title : 首页全场优惠卷
                 * attr : 1
                 */

                private String id;
                private String uid;
                private String j_id;
                private String zt;
                private String man;
                private String jian;
                private String ctime;
                private String etime;
                private String nc_id;
                private String title;
                private String attr;

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

                public String getJ_id() {
                    return j_id;
                }

                public void setJ_id(String j_id) {
                    this.j_id = j_id;
                }

                public String getZt() {
                    return zt;
                }

                public void setZt(String zt) {
                    this.zt = zt;
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

                public String getCtime() {
                    return ctime;
                }

                public void setCtime(String ctime) {
                    this.ctime = ctime;
                }

                public String getEtime() {
                    return etime;
                }

                public void setEtime(String etime) {
                    this.etime = etime;
                }

                public String getNc_id() {
                    return nc_id;
                }

                public void setNc_id(String nc_id) {
                    this.nc_id = nc_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getAttr() {
                    return attr;
                }

                public void setAttr(String attr) {
                    this.attr = attr;
                }
            }

            public static class DetailsBean {
                /**
                 * id : 10
                 * fid : 7
                 * cp_id : 461
                 * gg_id : 9878
                 * pic : /data/img/1811/5bfb9a1f70711.png
                 * gg_title : 70-80公斤
                 * pz_title : 黄心猕猴桃
                 * title : 荔枝果树
                 * num : 3
                 * price : 421.00
                 * maintain : ["有机肥","要颜值","常规三分之一","需要","需要","果脯",""]
                 * lanmu : 1
                 * ms_etime : null
                 * spike_price : null
                 * postage : 0.00
                 * js_lx : 2
                 * insurance : 0.00
                 * welfare : 0.30
                 */

                private String id;
                private String fid;
                private String cp_id;
                private String gg_id;
                private String pic;
                private String gg_title;
                private String pz_title;
                private String title;
                private String num;
                private String price;
                private String lanmu;
                private Long ms_etime;
                private String spike_price;
                private String postage;
                private String js_lx;
                private String insurance;
                private String welfare;
                private List<String> maintain;

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

                public Long getMs_etime() {
                    return ms_etime;
                }

                public void setMs_etime(Long ms_etime) {
                    this.ms_etime = ms_etime;
                }

                public String getSpike_price() {
                    return spike_price;
                }

                public void setSpike_price(String spike_price) {
                    this.spike_price = spike_price;
                }

                public String getPostage() {
                    return postage;
                }

                public void setPostage(String postage) {
                    this.postage = postage;
                }

                public String getJs_lx() {
                    return js_lx;
                }

                public void setJs_lx(String js_lx) {
                    this.js_lx = js_lx;
                }

                public String getInsurance() {
                    return insurance;
                }

                public void setInsurance(String insurance) {
                    this.insurance = insurance;
                }

                public String getWelfare() {
                    return welfare;
                }

                public void setWelfare(String welfare) {
                    this.welfare = welfare;
                }

                public List<String> getMaintain() {
                    return maintain;
                }

                public void setMaintain(List<String> maintain) {
                    this.maintain = maintain;
                }
            }
        }
    }
}
