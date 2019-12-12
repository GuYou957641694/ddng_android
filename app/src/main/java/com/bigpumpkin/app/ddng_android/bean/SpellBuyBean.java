package com.bigpumpkin.app.ddng_android.bean;

public class SpellBuyBean {


    /**
     * msg : SUCCESS
     * code : 200
     * data : {"Address":{"id":"47","tel":"15611036613","sheng":"测试","shi":"测试","qu":"测试","address":"古城","youbian":null,"name":"贾东升"},"Shop":{"id":"6","fidname":"北京朝阳农场1","title":"荔枝果树","pic":"/data/img/1910/5da92e25775bb.jpg","refund":"2","ctime":"12-00,01-00","price":"90.00","Product":"香梨","Product_id":"946","Specifications":"40-50公斤","Specifications_id":"4714","welfare":0.04,"Insurance":0.9,"price_pay":45.76}}
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
         * Address : {"id":"47","tel":"15611036613","sheng":"测试","shi":"测试","qu":"测试","address":"古城","youbian":null,"name":"贾东升"}
         * Shop : {"id":"6","fidname":"北京朝阳农场1","title":"荔枝果树","pic":"/data/img/1910/5da92e25775bb.jpg","refund":"2","ctime":"12-00,01-00","price":"90.00","Product":"香梨","Product_id":"946","Specifications":"40-50公斤","Specifications_id":"4714","welfare":0.04,"Insurance":0.9,"price_pay":45.76}
         */

        private AddressBean Address;
        private ShopBean Shop;

        public AddressBean getAddress() {
            return Address;
        }

        public void setAddress(AddressBean Address) {
            this.Address = Address;
        }

        public ShopBean getShop() {
            return Shop;
        }

        public void setShop(ShopBean Shop) {
            this.Shop = Shop;
        }

        public static class AddressBean {

            private String id;
            private String tel;
            private String sheng;
            private String shi;
            private String qu;
            private String address;
            private Object youbian;
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

            public Object getYoubian() {
                return youbian;
            }

            public void setYoubian(Object youbian) {
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
             * id : 6
             * fidname : 北京朝阳农场1
             * title : 荔枝果树
             * pic : /data/img/1910/5da92e25775bb.jpg
             * refund : 2
             * ctime : 12-00,01-00
             * price : 90.00
             * Product : 香梨
             * Product_id : 946
             * Specifications : 40-50公斤
             * Specifications_id : 4714
             * welfare : 0.04
             * Insurance : 0.9
             * price_pay : 45.76
             */

            private String id;
            private String fidname;
            private String title;
            private String pic;
            private String refund;
            private String ctime;
            private String price;
            private String Product;
            private String Product_id;
            private String Specifications;
            private String Specifications_id;
            private String Postage_price;
            private double welfare;
            private double Insurance;
            private double price_pay;


            public String getPostage_price() {
                return Postage_price;
            }

            public void setPostage_price(String postage_price) {
                Postage_price = postage_price;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getRefund() {
                return refund;
            }

            public void setRefund(String refund) {
                this.refund = refund;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getProduct() {
                return Product;
            }

            public void setProduct(String Product) {
                this.Product = Product;
            }

            public String getProduct_id() {
                return Product_id;
            }

            public void setProduct_id(String Product_id) {
                this.Product_id = Product_id;
            }

            public String getSpecifications() {
                return Specifications;
            }

            public void setSpecifications(String Specifications) {
                this.Specifications = Specifications;
            }

            public String getSpecifications_id() {
                return Specifications_id;
            }

            public void setSpecifications_id(String Specifications_id) {
                this.Specifications_id = Specifications_id;
            }

            public double getWelfare() {
                return welfare;
            }

            public void setWelfare(double welfare) {
                this.welfare = welfare;
            }

            public double getInsurance() {
                return Insurance;
            }

            public void setInsurance(double Insurance) {
                this.Insurance = Insurance;
            }

            public double getPrice_pay() {
                return price_pay;
            }

            public void setPrice_pay(double price_pay) {
                this.price_pay = price_pay;
            }
        }
    }
}
