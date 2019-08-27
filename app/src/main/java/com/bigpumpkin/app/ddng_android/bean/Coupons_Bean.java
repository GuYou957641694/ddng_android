package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class Coupons_Bean {


    /**
     * msg : 成功
     * code : 200
     * data : {"list":[{"id":"7","man":"200","jian":"3","ctime":"1548864000","etime":"1569859200","nc_id":"12","title":"北京海淀农场12","attr":"2","zt":"1","time":"2019:01:31-2019:10:01","lx":"1","yx":"2"},{"id":"6","man":"200","jian":"5","ctime":"1548864000","etime":"1569945600","nc_id":"12","title":"北京海淀农场12","attr":"2","zt":"1","time":"2019:01:31-2019:10:02","lx":"1","yx":"2"},{"id":"5","man":"200","jian":"10","ctime":"1548864000","etime":"1569945600","nc_id":"8","title":"北京海淀农场8","attr":"2","zt":"1","time":"2019:01:31-2019:10:02","lx":"1","yx":"2"},{"id":"4","man":"300","jian":"15","ctime":"1548259200","etime":"1572019200","nc_id":"2","title":"北京海淀农场2","attr":"2","zt":"1","time":"2019:01:24-2019:10:26","lx":"1","yx":"2"},{"id":"3","man":"5","jian":"5","ctime":"1550937600","etime":"1700086399","nc_id":"1","title":"北京海淀农场2","attr":"1","zt":"1","time":"2019:02:24-2023:11:16","lx":"1","yx":"1"},{"id":"2","man":"3","jian":"3","ctime":"1550937600","etime":"1700086399","nc_id":"1","title":"北京海淀农场2","attr":"1","zt":"1","time":"2019:02:24-2023:11:16","lx":"1","yx":"1"},{"id":"1","man":"500","jian":"5","ctime":"1550937600","etime":"1572019200","nc_id":"1","title":"北京海淀农场2","attr":"1","zt":"1","time":"2019:02:24-2019:10:26","lx":"1","yx":"1"}]}
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
             * id : 7
             * man : 200
             * jian : 3
             * ctime : 1548864000
             * etime : 1569859200
             * nc_id : 12
             * title : 北京海淀农场12
             * attr : 2
             * zt : 1
             * time : 2019:01:31-2019:10:01
             * lx : 1
             * yx : 2
             */

            private String id;
            private String man;
            private String jian;
            private String ctime;
            private String etime;
            private String nc_id;
            private String title;
            private String attr;
            private String zt;
            private String time;
            private String lx;
            private String yx;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getEtime() {
                return etime;
            }

            public void setEtime(String etime) {
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

            public String getAttr() {
                return attr;
            }

            public void setAttr(String attr) {
                this.attr = attr;
            }

            public String getZt() {
                return zt;
            }

            public void setZt(String zt) {
                this.zt = zt;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getLx() {
                return lx;
            }

            public void setLx(String lx) {
                this.lx = lx;
            }

            public String getYx() {
                return yx;
            }

            public void setYx(String yx) {
                this.yx = yx;
            }
        }
    }
}
