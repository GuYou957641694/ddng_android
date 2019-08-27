package com.bigpumpkin.app.ddng_android.bean;

import java.io.Serializable;
import java.util.List;

public class Shopping_Bean implements Serializable {


    /**
     * msg : 成功
     * code : 200
     * data : [{"nc_id":"9","id":"8","title":"北京海淀农场9","list":[{"id":"12","cp_id":"763","num":"3","gg_id":"7353","gg_title":"60-70公斤","pz_title":"云莓","spike_price":null,"price":"81.00","in_stock":"420","ms_etime":null,"title":"水果套餐36","sp_pic":"/data/img/1901/5c3c00da33508.png","sp_id":"763","real_price":"81.00","lx":"2"}]},{"nc_id":"1","id":"7","title":"北京朝阳农场1","list":[{"id":"11","cp_id":"461","num":"1","gg_id":"9878","gg_title":"70-80公斤","pz_title":"黄心猕猴桃","spike_price":null,"price":"421.00","in_stock":"149","ms_etime":null,"title":"荔枝果树","sp_pic":"/data/img/1811/5bfb9a1f70711.png","sp_id":"461","real_price":"421.00","lx":"2"},{"id":"10","cp_id":"461","num":"1","gg_id":"9878","gg_title":"70-80公斤","pz_title":"黄心猕猴桃","spike_price":null,"price":"421.00","in_stock":"149","ms_etime":null,"title":"荔枝果树","sp_pic":"/data/img/1811/5bfb9a1f70711.png","sp_id":"461","real_price":"421.00","lx":"2"},{"id":"9","cp_id":"461","num":"1","gg_id":"9878","gg_title":"70-80公斤","pz_title":"黄心猕猴桃","spike_price":null,"price":"421.00","in_stock":"149","ms_etime":null,"title":"荔枝果树","sp_pic":"/data/img/1811/5bfb9a1f70711.png","sp_id":"461","real_price":"421.00","lx":"2"}]},{"nc_id":"6","id":"6","title":"北京海淀农场6","list":[{"id":"8","cp_id":"638","num":"3","gg_id":"10054","gg_title":"30-40公斤","pz_title":"覆盆子","spike_price":null,"price":"109.00","in_stock":"312","ms_etime":null,"title":"水果套餐36","sp_pic":"/data/img/1901/5c3c00da33508.png","sp_id":"638","real_price":"109.00","lx":"2"}]},{"nc_id":"11","id":"5","title":"北京海淀农场11","list":[{"id":"7","cp_id":"817","num":"3","gg_id":"4581","gg_title":"40-50公斤","pz_title":"西梅","spike_price":null,"price":"467.00","in_stock":"495","ms_etime":null,"title":"水果套餐36","sp_pic":"/data/img/1901/5c3c00da33508.png","sp_id":"817","real_price":"467.00","lx":"2"}]},{"nc_id":"10","id":"4","title":"北京海淀农场10","list":[{"id":"5","cp_id":"793","num":"3","gg_id":"4557","gg_title":"40-50公斤","pz_title":"樱桃","spike_price":null,"price":"391.00","in_stock":"446","ms_etime":null,"title":"水果套餐36","sp_pic":"/data/img/1901/5c3c00da33508.png","sp_id":"793","real_price":"391.00","lx":"2"}]},{"nc_id":"2","id":"3","title":"北京海淀农场2","list":[{"id":"4","cp_id":"219","num":"3","gg_id":"3042","gg_title":"60-70公斤","pz_title":"香蕉","spike_price":null,"price":"2.00","in_stock":"196","ms_etime":null,"title":"生产者说23","sp_pic":"/data/img/1901/5c3414897ed4f.png","sp_id":"219","real_price":"2.00","lx":"2"}]},{"nc_id":"3","id":"2","title":"北京海淀农场3","list":[{"id":"14","cp_id":"21","num":"2","gg_id":"1903","gg_title":"40-50公斤","pz_title":"君迁子","spike_price":null,"price":"191.00","in_stock":"131","ms_etime":null,"title":"公益放生 21","sp_pic":"/data/img/1901/5c3414897ed4f.png","sp_id":"21","real_price":"191.00","lx":"2"},{"id":"2","cp_id":"607","num":"2","gg_id":"7197","gg_title":"20-30公斤","pz_title":"白里叶莓","spike_price":null,"price":"452.00","in_stock":"344","ms_etime":null,"title":"水果套餐36","sp_pic":"/data/img/1901/5c3c00da33508.png","sp_id":"607","real_price":"452.00","lx":"2"}]},{"nc_id":"1","id":"1","title":"北京朝阳农场1","list":[{"id":"13","cp_id":"381","num":"3","gg_id":"8856","gg_title":"90-100公斤","pz_title":"黑枣","spike_price":null,"price":"107.00","in_stock":"483","ms_etime":null,"title":"水果套餐37","sp_pic":"/data/img/1901/5c3c0039bb199.png","sp_id":"381","real_price":"107.00","lx":"2"},{"id":"6","cp_id":"9","num":"3","gg_id":"7","gg_title":"90-100公斤","pz_title":"香蕉","spike_price":null,"price":"278.00","in_stock":"78","ms_etime":null,"title":"越南富士9","sp_pic":"/data/img/1811/5bfb9a1f70711.png","sp_id":"9","real_price":"278.00","lx":"2"},{"id":"3","cp_id":"438","num":"3","gg_id":"5145","gg_title":"60-70公斤","pz_title":"黑醋栗","spike_price":null,"price":"311.00","in_stock":"273","ms_etime":null,"title":"公益放生 20","sp_pic":"/data/img/1901/5c341411223c8.png","sp_id":"438","real_price":"311.00","lx":"2"},{"id":"1","cp_id":"235","num":"1","gg_id":"4000","gg_title":"60-70公斤","pz_title":"红醋栗","spike_price":null,"price":"1.00","in_stock":"344","ms_etime":null,"title":"水果套餐39","sp_pic":"/data/img/1901/5c3c0039bb199.png","sp_id":"235","real_price":"1.00","lx":"2"}]}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Cloneable {
        /**
         * nc_id : 9
         * id : 8
         * title : 北京海淀农场9
         * list : [{"id":"12","cp_id":"763","num":"3","gg_id":"7353","gg_title":"60-70公斤","pz_title":"云莓","spike_price":null,"price":"81.00","in_stock":"420","ms_etime":null,"title":"水果套餐36","sp_pic":"/data/img/1901/5c3c00da33508.png","sp_id":"763","real_price":"81.00","lx":"2"}]
         */

