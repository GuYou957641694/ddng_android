package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Buy_NowBean {


    /**
     * msg : 成功
     * code : 200
     * data : {"address":{"id":"20","uid":"46","tel":"11111111111","isindex":"1","sheng":"南京市","shi":"南京市","qu":"江苏省","address":"xx","youbian":"123","name":"gg"},"list":[{"id":"1","title":"北京朝阳农场1","details":[{"cp_id":"4","postage":"0","pic":"/data/img/1811/5bfb9a1f70711.png","cp_title":"香蕉果树","cp_fl":"1","gg_id":"2","gg_in_stock":"60","gg_title":"10-20公斤","pz_id":"2","pz_title":"西梅","ms_stime":null,"ms_etime":null,"spike_price":null,"price":"0.01","num":"2","js_lx":"2","jian":"0.00","adoption":{"title":"认养协议"},"treasure":{"val":"2","title":"准收宝","insurance":4.0E-4},"welfare":{"val":"0.1","title":"植物放生公益","price":"0.00"},"all_price":0.0204}]}]}
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
         * address : {"id":"20","uid":"46","tel":"11111111111","isindex":"1","sheng":"南京市","shi":"南京市","qu":"江苏省","address":"xx","youbian":"123","name":"gg"}
         * list : [{"id":"1","title":"北京朝阳农场1","details":[{"cp_id":"4","postage":"0","pic":"/data/img/1811/5bfb9a1f70711.png","cp_title":"香蕉果树","cp_fl":"1","gg_id":"2","gg_in_stock":"60","gg_title":"10-20公斤","pz_id":"2","pz_title":"西梅","ms_stime":null,"ms_etime":null,"spike_price":null,"price":"0.01","num":"2","js_lx":"2","jian":"0.00","adoption":{"title":"认养协议"},"treasure":{"val":"2","title":"准收宝","insurance":4.0E-4},"welfare":{"val":"0.1","title":"植物放生公益","price":"0.00"},"all_price":0.0204}]}]
         */

        private AddressBean address;
        private List<ListBean> list;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AddressBean {
            /**
             * id : 20
             * uid : 46
             * tel : 11111111111
             * isindex : 1
             * sheng : 南京市
             * shi : 南京市
             * qu : 江苏省
             * address : xx
             * youbian : 123
             * name : gg
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
             * id : 1
             * title : 北京朝阳农场1
             * details : [{"cp_id":"4","postage":"0","pic":"/data/img/1811/5bfb9a1f70711.png","cp_title":"香蕉果树","cp_fl":"1","gg_id":"2","gg_in_stock":"60","gg_title":"10-20公斤","pz_id":"2","pz_title":"西梅","ms_stime":null,"ms_etime":null,"spike_price":null,"price":"0.01","num":"2","js_lx":"2","jian":"0.00","adoption":{"title":"认养协议"},"treasure":{"val":"2","title":"准收宝","insurance":4.0E-4},"welfare":{"val":"0.1","title":"植物放生公益","price":"0.00"},"all_price":0.0204}]
             */

            private String id;
            private String title;
            private List<DetailsBean> details;

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

            public List<DetailsBean> getDetails() {
                return details;
            }

            public void setDetails(List<DetailsBean> details) {
                this.details = details;
            }

            public static class DetailsBean {
                /**
                 * cp_id : 4
                 * postage : 0
                 * pic : /data/img/1811/5bfb9a1f70711.png
                 * cp_title : 香蕉果树
                 * cp_fl : 1
                 * gg_id : 2
                 * gg_in_stock : 60
                 * gg_title : 10-20公斤
                 * pz_id : 2
                 * pz_title : 西梅
                 * ms_stime : null
                 * ms_etime : null
                 * spike_price : null
                 * price : 0.01
                 * num : 2
                 * js_lx : 2
                 * jian : 0.00
                 * adoption : {"title":"认养协议"}
                 * treasure : {"val":"2","title":"准收宝","insurance":4.0E-4}
                 * welfare : {"val":"0.1","title":"植物放生公益","price":"0.00"}
                 * all_price : 0.0204
                 */

                private String cp_id;
                private String postage;
                private String pic;
                private String cp_title;
                private String cp_fl;
                private String gg_id;
                private String gg_in_stock;
                private String gg_title;
                private String pz_id;
                private String pz_title;
                private Object ms_stime;
                private Object ms_etime;
                private Object spike_price;
                private String price;
                private String num;
                private String js_lx;
                private String jian;
                private String maintain;
                private String self_mail;
                private String lanmu;
                private AdoptionBean adoption;
                private TreasureBean treasure;
                private WelfareBean welfare;
                private double all_price;

                public String getLanmu() {
                    return lanmu;
                }

                public void setLanmu(String lanmu) {
                    this.lanmu = lanmu;
                }

                public String getSelf_mail() {
                    return self_mail;
                }

                public void setSelf_mail(String self_mail) {
                    this.self_mail = self_mail;
                }

                public String getMaintain() {
                    return maintain;
                }

                public void setMaintain(String maintain) {
                    this.maintain = maintain;
                }

                public String getCp_id() {
                    return cp_id;
                }

                public void setCp_id(String cp_id) {
                    this.cp_id = cp_id;
                }

                public String getPostage() {
                    return postage;
                }

                public void setPostage(String postage) {
                    this.postage = postage;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getCp_title() {
                    return cp_title;
                }

                public void setCp_title(String cp_title) {
                    this.cp_title = cp_title;
                }

                public String getCp_fl() {
                    return cp_fl;
                }

                public void setCp_fl(String cp_fl) {
                    this.cp_fl = cp_fl;
                }

                public String getGg_id() {
                    return gg_id;
                }

                public void setGg_id(String gg_id) {
                    this.gg_id = gg_id;
                }

                public String getGg_in_stock() {
                    return gg_in_stock;
                }

                public void setGg_in_stock(String gg_in_stock) {
                    this.gg_in_stock = gg_in_stock;
                }

                public String getGg_title() {
                    return gg_title;
                }

                public void setGg_title(String gg_title) {
                    this.gg_title = gg_title;
                }

                public String getPz_id() {
                    return pz_id;
                }

                public void setPz_id(String pz_id) {
                    this.pz_id = pz_id;
                }

                public String getPz_title() {
                    return pz_title;
                }

                public void setPz_title(String pz_title) {
                    this.pz_title = pz_title;
                }

                public Object getMs_stime() {
                    return ms_stime;
                }

                public void setMs_stime(Object ms_stime) {
                    this.ms_stime = ms_stime;
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

                public String getJs_lx() {
                    return js_lx;
                }

                public void setJs_lx(String js_lx) {
                    this.js_lx = js_lx;
                }

                public String getJian() {
                    return jian;
                }

                public void setJian(String jian) {
                    this.jian = jian;
                }

                public AdoptionBean getAdoption() {
                    return adoption;
                }

                public void setAdoption(AdoptionBean adoption) {
                    this.adoption = adoption;
                }

                public TreasureBean getTreasure() {
                    return treasure;
                }

                public void setTreasure(TreasureBean treasure) {
                    this.treasure = treasure;
                }

                public WelfareBean getWelfare() {
                    return welfare;
                }

                public void setWelfare(WelfareBean welfare) {
                    this.welfare = welfare;
                }

                public double getAll_price() {
                    return all_price;
                }

                public void setAll_price(double all_price) {
                    this.all_price = all_price;
                }

                public static class AdoptionBean {
                    /**
                     * title : 认养协议
                     */

                    private String title;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }

                public static class TreasureBean {
                    /**
                     * val : 2
                     * title : 准收宝
                     * insurance : 4.0E-4
                     */

                    private String val;
                    private String title;
                    private double insurance;

                    public String getVal() {
                        return val;
                    }

                    public void setVal(String val) {
                        this.val = val;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public double getInsurance() {
                        return insurance;
                    }

                    public void setInsurance(double insurance) {
                        this.insurance = insurance;
                    }
                }

                public static class WelfareBean {
                    /**
                     * val : 0.1
                     * title : 植物放生公益
                     * price : 0.00
                     */

                    private String val;
                    private String title;
                    private String price;

                    public String getVal() {
                        return val;
                    }

                    public void setVal(String val) {
                        this.val = val;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
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
    }
}
