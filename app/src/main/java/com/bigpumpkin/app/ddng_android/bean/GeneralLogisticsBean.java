package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class GeneralLogisticsBean {


    /**
     * msg : success
     * code : 200
     * data : {"list":{"message":"ok","nu":"YT4242688631310","ischeck":"1","condition":"F00","com":"yuantong","status":"200","state":"3","data":[{"time":"2019-11-29 20:06:39","ftime":"2019-11-29 20:06:39","context":"客户签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15611635156，投诉电话：010-52729888"},{"time":"2019-11-28 09:07:00","ftime":"2019-11-28 09:07:00","context":"兴盛锦苑10号楼2单元101妈妈驿站已发出自提短信,请上门自提,联系电话18510893822"},{"time":"2019-11-28 09:06:59","ftime":"2019-11-28 09:06:59","context":"快件已到达兴盛锦苑10号楼2单元101妈妈驿站,联系电话18510893822"},{"time":"2019-11-28 07:25:47","ftime":"2019-11-28 07:25:47","context":"【北京市丰台区南小街公司】 派件中 派件人: 傅崧 电话 15611635156 如有疑问，请联系：010-52729888"},{"time":"2019-11-28 07:24:42","ftime":"2019-11-28 07:24:42","context":"【北京市丰台区南小街公司】 已收入"},{"time":"2019-11-27 21:55:02","ftime":"2019-11-27 21:55:02","context":"【北京转运中心】 已发出 下一站 【北京市丰台区南小街】"},{"time":"2019-11-27 21:43:44","ftime":"2019-11-27 21:43:44","context":"【北京转运中心公司】 已收入"},{"time":"2019-11-26 20:52:42","ftime":"2019-11-26 20:52:42","context":"【南京转运中心】 已发出 下一站 【黑庄户接驳点】"},{"time":"2019-11-26 20:48:16","ftime":"2019-11-26 20:48:16","context":"【南京转运中心公司】 已收入"},{"time":"2019-11-26 18:39:20","ftime":"2019-11-26 18:39:20","context":"【江苏省镇江市句容市】 已发出 下一站 【南京转运中心】"},{"time":"2019-11-26 18:10:41","ftime":"2019-11-26 18:10:41","context":"【江苏省镇江市句容市公司】 已打包"},{"time":"2019-11-26 16:42:49","ftime":"2019-11-26 16:42:49","context":"【江苏省镇江市句容市公司】 已收件 取件人: 周寿梅 (18952969227)"}]},"order_id":"1","numbering":"2019120255282","orderlist":[{"id":"1","pic":"/data/img/1911/5dc0eba180231.jpg","title":"北京富士果树5","gg_title":"40-50公斤","pz_title":"芒果"},{"id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","title":"越南富士9","gg_title":"90-100公斤","pz_title":"水蜜桃"}]}
     */

    private String msg;
    private String code;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * list : {"message":"ok","nu":"YT4242688631310","ischeck":"1","condition":"F00","com":"yuantong","status":"200","state":"3","data":[{"time":"2019-11-29 20:06:39","ftime":"2019-11-29 20:06:39","context":"客户签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15611635156，投诉电话：010-52729888"},{"time":"2019-11-28 09:07:00","ftime":"2019-11-28 09:07:00","context":"兴盛锦苑10号楼2单元101妈妈驿站已发出自提短信,请上门自提,联系电话18510893822"},{"time":"2019-11-28 09:06:59","ftime":"2019-11-28 09:06:59","context":"快件已到达兴盛锦苑10号楼2单元101妈妈驿站,联系电话18510893822"},{"time":"2019-11-28 07:25:47","ftime":"2019-11-28 07:25:47","context":"【北京市丰台区南小街公司】 派件中 派件人: 傅崧 电话 15611635156 如有疑问，请联系：010-52729888"},{"time":"2019-11-28 07:24:42","ftime":"2019-11-28 07:24:42","context":"【北京市丰台区南小街公司】 已收入"},{"time":"2019-11-27 21:55:02","ftime":"2019-11-27 21:55:02","context":"【北京转运中心】 已发出 下一站 【北京市丰台区南小街】"},{"time":"2019-11-27 21:43:44","ftime":"2019-11-27 21:43:44","context":"【北京转运中心公司】 已收入"},{"time":"2019-11-26 20:52:42","ftime":"2019-11-26 20:52:42","context":"【南京转运中心】 已发出 下一站 【黑庄户接驳点】"},{"time":"2019-11-26 20:48:16","ftime":"2019-11-26 20:48:16","context":"【南京转运中心公司】 已收入"},{"time":"2019-11-26 18:39:20","ftime":"2019-11-26 18:39:20","context":"【江苏省镇江市句容市】 已发出 下一站 【南京转运中心】"},{"time":"2019-11-26 18:10:41","ftime":"2019-11-26 18:10:41","context":"【江苏省镇江市句容市公司】 已打包"},{"time":"2019-11-26 16:42:49","ftime":"2019-11-26 16:42:49","context":"【江苏省镇江市句容市公司】 已收件 取件人: 周寿梅 (18952969227)"}]}
         * order_id : 1
         * numbering : 2019120255282
         * orderlist : [{"id":"1","pic":"/data/img/1911/5dc0eba180231.jpg","title":"北京富士果树5","gg_title":"40-50公斤","pz_title":"芒果"},{"id":"2","pic":"/data/img/1811/5bfb9a1f70711.png","title":"越南富士9","gg_title":"90-100公斤","pz_title":"水蜜桃"}]
         */

        private ListBean list;
        private String order_id;
        private String numbering;
        private String express_title;
        private String express_pic;
        private String express_tel;
        private List<OrderlistBean> orderlist;

        public String getExpress_pic() {
            return express_pic;
        }

        public void setExpress_pic(String express_pic) {
            this.express_pic = express_pic;
        }

        public String getExpress_tel() {
            return express_tel;
        }

        public void setExpress_tel(String express_tel) {
            this.express_tel = express_tel;
        }

        public String getExpress_title() {
            return express_title;
        }

        public void setExpress_title(String express_title) {
            this.express_title = express_title;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getNumbering() {
            return numbering;
        }

        public void setNumbering(String numbering) {
            this.numbering = numbering;
        }

        public List<OrderlistBean> getOrderlist() {
            return orderlist;
        }

        public void setOrderlist(List<OrderlistBean> orderlist) {
            this.orderlist = orderlist;
        }

        public static class ListBean {
            /**
             * message : ok
             * nu : YT4242688631310
             * ischeck : 1
             * condition : F00
             * com : yuantong
             * status : 200
             * state : 3
             * data : [{"time":"2019-11-29 20:06:39","ftime":"2019-11-29 20:06:39","context":"客户签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15611635156，投诉电话：010-52729888"},{"time":"2019-11-28 09:07:00","ftime":"2019-11-28 09:07:00","context":"兴盛锦苑10号楼2单元101妈妈驿站已发出自提短信,请上门自提,联系电话18510893822"},{"time":"2019-11-28 09:06:59","ftime":"2019-11-28 09:06:59","context":"快件已到达兴盛锦苑10号楼2单元101妈妈驿站,联系电话18510893822"},{"time":"2019-11-28 07:25:47","ftime":"2019-11-28 07:25:47","context":"【北京市丰台区南小街公司】 派件中 派件人: 傅崧 电话 15611635156 如有疑问，请联系：010-52729888"},{"time":"2019-11-28 07:24:42","ftime":"2019-11-28 07:24:42","context":"【北京市丰台区南小街公司】 已收入"},{"time":"2019-11-27 21:55:02","ftime":"2019-11-27 21:55:02","context":"【北京转运中心】 已发出 下一站 【北京市丰台区南小街】"},{"time":"2019-11-27 21:43:44","ftime":"2019-11-27 21:43:44","context":"【北京转运中心公司】 已收入"},{"time":"2019-11-26 20:52:42","ftime":"2019-11-26 20:52:42","context":"【南京转运中心】 已发出 下一站 【黑庄户接驳点】"},{"time":"2019-11-26 20:48:16","ftime":"2019-11-26 20:48:16","context":"【南京转运中心公司】 已收入"},{"time":"2019-11-26 18:39:20","ftime":"2019-11-26 18:39:20","context":"【江苏省镇江市句容市】 已发出 下一站 【南京转运中心】"},{"time":"2019-11-26 18:10:41","ftime":"2019-11-26 18:10:41","context":"【江苏省镇江市句容市公司】 已打包"},{"time":"2019-11-26 16:42:49","ftime":"2019-11-26 16:42:49","context":"【江苏省镇江市句容市公司】 已收件 取件人: 周寿梅 (18952969227)"}]
             */

            private String message;
            private String nu;
            private String ischeck;
            private String condition;
            private String com;
            private String status;
            private String state;
            private List<DataBean> data;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getNu() {
                return nu;
            }

            public void setNu(String nu) {
                this.nu = nu;
            }

            public String getIscheck() {
                return ischeck;
            }

            public void setIscheck(String ischeck) {
                this.ischeck = ischeck;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public String getCom() {
                return com;
            }

            public void setCom(String com) {
                this.com = com;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * time : 2019-11-29 20:06:39
                 * ftime : 2019-11-29 20:06:39
                 * context : 客户签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15611635156，投诉电话：010-52729888
                 */

                private String time;
                private String ftime;
                private String context;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getFtime() {
                    return ftime;
                }

                public void setFtime(String ftime) {
                    this.ftime = ftime;
                }

                public String getContext() {
                    return context;
                }

                public void setContext(String context) {
                    this.context = context;
                }
            }
        }

        public static class OrderlistBean {
            /**
             * id : 1
             * pic : /data/img/1911/5dc0eba180231.jpg
             * title : 北京富士果树5
             * gg_title : 40-50公斤
             * pz_title : 芒果
             */

            private String id;
            private String pic;
            private String title;
            private String gg_title;
            private String pz_title;

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
        }
    }
}
