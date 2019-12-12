package com.bigpumpkin.app.ddng_android.bean;

import java.util.List;

public class HotSearchsBean {


    /**
     * msg : success
     * code : 200
     * data : ["测试","苹果树","香蕉树","橙子"]
     */

    private String msg;
    private String code;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
