package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class For_GoodsBean {


    /**
     * msg : 查看成功
     * code : 200
     * data : {"list":[{"farm_name":"北京海淀农场2","numbering":"2019081253609","id":"17","price":"3.70","pay_time":"1565590136","details":{"id":"20","price":"2.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"60-70公斤","pz_title":"香蕉","num":"3"},"all_num":"3","botton3":"1","botton4":"1"},{"farm_name":"北京朝阳农场1","numbering":"2019081227226","id":"16","price":"317.40","pay_time":"1565590136","details":{"id":"19","price":"107.00","pic":"/data/img/1901/5c3c0039bb199.png","gg_title":"90-100公斤","pz_title":"泰国鸡","num":"3"},"all_num":"4","botton3":"1","botton4":"1"},{"farm_name":"北京海淀农场2","numbering":"2019080986298","id":"15","price":"3.70","pay_time":"1565317465","details":{"id":"17","price":"2.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"60-70公斤","pz_title":"香蕉","num":"3"},"all_num":"3","botton3":"1","botton4":"1"},{"farm_name":"北京朝阳农场1","numbering":"2019080929423","id":"14","price":"317.40","pay_time":"1565317465","details":{"id":"16","price":"107.00","pic":"/data/img/1901/5c3c0039bb199.png","gg_title":"90-100公斤","pz_title":"黑枣鸡","num":"3"},"all_num":"4","botton3":"1","botton4":"1"},{"farm_name":"北京海淀农场2","numbering":"2019080991152","id":"13","price":"1.40","pay_time":"1565317382","details":{"id":"14","price":"2.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"60-70公斤","pz_title":"香蕉","num":"3"},"all_num":"3","botton3":"1","botton4":"1"},{"farm_name":"北京海淀农场2","numbering":"2019080887625","id":"12","price":"1.40","pay_time":"1565256584","details":{"id":"13","price":"2.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"60-70公斤","pz_title":"香蕉","num":"3"},"all_num":"3","botton3":"1","botton4":"1"},{"farm_name":"北京海淀农场2","numbering":"2019080845856","id":"11","price":"1.40","pay_time":"1565256515","details":{"id":"12","price":"2.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"60-70公斤","pz_title":"香蕉","num":"3"},"all_num":"3","botton3":"1","botton4":"1"},{"farm_name":"北京海淀农场2","numbering":"2019080831019","id":"10","price":"1.40","pay_time":"1565254695","details":{"id":"11","price":"2.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"60-70公斤","pz_title":"香蕉","num":"3"},"all_num":"3","botton3":"1","botton4":"1"},{"farm_name":"北京海淀农场2","numbering":"2019080813076","id":"9","price":"3.40","pay_time":"1565251146","details":{"id":"10","price":"2.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"60-70公斤","pz_title":"香蕉","num":"3"},"all_num":"3","botton3":"1","botton4":"1"},{"farm_name":"北京朝阳农场1","numbering":"2019080879753","id":"8","price":"-3.90","pay_time":"1565251146","details":{"id":"9","price":"1.00","pic":"/data/img/1901/5c3c0039bb199.png","gg_title":"60-70公斤","pz_title":"红醋栗","num":"1"},"all_num":"1","botton3":"1","botton4":"1"},{"farm_name":"北京朝阳农场1","numbering":"2019070160429","id":"2","price":"1775.88","pay_time":null,"details":{"id":"8","price":"207.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"规格/产量2","pz_title":"公益放生品种2","num":"3"},"all_num":"6","botton3":"1","botton4":"1"},{"farm_name":"北京朝阳农场1","numbering":"2019062829696","id":"1","price":"1775.88","pay_time":null,"details":{"id":"4","price":"207.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"规格/产量2","pz_title":"公益放生品种2","num":"3"},"all_num":"6","botton3":"1","botton4":"1"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * farm_name : 北京海淀农场2
             * numbering : 2019081253609
             * id : 17
             * price : 3.70
             * pay_time : 1565590136
             * details : {"id":"20","price":"2.00","pic":"/data/img/1901/5c3414897ed4f.png","gg_title":"60-70公斤","pz_title":"香蕉","num":"3"}
             * all_num : 3
             * botton3 : 1
             * botton4 : 1
             */

            private String farm_name;
            private String numbering;
            private String id;
            private String price;
            private String pay_time;
            private DetailsBean details;
            private String all_num;
            private String botton3;
            private String botton4;

            public String getFarm_name() {
                return farm_name;
            }

            public void setFarm_name(String farm_name) {
                this.farm_name = farm_name;
            }

            public String getNumbering() {
                return numbering;
            }

            public void setNumbering(String numbering) {
                this.numbering = numbering;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public DetailsBean getDetails() {
                return details;
            }

            public void setDetails(DetailsBean details) {
                this.details = details;
            }

            public String getAll_num() {
                return all_num;
            }

            public void setAll_num(String all_num) {
                this.all_num = all_num;
            }

            public String getBotton3() {
                return botton3;
            }

            public void setBotton3(String botton3) {
                this.botton3 = botton3;
            }

            public String getBotton4() {
                return botton4;
            }

            public void setBotton4(String botton4) {
                this.botton4 = botton4;
            }

            public static class DetailsBean {
                /**
                 * id : 20
                 * price : 2.00
                 * pic : /data/img/1901/5c3414897ed4f.png
                 * gg_title : 60-70公斤
                 * pz_title : 香蕉
                 * num : 3
                 */

                private String id;
                private String price;
                private String pic;
                private String gg_title;
                private String pz_title;
                private String num;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }
        }
    }
}
