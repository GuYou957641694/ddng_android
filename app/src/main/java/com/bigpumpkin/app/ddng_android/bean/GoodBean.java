package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class GoodBean {

    /**
     * msg : success
     * code : 200
     * data : {"collection":"2","title":"北京富士苹果3","mp4":"/data/img/1812/5c07373664007.mp4","pic":"/data/img/1901/5c341411223c8.png","category":"海棠果","chandi":"北京","wheel":[{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_12180.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_87549.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_10109.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_89631.png"}],"options":[{"id":"1","title":"种植维护","customlist":[{"id":"20","title":"常规维护"},{"id":"21","title":"定制维护"}]}],"price":"31.00","variety":[{"id":"1","title":"香瓜","voo":[{"id":"1","fid":"1","fidname":"香瓜","title":"30-40公斤","price":"160.00","in_stock":"22","v_price":"180.00"},{"id":"3769","fid":"1","fidname":"香瓜","title":"90-100公斤","price":"58.00","in_stock":"491","v_price":"78.00"},{"id":"7537","fid":"1","fidname":"香瓜","title":"30-40公斤","price":"345.00","in_stock":"65","v_price":"365.00"}]},{"id":"943","title":"白兰瓜","voo":[{"id":"943","fid":"943","fidname":"白兰瓜","title":"40-50公斤","price":"31.00","in_stock":"275","v_price":"51.00"},{"id":"4711","fid":"943","fidname":"白兰瓜","title":"40-50公斤","price":"211.00","in_stock":"448","v_price":"231.00"},{"id":"8479","fid":"943","fidname":"白兰瓜","title":"20-30公斤","price":"37.00","in_stock":"380","v_price":"57.00"}]},{"id":"1885","title":"黄心猕猴桃","voo":[{"id":"1885","fid":"1885","fidname":"黄心猕猴桃","title":"30-40公斤","price":"415.00","in_stock":"30","v_price":"435.00"},{"id":"5653","fid":"1885","fidname":"黄心猕猴桃","title":"20-30公斤","price":"166.00","in_stock":"62","v_price":"186.00"},{"id":"9421","fid":"1885","fidname":"黄心猕猴桃","title":"60-70公斤","price":"52.00","in_stock":"216","v_price":"72.00"}]},{"id":"2827","title":"金铃子","voo":[{"id":"2827","fid":"2827","fidname":"金铃子","title":"30-40公斤","price":"211.00","in_stock":"431","v_price":"231.00"},{"id":"6595","fid":"2827","fidname":"金铃子","title":"60-70公斤","price":"399.00","in_stock":"186","v_price":"419.00"},{"id":"10363","fid":"2827","fidname":"金铃子","title":"90-100公斤","price":"295.00","in_stock":"238","v_price":"315.00"}]}],"coupon":[{"man":"100","jian":"2","ctime":"1548259200","etime":"1569859200","nc_id":"2","title":"惠","id":"9","get":2},{"man":"100","jian":"3","ctime":"1548259200","etime":"1569859200","nc_id":"2","title":"惠","id":"10","get":1},{"man":"300","jian":"15","ctime":"1548259200","etime":"1572019200","nc_id":"2","title":"惠","id":"4","get":1}],"farm_details":{"title":"北京海淀农场2","id":"2","uid":"1","pic":"/Public/home/images/touxiang.jpg"},"farm_grade":4,"evaluation":{"num":"6","label":[{"title":"物美价廉","id":"1","num":"0"},{"title":"服务态度好","id":"2","num":"0"},{"title":"品质好","id":"3","num":"0"},{"title":"很划算","id":"4","num":"0"}]},"Service":"/Html/Service/Service_static2.html","Details":"/Fruitmall/Commodity_list_h5s.html?id=3"}
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
         * title : 北京富士苹果3
         * mp4 : /data/img/1812/5c07373664007.mp4
         * pic : /data/img/1901/5c341411223c8.png
         * category : 海棠果
         * chandi : 北京
         * wheel : [{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_12180.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_87549.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_10109.png"},{"pic":"Public/Admin/js/kindeditor/php/../attached/image/20181126/20181126070002_89631.png"}]
         * options : [{"id":"1","title":"种植维护","customlist":[{"id":"20","title":"常规维护"},{"id":"21","title":"定制维护"}]}]
         * price : 31.00
         * variety : [{"id":"1","title":"香瓜","voo":[{"id":"1","fid":"1","fidname":"香瓜","title":"30-40公斤","price":"160.00","in_stock":"22","v_price":"180.00"},{"id":"3769","fid":"1","fidname":"香瓜","title":"90-100公斤","price":"58.00","in_stock":"491","v_price":"78.00"},{"id":"7537","fid":"1","fidname":"香瓜","title":"30-40公斤","price":"345.00","in_stock":"65","v_price":"365.00"}]},{"id":"943","title":"白兰瓜","voo":[{"id":"943","fid":"943","fidname":"白兰瓜","title":"40-50公斤","price":"31.00","in_stock":"275","v_price":"51.00"},{"id":"4711","fid":"943","fidname":"白兰瓜","title":"40-50公斤","price":"211.00","in_stock":"448","v_price":"231.00"},{"id":"8479","fid":"943","fidname":"白兰瓜","title":"20-30公斤","price":"37.00","in_stock":"380","v_price":"57.00"}]},{"id":"1885","title":"黄心猕猴桃","voo":[{"id":"1885","fid":"1885","fidname":"黄心猕猴桃","title":"30-40公斤","price":"415.00","in_stock":"30","v_price":"435.00"},{"id":"5653","fid":"1885","fidname":"黄心猕猴桃","title":"20-30公斤","price":"166.00","in_stock":"62","v_price":"186.00"},{"id":"9421","fid":"1885","fidname":"黄心猕猴桃","title":"60-70公斤","price":"52.00","in_stock":"216","v_price":"72.00"}]},{"id":"2827","title":"金铃子","voo":[{"id":"2827","fid":"2827","fidname":"金铃子","title":"30-40公斤","price":"211.00","in_stock":"431","v_price":"231.00"},{"id":"6595","fid":"2827","fidname":"金铃子","title":"60-70公斤","price":"399.00","in_stock":"186","v_price":"419.00"},{"id":"10363","fid":"2827","fidname":"金铃子","title":"90-100公斤","price":"295.00","in_stock":"238","v_price":"315.00"}]}]
         * coupon : [{"man":"100","jian":"2","ctime":"1548259200","etime":"1569859200","nc_id":"2","title":"惠","id":"9","get":2},{"man":"100","jian":"3","ctime":"1548259200","etime":"1569859200","nc_id":"2","title":"惠","id":"10","get":1},{"man":"300","jian":"15","ctime":"1548259200","etime":"1572019200","nc_id":"2","title":"惠","id":"4","get":1}]
         * farm_details : {"title":"北京海淀农场2","id":"2","uid":"1","pic":"/Public/home/images/touxiang.jpg"}
         * farm_grade : 4
         * evaluation : {"num":"6","label":[{"title":"物美价廉","id":"1","num":"0"},{"title":"服务态度好","id":"2","num":"0"},{"title":"品质好","id":"3","num":"0"},{"title":"很划算","id":"4","num":"0"}]}
         * Service : /Html/Service/Service_static2.html
         * Details : /Fruitmall/Commodity_list_h5s.html?id=3
         */

        private String collection;
        private String title;
        private String mp4;
        private String pic;
        private String category;
        private String chandi;
        private String price;
        private FarmDetailsBean farm_details;
        private int farm_grade;
        private EvaluationBean evaluation;
        private String Service;
        private String Details;
        private List<WheelBean> wheel;
        private List<OptionsBean> options;
        private List<VarietyBean> variety;
        private List<CouponBean> coupon;

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

        public List<WheelBean> getWheel() {
            return wheel;
        }

        public void setWheel(List<WheelBean> wheel) {
            this.wheel = wheel;
        }

        public List<OptionsBean> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsBean> options) {
            this.options = options;
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

        public static class FarmDetailsBean {
            /**
             * title : 北京海淀农场2
             * id : 2
             * uid : 1
             * pic : /Public/home/images/touxiang.jpg
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
             * num : 6
             * label : [{"title":"物美价廉","id":"1","num":"0"},{"title":"服务态度好","id":"2","num":"0"},{"title":"品质好","id":"3","num":"0"},{"title":"很划算","id":"4","num":"0"}]
             */

            private String num;
            private List<LabelBean> label;

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
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
                 * title : 物美价廉
                 * id : 1
                 * num : 0
                 */

                private String title;
                private String id;
                private String num;

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

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
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

        public static class OptionsBean {
            /**
             * id : 1
             * title : 种植维护
             * customlist : [{"id":"20","title":"常规维护"},{"id":"21","title":"定制维护"}]
             */

            private String id;
            private String title;
            private List<CustomlistBean> customlist;

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

        public static class VarietyBean {
            /**
             * id : 1
             * title : 香瓜
             * voo : [{"id":"1","fid":"1","fidname":"香瓜","title":"30-40公斤","price":"160.00","in_stock":"22","v_price":"180.00"},{"id":"3769","fid":"1","fidname":"香瓜","title":"90-100公斤","price":"58.00","in_stock":"491","v_price":"78.00"},{"id":"7537","fid":"1","fidname":"香瓜","title":"30-40公斤","price":"345.00","in_stock":"65","v_price":"365.00"}]
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
                 * id : 1
                 * fid : 1
                 * fidname : 香瓜
                 * title : 30-40公斤
                 * price : 160.00
                 * in_stock : 22
                 * v_price : 180.00
                 */

                private String id;
                private String fid;
                private String fidname;
                private String title;
                private String price;
                private String in_stock;
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

                public String getIn_stock() {
                    return in_stock;
                }

                public void setIn_stock(String in_stock) {
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
             * etime : 1569859200
             * nc_id : 2
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
    }
}
