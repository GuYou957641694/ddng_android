package com.bigpumpkin.app.ddng_android.bean;

public class Agreement_Bean {


    /**
     * msg :
     * code : 200
     * data : {"id":"1","title":"注册协议","content":"<p>\r\n\t注册协议注册协议\r\n<\/p>\r\n<p>\r\n\t注册协议\r\n<\/p>\r\n<p>\r\n\t注册协议\r\n<\/p>\r\n<p>\r\n\t注册协议\r\n<\/p>\r\n<p>\r\n\t注册协议\r\n<\/p>\r\n<p>\r\n\t注册协议\r\n<\/p>\r\n<p>\r\n<\/p>"}
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
         * id : 1
         * title : 注册协议
         * content : <p>
         注册协议注册协议
         </p>
         <p>
         注册协议
         </p>
         <p>
         注册协议
         </p>
         <p>
         注册协议
         </p>
         <p>
         注册协议
         </p>
         <p>
         注册协议
         </p>
         <p>
         </p>
         */

        private String id;
        private String title;
        private String content;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
