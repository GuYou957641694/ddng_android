package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Settlement_Bean {


    /**
     * msg : 成功
     * code : 200
     * data : {"address":{"id":"21","uid":"46","tel":"12345678911","isindex":"1","sheng":"太原市","shi":"太原市","qu":"山西省","address":"现代教育","youbian":"123","name":"贾东升"},"list":[{"title":"北京海淀农场2","id":"31","nc_id":"2","details":[{"id":"76","fid":"31","cp_id":"23","gg_id":"4731","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"20-30公斤","pz_title":"白里叶莓","title":"生产者说23","num":"4","price":"168.00","maintain":null,"lanmu":"4","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","welfare":"0.40"}],"total_price":672,"jian":false},{"title":"北京朝阳农场1","id":"30","nc_id":"1","details":[{"id":"77","fid":"30","cp_id":"13","gg_id":"11","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"20-30公斤","pz_title":"乌饭果","title":"农场深加工13","num":"6","price":"36.00","maintain":null,"lanmu":"5","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","welfare":"0.60"}],"total_price":216,"jian":false}],"shopping_all_price":"889.00"}
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
         * address : {"id":"21","uid":"46","tel":"12345678911","isindex":"1","sheng":"太原市","shi":"太原市","qu":"山西省","address":"现代教育","youbian":"123","name":"贾东升"}
         * list : [{"title":"北京海淀农场2","id":"31","nc_id":"2","details":[{"id":"76","fid":"31","cp_id":"23","gg_id":"4731","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"20-30公斤","pz_title":"白里叶莓","title":"生产者说23","num":"4","price":"168.00","maintain":null,"lanmu":"4","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","welfare":"0.40"}],"total_price":672,"jian":false},{"title":"北京朝阳农场1","id":"30","nc_id":"1","details":[{"id":"77","fid":"30","cp_id":"13","gg_id":"11","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"20-30公斤","pz_title":"乌饭果","title":"农场深加工13","num":"6","price":"36.00","maintain":null,"lanmu":"5","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","welfare":"0.60"}],"total_price":216,"jian":false}]
         * shopping_all_price : 889.00
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
             * id : 21
             * uid : 46
             * tel : 12345678911
             * isindex : 1
             * sheng : 太原市
             * shi : 太原市
             * qu : 山西省
             * address : 现代教育
             * youbian : 123
             * name : 贾东升
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
             * title : 北京海淀农场2
             * id : 31
             * nc_id : 2
             * details : [{"id":"76","fid":"31","cp_id":"23","gg_id":"4731","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"20-30公斤","pz_title":"白里叶莓","title":"生产者说23","num":"4","price":"168.00","maintain":null,"lanmu":"4","ms_etime":null,"spike_price":null,"postage":"0.00","js_lx":"2","welfare":"0.40"}]
             * total_price : 672
             * jian : false
             */

            private String title;
            private String id;
            private String nc_id;
            private String total_price;
            private String jian;
            private List<DetailsBean> details;

            public String getJian() {
                return jian;
            }

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

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String isJian() {
                return jian;
            }

            public void setJian(String jian) {
                this.jian = jian;
            }

            public List<DetailsBean> getDetails() {
                return details;
            }

            public void setDetails(List<DetailsBean> details) {
                this.details = details;
            }

            public static class DetailsBean {
                /**
                 * id : 76
                 * fid : 31
                 * cp_id : 23
                 * gg_id : 4731
                 * pic : /data/img/1901/5c3414897ed4f.png
                 * gg_title : 20-30公斤
                 * pz_title : 白里叶莓
                 * title : 生产者说23
                 * num : 4
                 * price : 168.00
                 * maintain : null
                 * lanmu : 4
                 * ms_etime : null
                 * spike_price : null
                 * postage : 0.00
                 * js_lx : 2
                 * welfare : 0.40
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
                private Object maintain;
                private String lanmu;
                private Object ms_etime;
                private Object spike_price;
                private String postage;
                private String js_lx;
                private String welfare;

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

                public Object getMaintain() {
                    return maintain;
                }

                public void setMaintain(Object maintain) {
                    this.maintain = maintain;
                }

                public String getLanmu() {
                    return lanmu;
                }

                public void setLanmu(String lanmu) {
                    this.lanmu = lanmu;
                }

                public Object getMs_etime() {
                    return ms_etime;
                }

                public void setMs_etime(Object ms_etime) {
                    this.ms_etime = ms_etime;
                }

                public Object getSpike_price() {
                    return spike_price;
                }

                public void setSpike_price(Object spike_price) {
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

                public String getWelfare() {
                    return welfare;
                }

                public void setWelfare(String welfare) {
                    this.welfare = welfare;
                }
            }
        }
    }
}
