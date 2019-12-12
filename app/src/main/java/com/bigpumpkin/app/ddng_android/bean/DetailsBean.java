package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class DetailsBean {


    /**
     * msg : success
     * code : 200
     * data : {"collection":2,"title":"荔枝果树","mp4":"/data/img/1812/5c07373664007.mp4","pic":"/data/img/1910/5da92e25775bb.jpg","category":"苹果（红富士,红星,国光,秦冠,黄元帅）","chandi":"北京","price":"1.00","refund":{"title":"7天无理由退货退款","id":"2"},"wheel":[{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_12180.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_87549.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_10109.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_89631.png"}],"variety":[{"id":"4","title":"菠萝","voo":[{"id":"4","fid":"4","fidname":"菠萝","title":"60-70公斤","price":"132.00","in_stock":"397","v_price":"152.00"},{"id":"3772","fid":"4","fidname":"菠萝","title":"80-90公斤","price":"211.00","in_stock":"428","v_price":"231.00"},{"id":"7540","fid":"4","fidname":"菠萝","title":"60-70公斤","price":"286.00","in_stock":"328","v_price":"306.00"}]},{"id":"946","title":"香梨","voo":[{"id":"946","fid":"946","fidname":"香梨","title":"60-70公斤","price":"11.00","in_stock":"140","v_price":"31.00"},{"id":"4714","fid":"946","fidname":"香梨","title":"40-50公斤","price":"90.00","in_stock":"23","v_price":"110.00"},{"id":"8482","fid":"946","fidname":"香梨","title":"20-30公斤","price":"203.00","in_stock":"341","v_price":"223.00"}]},{"id":"1888","title":"野樱莓","voo":[{"id":"1888","fid":"1888","fidname":"野樱莓","title":"20-30公斤","price":"361.00","in_stock":"72","v_price":"381.00"},{"id":"5656","fid":"1888","fidname":"野樱莓","title":"40-50公斤","price":"16.00","in_stock":"418","v_price":"36.00"},{"id":"9424","fid":"1888","fidname":"野樱莓","title":"70-80公斤","price":"276.00","in_stock":"267","v_price":"296.00"}]},{"id":"2830","title":"南洋红香蕉","voo":[{"id":"2830","fid":"2830","fidname":"南洋红香蕉","title":"90-100公斤","price":"218.00","in_stock":"368","v_price":"238.00"},{"id":"6598","fid":"2830","fidname":"南洋红香蕉","title":"80-90公斤","price":"111.00","in_stock":"441","v_price":"131.00"},{"id":"10366","fid":"2830","fidname":"南洋红香蕉","title":"40-50公斤","price":"497.00","in_stock":"233","v_price":"517.00"},{"id":"11321","fid":"2830","fidname":"南洋红香蕉","title":"35","price":"2.00","in_stock":"10","v_price":"200.00"}]}],"coupon":[{"man":"100","jian":"2","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠","id":"9","get":2},{"man":"100","jian":"3","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠","id":"10","get":2},{"man":"200","jian":"10","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠","id":"5","get":2},{"man":"200","jian":"5","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"5元优惠","id":"6","get":2},{"man":"200","jian":"3","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠","id":"7","get":2},{"man":"300","jian":"15","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠","id":"4","get":2},{"man":"1000","jian":"50","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠","id":"8","get":2}],"single":2,"farm_details":{"title":"北京朝阳农场1","id":"1","uid":"1","pic":"https://tfs.alipayobjects.com/images/partner/TB1G4jqbX5LDuNjme7TXXatkpXa"},"recommend":[{"id":"9","title":"越南富士9","price":"1.00","pic":"/data/img/1811/5bfb9a1f70711.png"},{"id":"5","title":"北京富士果树5","price":"1.00","pic":"/data/img/1901/5c3414897ed4f.png"},{"id":"6","title":"荔枝果树","price":"1.00","pic":"/data/img/1910/5da92e25775bb.jpg"}],"farm_grade":4,"evaluation":{"num":0,"label":[{"title":"No evaluation","id":"0","num":0}]},"Service":"shidu/Html/Service/Service_static2.html","Details":"Fruitmall/Commodity_list_h5s.html?id=6","type":3}
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
         * collection : 2
         * title : 荔枝果树
         * mp4 : /data/img/1812/5c07373664007.mp4
         * pic : /data/img/1910/5da92e25775bb.jpg
         * category : 苹果（红富士,红星,国光,秦冠,黄元帅）
         * chandi : 北京
         * price : 1.00
         * refund : {"title":"7天无理由退货退款","id":"2"}
         * wheel : [{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_12180.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_87549.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_10109.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_89631.png"}]
         * variety : [{"id":"4","title":"菠萝","voo":[{"id":"4","fid":"4","fidname":"菠萝","title":"60-70公斤","price":"132.00","in_stock":"397","v_price":"152.00"},{"id":"3772","fid":"4","fidname":"菠萝","title":"80-90公斤","price":"211.00","in_stock":"428","v_price":"231.00"},{"id":"7540","fid":"4","fidname":"菠萝","title":"60-70公斤","price":"286.00","in_stock":"328","v_price":"306.00"}]},{"id":"946","title":"香梨","voo":[{"id":"946","fid":"946","fidname":"香梨","title":"60-70公斤","price":"11.00","in_stock":"140","v_price":"31.00"},{"id":"4714","fid":"946","fidname":"香梨","title":"40-50公斤","price":"90.00","in_stock":"23","v_price":"110.00"},{"id":"8482","fid":"946","fidname":"香梨","title":"20-30公斤","price":"203.00","in_stock":"341","v_price":"223.00"}]},{"id":"1888","title":"野樱莓","voo":[{"id":"1888","fid":"1888","fidname":"野樱莓","title":"20-30公斤","price":"361.00","in_stock":"72","v_price":"381.00"},{"id":"5656","fid":"1888","fidname":"野樱莓","title":"40-50公斤","price":"16.00","in_stock":"418","v_price":"36.00"},{"id":"9424","fid":"1888","fidname":"野樱莓","title":"70-80公斤","price":"276.00","in_stock":"267","v_price":"296.00"}]},{"id":"2830","title":"南洋红香蕉","voo":[{"id":"2830","fid":"2830","fidname":"南洋红香蕉","title":"90-100公斤","price":"218.00","in_stock":"368","v_price":"238.00"},{"id":"6598","fid":"2830","fidname":"南洋红香蕉","title":"80-90公斤","price":"111.00","in_stock":"441","v_price":"131.00"},{"id":"10366","fid":"2830","fidname":"南洋红香蕉","title":"40-50公斤","price":"497.00","in_stock":"233","v_price":"517.00"},{"id":"11321","fid":"2830","fidname":"南洋红香蕉","title":"35","price":"2.00","in_stock":"10","v_price":"200.00"}]}]
         * coupon : [{"man":"100","jian":"2","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠","id":"9","get":2},{"man":"100","jian":"3","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠","id":"10","get":2},{"man":"200","jian":"10","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠","id":"5","get":2},{"man":"200","jian":"5","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"5元优惠","id":"6","get":2},{"man":"200","jian":"3","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠","id":"7","get":2},{"man":"300","jian":"15","ctime":"1548259200","etime":"1602345600","nc_id":"1","title":"惠","id":"4","get":2},{"man":"1000","jian":"50","ctime":"1548864000","etime":"1602345600","nc_id":"1","title":"惠","id":"8","get":2}]
         * single : 2
         * farm_details : {"title":"北京朝阳农场1","id":"1","uid":"1","pic":"https://tfs.alipayobjects.com/images/partner/TB1G4jqbX5LDuNjme7TXXatkpXa"}
         * recommend : [{"id":"9","title":"越南富士9","price":"1.00","pic":"/data/img/1811/5bfb9a1f70711.png"},{"id":"5","title":"北京富士果树5","price":"1.00","pic":"/data/img/1901/5c3414897ed4f.png"},{"id":"6","title":"荔枝果树","price":"1.00","pic":"/data/img/1910/5da92e25775bb.jpg"}]
         * farm_grade : 4
         * evaluation : {"num":0,"label":[{"title":"No evaluation","id":"0","num":0}]}
         * Service : shidu/Html/Service/Service_static2.html
         * Details : Fruitmall/Commodity_list_h5s.html?id=6
         * type : 3
         */

        private String collection;
        private String title;
        private String mp4;
        private String pic;
        private String category;
        private String chandi;
        private String price;
        private RefundBean refund;
        private FarmDetailsBean farm_details;
        private int farm_grade;
        private EvaluationBean evaluation;
        private String Service;
        private String Details;
        private int type;
        private List<WheelBean> wheel;
        private List<VarietyBean> variety;
        private List<CouponBean> coupon;
        private List<RecommendBean> recommend;
        private List<SingleBean> single;
        private List<OptionsBean> options;

        public List<OptionsBean> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsBean> options) {
            this.options = options;
        }
        public List<SingleBean> getSingle() {
            return single;
        }

        public void setSingle(List<SingleBean> single) {
            this.single = single;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMp4() {
            return mp4;
        }

        public void setMp4(String mp4) {
            this.mp4 = mp4;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getChandi() {
            return chandi;
        }

        public void setChandi(String chandi) {
            this.chandi = chandi;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public RefundBean getRefund() {
            return refund;
        }

        public void setRefund(RefundBean refund) {
            this.refund = refund;
        }

        public static class OptionsBean {
            /**
             * id : 1
             * title : 种植维护
             * customlist : [{"id":"20","title":"常规维护"},{"id":"21","title":"定制维护"}]
             */

            private String id;
            private String title;
            private List<CustomlistBean> customlist;
            private boolean type = false;

            public boolean isType() {
                return type;
            }

            public void setType(boolean type) {
                this.type = type;
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

            public List<CustomlistBean> getCustomlist() {
                return customlist;
            }

            public void setCustomlist(List<CustomlistBean> customlist) {
                this.customlist = customlist;
            }

            public static class CustomlistBean {
                /**
                 * id : 20
                 * title : 常规维护
                 */
                private String id;
                private String title;
                private boolean isSelected = false;

                public boolean isSelected() {
                    return isSelected;
                }

                public void setSelected(boolean selected) {
                    isSelected = selected;
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
            }
        }

        public FarmDetailsBean getFarm_details() {
            return farm_details;
        }

        public void setFarm_details(FarmDetailsBean farm_details) {
            this.farm_details = farm_details;
        }

        public int getFarm_grade() {
            return farm_grade;
        }

        public void setFarm_grade(int farm_grade) {
            this.farm_grade = farm_grade;
        }

        public EvaluationBean getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(EvaluationBean evaluation) {
            this.evaluation = evaluation;
        }

        public String getService() {
            return Service;
        }

        public void setService(String Service) {
            this.Service = Service;
        }

        public String getDetails() {
            return Details;
        }

        public void setDetails(String Details) {
            this.Details = Details;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<WheelBean> getWheel() {
            return wheel;
        }

        public void setWheel(List<WheelBean> wheel) {
            this.wheel = wheel;
        }

        public List<VarietyBean> getVariety() {
            return variety;
        }

        public void setVariety(List<VarietyBean> variety) {
            this.variety = variety;
        }

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public static class SingleBean {
            /**
             * id : 6
             * name : 农场主2
             * title : 哈哈哈
             * num : 2
             * etime : 1572078080
             */

            private String id;
            private String name;
            private String title;
            private String num;
            private String s_num;
            private long etime;
            private String pic;

            public String getS_num() {
                return s_num;
            }

            public void setS_num(String s_num) {
                this.s_num = s_num;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public long getEtime() {
                return etime;
            }

            public void setEtime(long etime) {
                this.etime = etime;
            }
        }

        public static class RefundBean {
            /**
             * title : 7天无理由退货退款
             * id : 2
             */

            private String title;
            private String id;

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
        }

        public static class FarmDetailsBean {
            /**
             * title : 北京朝阳农场1
             * id : 1
             * uid : 1
             * pic : https://tfs.alipayobjects.com/images/partner/TB1G4jqbX5LDuNjme7TXXatkpXa
             */

            private String title;
            private String id;
            private String uid;
            private String pic;

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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class EvaluationBean {
            /**
             * num : 0
             * label : [{"title":"No evaluation","id":"0","num":0}]
             */

            private int num;
            private List<LabelBean> label;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public List<LabelBean> getLabel() {
                return label;
            }

            public void setLabel(List<LabelBean> label) {
                this.label = label;
            }

            public static class LabelBean {
                /**
                 * title : No evaluation
                 * id : 0
                 * num : 0
                 */

                private String title;
                private String id;
                private int num;

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

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }
            }
        }

        public static class WheelBean {
            /**
             * pic : Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_12180.png
             */

            private String pic;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class VarietyBean {
            /**
             * id : 4
             * title : 菠萝
             * voo : [{"id":"4","fid":"4","fidname":"菠萝","title":"60-70公斤","price":"132.00","in_stock":"397","v_price":"152.00"},{"id":"3772","fid":"4","fidname":"菠萝","title":"80-90公斤","price":"211.00","in_stock":"428","v_price":"231.00"},{"id":"7540","fid":"4","fidname":"菠萝","title":"60-70公斤","price":"286.00","in_stock":"328","v_price":"306.00"}]
             */

            private String id;
            private String title;
            private List<VooBean> voo;

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

            public List<VooBean> getVoo() {
                return voo;
            }

            public void setVoo(List<VooBean> voo) {
                this.voo = voo;
            }

            public static class VooBean {
                /**
                 * id : 4
                 * fid : 4
                 * fidname : 菠萝
                 * title : 60-70公斤
                 * price : 132.00
                 * in_stock : 397
                 * v_price : 152.00
                 */

                private String id;
                private String fid;
                private String fidname;
                private String title;
                private String price;
                private int in_stock;
                private String v_price;

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

                public String getFidname() {
                    return fidname;
                }

                public void setFidname(String fidname) {
                    this.fidname = fidname;
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

                public int getIn_stock() {
                    return in_stock;
                }

                public void setIn_stock(int in_stock) {
                    this.in_stock = in_stock;
                }

                public String getV_price() {
                    return v_price;
                }

                public void setV_price(String v_price) {
                    this.v_price = v_price;
                }
            }
        }

        public static class CouponBean {
            /**
             * man : 100
             * jian : 2
             * ctime : 1548259200
             * etime : 1602345600
             * nc_id : 1
             * title : 惠
             * id : 9
             * get : 2
             */

            private String man;
            private String jian;
            private long ctime;
            private long etime;
            private String nc_id;
            private String title;
            private String id;
            private int get;

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

            public long getCtime() {
                return ctime;
            }

            public void setCtime(long ctime) {
                this.ctime = ctime;
            }

            public long getEtime() {
                return etime;
            }

            public void setEtime(long etime) {
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getGet() {
                return get;
            }

            public void setGet(int get) {
                this.get = get;
            }
        }

        public static class RecommendBean {
            /**
             * id : 9
             * title : 越南富士9
             * price : 1.00
             * pic : /data/img/1811/5bfb9a1f70711.png
             */

            private String id;
            private String title;
            private String price;
            private String pic;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
