package com.bigpumpkin.app.ddng_android.bean;

import java.io.Serializable;
import java.util.List;

public class Coupons_Bean  implements Serializable{

    /**
     * msg : success
     * code : 200
     * data : {"Overdue":[{"id":"21","zt":"1","man":"500","jian":"50","ctime":"1548864000","etime":"1575945214","title":"5元优惠","nc_id":"1"}],"Used":[{"id":"18","zt":"2","man":"100","jian":"3","ctime":"1548259200","etime":"1602345600","title":"惠","nc_id":"1"},{"id":"20","zt":"2","man":"500","jian":"40","ctime":"1548864000","etime":"1602345600","title":"5元优惠","nc_id":"1"},{"id":"36","zt":"2","man":"500","jian":"100","ctime":"1550937600","etime":"1602345600","title":"首页全场优惠卷","nc_id":"3"}],"Unused":[{"id":"19","zt":"1","man":"1000","jian":"1000","ctime":"1548864000","etime":"1602345600","title":"惠","nc_id":"1"}]}
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

    public static class DataBean implements Serializable{
        private List<OverdueBean> Overdue;
        private List<UsedBean> Used;
        private List<UnusedBean> Unused;

        public List<OverdueBean> getOverdue() {
            return Overdue;
        }

        public void setOverdue(List<OverdueBean> Overdue) {
            this.Overdue = Overdue;
        }

        public List<UsedBean> getUsed() {
            return Used;
        }

        public void setUsed(List<UsedBean> Used) {
            this.Used = Used;
        }

        public List<UnusedBean> getUnused() {
            return Unused;
        }

        public void setUnused(List<UnusedBean> Unused) {
            this.Unused = Unused;
        }

        public static class OverdueBean implements Serializable {
            /**
             * id : 21
             * zt : 1
             * man : 500
             * jian : 50
             * ctime : 1548864000
             * etime : 1575945214
             * title : 5元优惠
             * nc_id : 1
             */

            private String id;
            private String zt;
            private String man;
            private String jian;
            private long ctime;
            private long etime;
            private String title;
            private String nc_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getZt() {
                return zt;
            }

            public void setZt(String zt) {
                this.zt = zt;
            }

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNc_id() {
                return nc_id;
            }

            public void setNc_id(String nc_id) {
                this.nc_id = nc_id;
            }
        }

        public static class UsedBean implements Serializable{
            /**
             * id : 18
             * zt : 2
             * man : 100
             * jian : 3
             * ctime : 1548259200
             * etime : 1602345600
             * title : 惠
             * nc_id : 1
             */

            private String id;
            private String zt;
            private String man;
            private String jian;
            private long ctime;
            private long etime;
            private String title;
            private String nc_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getZt() {
                return zt;
            }

            public void setZt(String zt) {
                this.zt = zt;
            }

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNc_id() {
                return nc_id;
            }

            public void setNc_id(String nc_id) {
                this.nc_id = nc_id;
            }
        }

        public static class UnusedBean implements Serializable{
            /**
             * id : 19
             * zt : 1
             * man : 1000
             * jian : 1000
             * ctime : 1548864000
             * etime : 1602345600
             * title : 惠
             * nc_id : 1
             */

            private String id;
            private String zt;
            private String man;
            private String jian;
            private long ctime;
            private long etime;
            private String title;
            private String nc_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getZt() {
                return zt;
            }

            public void setZt(String zt) {
                this.zt = zt;
            }

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNc_id() {
                return nc_id;
            }

            public void setNc_id(String nc_id) {
                this.nc_id = nc_id;
            }
        }
    }
}