        private String nc_id;
        private String id;
        private String title;
        private List<ListBean> list;
        private boolean isSelect_shop;

        public boolean getIsSelect_shop() {
            return isSelect_shop;
        }

        public void setIsSelect_shop(boolean select_shop) {
            isSelect_shop = select_shop;
        }

        public DataBean clone() {
            DataBean o = null;
            try {
                o = (DataBean) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return o;
        }

        public String getNc_id() {
            return nc_id;
        }

        public void setNc_id(String nc_id) {
            this.nc_id = nc_id;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 12
             * cp_id : 763
             * num : 3
             * gg_id : 7353
             * gg_title : 60-70公斤
             * pz_title : 云莓
             * spike_price : null
             * price : 81.00
             * in_stock : 420
             * ms_etime : null
             * title : 水果套餐36
             * sp_pic : /data/img/1901/5c3c00da33508.png
             * sp_id : 763
             * real_price : 81.00
             * lx : 2
             */

            private String id;
            private String cp_id;
            private String num;
            private String gg_id;
            private String gg_title;
            private String pz_title;
            private Object spike_price;
            private String price;
            private String in_stock;
            private Object ms_etime;
            private String title;
            private String sp_pic;
            private String sp_id;
            private String real_price;
            private String lx;
            private boolean isSelect;        //商品是否在购物车中被选中

            public boolean getIsSelect() {
                return isSelect;
            }

            public void setIsSelect(boolean isSelect) {
                this.isSelect = isSelect;
            }

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

            public String getGg_id() {
                return gg_id;
            }

            public void setGg_id(String gg_id) {
                this.gg_id = gg_id;
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

            public String getIn_stock() {
                return in_stock;
            }

            public void setIn_stock(String in_stock) {
                this.in_stock = in_stock;
            }

            public Object getMs_etime() {
                return ms_etime;
            }

            public void setMs_etime(Object ms_etime) {
                this.ms_etime = ms_etime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSp_pic() {
                return sp_pic;
            }

            public void setSp_pic(String sp_pic) {
                this.sp_pic = sp_pic;
            }

            public String getSp_id() {
                return sp_id;
            }

            public void setSp_id(String sp_id) {
                this.sp_id = sp_id;
            }

            public String getReal_price() {
                return real_price;
            }

            public void setReal_price(String real_price) {
                this.real_price = real_price;
            }

            public String getLx() {
                return lx;
            }

            public void setLx(String lx) {
                this.lx = lx;
            }
        }
    }
}
