package com.bigpumpkin.app.ddng_android.bean;

import java.io.Serializable;
import java.util.List;

public class IntegralBean implements Serializable {


    /**
     * msg : success
     * code : 200
     * data : {"integral":"3425","list":[{"id":"55","title":"12,修剪操作","uid":"1","ctime":"1561453390","fen":"10","types":"1"},{"id":"56","title":"12,浇水操作","uid":"1","ctime":"1561453399","fen":"10","types":"1"},{"id":"57","title":"12,松土操作","uid":"1","ctime":"1561453407","fen":"10","types":"1"},{"id":"58","title":"树编号为14你很棒，我们已通知农场主正常维护。积分已存入","uid":"1","ctime":"1561453407","fen":"0","types":"1"},{"id":"59","title":"1,农药操作","uid":"1","ctime":"1561456320","fen":"10","types":"1"},{"id":"60","title":"1,施肥操作","uid":"1","ctime":"1561456336","fen":"10","types":"1"},{"id":"61","title":"1,修剪操作","uid":"1","ctime":"1561456426","fen":"10","types":"1"},{"id":"62","title":"1,松土操作","uid":"1","ctime":"1561456507","fen":"10","types":"1"},{"id":"63","title":"2,农药操作","uid":"1","ctime":"1561456584","fen":"10","types":"1"},{"id":"64","title":"2,除草操作","uid":"1","ctime":"1561456606","fen":"10","types":"1"},{"id":"65","title":"4,农药操作","uid":"1","ctime":"1561456823","fen":"10","types":"1"},{"id":"66","title":"4,施肥操作","uid":"1","ctime":"1561456887","fen":"10","types":"1"},{"id":"67","title":"4,浇水操作","uid":"1","ctime":"1561457057","fen":"10","types":"1"},{"id":"68","title":"4,松土操作","uid":"1","ctime":"1561457097","fen":"10","types":"1"},{"id":"69","title":"5,松土操作","uid":"1","ctime":"1561534921","fen":"10","types":"1"},{"id":"70","title":"5,浇水操作","uid":"1","ctime":"1561534982","fen":"10","types":"1"},{"id":"71","title":"5,农药操作","uid":"1","ctime":"1561535029","fen":"10","types":"1"},{"id":"72","title":"5,施肥操作","uid":"1","ctime":"1561535086","fen":"10","types":"1"},{"id":"73","title":"6,农药操作","uid":"1","ctime":"1561535193","fen":"10","types":"1"},{"id":"74","title":"6,施肥操作","uid":"1","ctime":"1561535200","fen":"10","types":"1"},{"id":"75","title":"6,浇水操作","uid":"1","ctime":"1561535262","fen":"10","types":"1"},{"id":"76","title":"6,修剪操作","uid":"1","ctime":"1561535310","fen":"10","types":"1"},{"id":"77","title":"6,除草操作","uid":"1","ctime":"1561535451","fen":"10","types":"1"},{"id":"78","title":"6,松土操作","uid":"1","ctime":"1561535618","fen":"10","types":"1"},{"id":"79","title":"树编号为7你很棒，我们已通知农场主正常维护。积分已存入","uid":"1","ctime":"1561535618","fen":"0","types":"1"},{"id":"80","title":"7,农药操作","uid":"1","ctime":"1561535921","fen":"10","types":"1"},{"id":"81","title":"7,施肥操作","uid":"1","ctime":"1561535960","fen":"10","types":"1"},{"id":"82","title":"7,修剪操作","uid":"1","ctime":"1561535976","fen":"10","types":"1"},{"id":"83","title":"7,除草操作","uid":"1","ctime":"1561535985","fen":"10","types":"1"},{"id":"84","title":"8,农药操作","uid":"1","ctime":"1561536103","fen":"10","types":"1"}]}
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
        /**
         * integral : 3425
         * list : [{"id":"55","title":"12,修剪操作","uid":"1","ctime":"1561453390","fen":"10","types":"1"},{"id":"56","title":"12,浇水操作","uid":"1","ctime":"1561453399","fen":"10","types":"1"},{"id":"57","title":"12,松土操作","uid":"1","ctime":"1561453407","fen":"10","types":"1"},{"id":"58","title":"树编号为14你很棒，我们已通知农场主正常维护。积分已存入","uid":"1","ctime":"1561453407","fen":"0","types":"1"},{"id":"59","title":"1,农药操作","uid":"1","ctime":"1561456320","fen":"10","types":"1"},{"id":"60","title":"1,施肥操作","uid":"1","ctime":"1561456336","fen":"10","types":"1"},{"id":"61","title":"1,修剪操作","uid":"1","ctime":"1561456426","fen":"10","types":"1"},{"id":"62","title":"1,松土操作","uid":"1","ctime":"1561456507","fen":"10","types":"1"},{"id":"63","title":"2,农药操作","uid":"1","ctime":"1561456584","fen":"10","types":"1"},{"id":"64","title":"2,除草操作","uid":"1","ctime":"1561456606","fen":"10","types":"1"},{"id":"65","title":"4,农药操作","uid":"1","ctime":"1561456823","fen":"10","types":"1"},{"id":"66","title":"4,施肥操作","uid":"1","ctime":"1561456887","fen":"10","types":"1"},{"id":"67","title":"4,浇水操作","uid":"1","ctime":"1561457057","fen":"10","types":"1"},{"id":"68","title":"4,松土操作","uid":"1","ctime":"1561457097","fen":"10","types":"1"},{"id":"69","title":"5,松土操作","uid":"1","ctime":"1561534921","fen":"10","types":"1"},{"id":"70","title":"5,浇水操作","uid":"1","ctime":"1561534982","fen":"10","types":"1"},{"id":"71","title":"5,农药操作","uid":"1","ctime":"1561535029","fen":"10","types":"1"},{"id":"72","title":"5,施肥操作","uid":"1","ctime":"1561535086","fen":"10","types":"1"},{"id":"73","title":"6,农药操作","uid":"1","ctime":"1561535193","fen":"10","types":"1"},{"id":"74","title":"6,施肥操作","uid":"1","ctime":"1561535200","fen":"10","types":"1"},{"id":"75","title":"6,浇水操作","uid":"1","ctime":"1561535262","fen":"10","types":"1"},{"id":"76","title":"6,修剪操作","uid":"1","ctime":"1561535310","fen":"10","types":"1"},{"id":"77","title":"6,除草操作","uid":"1","ctime":"1561535451","fen":"10","types":"1"},{"id":"78","title":"6,松土操作","uid":"1","ctime":"1561535618","fen":"10","types":"1"},{"id":"79","title":"树编号为7你很棒，我们已通知农场主正常维护。积分已存入","uid":"1","ctime":"1561535618","fen":"0","types":"1"},{"id":"80","title":"7,农药操作","uid":"1","ctime":"1561535921","fen":"10","types":"1"},{"id":"81","title":"7,施肥操作","uid":"1","ctime":"1561535960","fen":"10","types":"1"},{"id":"82","title":"7,修剪操作","uid":"1","ctime":"1561535976","fen":"10","types":"1"},{"id":"83","title":"7,除草操作","uid":"1","ctime":"1561535985","fen":"10","types":"1"},{"id":"84","title":"8,农药操作","uid":"1","ctime":"1561536103","fen":"10","types":"1"}]
         */

        private String integral;
        private List<ListBean> list;

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * id : 55
             * title : 12,修剪操作
             * uid : 1
             * ctime : 1561453390
             * fen : 10
             * types : 1
             */

            private String id;
            private String title;
            private String uid;
            private long ctime;
            private String fen;
            private String types;

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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public long getCtime() {
                return ctime;
            }

            public void setCtime(long ctime) {
                this.ctime = ctime;
            }

            public String getFen() {
                return fen;
            }

            public void setFen(String fen) {
                this.fen = fen;
            }

            public String getTypes() {
                return types;
            }

            public void setTypes(String types) {
                this.types = types;
            }
        }
    }
}
