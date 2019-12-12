package com.bigpumpkin.app.ddng_android.bean;

public class ParticipateDetailsBean {


    /**
     * msg : success
     * code : 200
     * data : {"address":{"id":"18","tel":"15506410555","sheng":"山东省","shi":"菏泽市","qu":"县城","address":"地址","youbian":"0","name":"测试"},"shop":{"id":"8","cp_id":"6","num":"3","nc_id":"1","nc_title":"北京朝阳农场1","img_pic":"/data/img/1910/5da92e25775bb.jpg","cp_title":"荔枝果树","gg_id":"4","welfare":"0.00","price":"1.00"},"fruit_tree_variety":{"id":"4","title":"菠萝"},"fruit_tree_varietylist":{"id":"4","title":"60-70公斤","fid":"4","price":"3.00"},"docume":{"id":"2","title":"7天无理由退货退款"},"price":1.02,"lnsurance":0.02}
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
         * address : {"id":"18","tel":"15506410555","sheng":"山东省","shi":"菏泽市","qu":"县城","address":"地址","youbian":"0","name":"测试"}
         * shop : {"id":"8","cp_id":"6","num":"3","nc_id":"1","nc_title":"北京朝阳农场1","img_pic":"/data/img/1910/5da92e25775bb.jpg","cp_title":"荔枝果树","gg_id":"4","welfare":"0.00","price":"1.00"}
         * fruit_tree_variety : {"id":"4","title":"菠萝"}
         * fruit_tree_varietylist : {"id":"4","title":"60-70公斤","fid":"4","price":"3.00"}
         * docume : {"id":"2","title":"7天无理由退货退款"}
         * price : 1.02
         * lnsurance : 0.02
         */

        private AddressBean address;
        private ShopBean shop;
        private FruitTreeVarietyBean fruit_tree_variety;
        private FruitTreeVarietylistBean fruit_tree_varietylist;
        private DocumeBean docume;
        private double price;
        private double lnsurance;

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

        public DocumeBean getDocume() {
            return docume;
        }

        public void setDocume(DocumeBean docume) {
            this.docume = docume;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getLnsurance() {
            return lnsurance;
        }

        public void setLnsurance(double lnsurance) {
            this.lnsurance = lnsurance;
        }

        public static class AddressBean {
            /**
             * id : 18
             * tel : 15506410555
             * sheng : 山东省
             * shi : 菏泽市
             * qu : 县城
             * address : 地址
             * youbian : 0
             * name : 测试
             */

            private String id;
            private String tel;
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
        }

        public static class ShopBean {
            /**
             * id : 8
             * cp_id : 6
             * num : 3
             * nc_id : 1
             * nc_title : 北京朝阳农场1
             * img_pic : /data/img/1910/5da92e25775bb.jpg
             * cp_title : 荔枝果树
             * gg_id : 4
             * welfare : 0.00
             * price : 1.00
             */

            private String id;
            private String cp_id;
            private String num;
            private String nc_id;
            private String nc_title;
            private String img_pic;
            private String cp_title;
            private String gg_id;
            private String welfare;
            private String price;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCp_id() {
                return cp_id;
            }

            public void setCp_id(String cp_id) {
                this.cp_id = cp_id;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getNc_id() {
                return nc_id;
            }

            public void setNc_id(String nc_id) {
                this.nc_id = nc_id;
            }

            public String getNc_title() {
                return nc_title;
            }

            public void setNc_title(String nc_title) {
                this.nc_title = nc_title;
            }

            public String getImg_pic() {
                return img_pic;
            }

            public void setImg_pic(String img_pic) {
                this.img_pic = img_pic;
            }

            public String getCp_title() {
                return cp_title;
            }

            public void setCp_title(String cp_title) {
                this.cp_title = cp_title;
            }

            public String getGg_id() {
                return gg_id;
            }

            public void setGg_id(String gg_id) {
                this.gg_id = gg_id;
            }

            public String getWelfare() {
                return welfare;
            }

            public void setWelfare(String welfare) {
                this.welfare = welfare;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
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
             * title : 60-70公斤
             * fid : 4
             * price : 3.00
             */

            private String id;
            private String title;
            private String fid;
            private String price;

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

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }

        public static class DocumeBean {
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
}
