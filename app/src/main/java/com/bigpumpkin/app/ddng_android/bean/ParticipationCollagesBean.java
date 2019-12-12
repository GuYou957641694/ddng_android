package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class ParticipationCollagesBean {


    /**
     * msg : success
     * code : 200
     * data : {"id":"8","shop":{"id":"6","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","ctime":"12-00,01-00","fruit_tree_variety":{"id":"4","title":"菠萝"},"fruit_tree_varietylist":{"id":"4","fid":"4","title":"60-70公斤","price":"3.00"}},"price":"1.00","number":2,"time":"1572239797","pic":[{"id":"87","name":"小奶狗","pic":"https://tfs.alipayobjects.com/images/partner/TB1rjhpX.9KDuNjmf7TXXXxrFXa"}],"title":"打个电话"}
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
         * id : 8
         * shop : {"id":"6","pic":"/data/img/1910/5da92e25775bb.jpg","title":"荔枝果树","ctime":"12-00,01-00","fruit_tree_variety":{"id":"4","title":"菠萝"},"fruit_tree_varietylist":{"id":"4","fid":"4","title":"60-70公斤","price":"3.00"}}
         * price : 1.00
         * number : 2
         * time : 1572239797
         * pic : [{"id":"87","name":"小奶狗","pic":"https://tfs.alipayobjects.com/images/partner/TB1rjhpX.9KDuNjmf7TXXXxrFXa"}]
         * title : 打个电话
         */

        private String id;
        private ShopBean shop;
        private String price;
        private String number;
        private long time;
        private String title;
        private List<PicBean> pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<PicBean> getPic() {
            return pic;
        }

        public void setPic(List<PicBean> pic) {
            this.pic = pic;
        }

        public static class ShopBean {
            /**
             * id : 6
             * pic : /data/img/1910/5da92e25775bb.jpg
             * title : 荔枝果树
             * ctime : 12-00,01-00
             * fruit_tree_variety : {"id":"4","title":"菠萝"}
             * fruit_tree_varietylist : {"id":"4","fid":"4","title":"60-70公斤","price":"3.00"}
             */

            private String id;
            private String pic;
            private String title;
            private String ctime;
            private FruitTreeVarietyBean fruit_tree_variety;
            private FruitTreeVarietylistBean fruit_tree_varietylist;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public FruitTreeVarietyBean getFruit_tree_variety() {
                return fruit_tree_variety;
            }

            public void setFruit_tree_variety(FruitTreeVarietyBean fruit_tree_variety) {
                this.fruit_tree_variety = fruit_tree_variety;
            }

            public FruitTreeVarietylistBean getFruit_tree_varietylist() {
                return fruit_tree_varietylist;
            }

            public void setFruit_tree_varietylist(FruitTreeVarietylistBean fruit_tree_varietylist) {
                this.fruit_tree_varietylist = fruit_tree_varietylist;
            }

            public static class FruitTreeVarietyBean {
                /**
                 * id : 4
                 * title : 菠萝
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

            public static class FruitTreeVarietylistBean {
                /**
                 * id : 4
                 * fid : 4
                 * title : 60-70公斤
                 * price : 3.00
                 */

                private String id;
                private String fid;
                private String title;
                private String price;

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

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }

        public static class PicBean {
            /**
             * id : 87
             * name : 小奶狗
             * pic : https://tfs.alipayobjects.com/images/partner/TB1rjhpX.9KDuNjmf7TXXXxrFXa
             */

            private String id;
            private String name;
            private String pic;

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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
