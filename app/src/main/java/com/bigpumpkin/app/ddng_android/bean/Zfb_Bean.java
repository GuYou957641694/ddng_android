package com.bigpumpkin.app.ddng_android.bean;

public class Zfb_Bean {


    /**
     * msg : success
     * code : 200
     * data : apiname=com.alipay.account.auth&appid=2019080966113968&app_name=WEILAILINGDI&auth_type=AUTHACCOUNT&biz_type=openservice&method=alipay.open.auth.sdk.code.get&product_idAPP_FAST_LOGIN&scope=kuaijie&pid=&target_id=726202b29c1876bee38b58cb0d95f536&sign_type_=RSA2&sign=apiname%3Dcom.alipay.account.auth%26appid%3D2019080966113968%26app_name%3DWEILAILINGDI%26auth_type%3DAUTHACCOUNT%26biz_type%3Dopenservice%26method%3Dalipay.open.auth.sdk.code.get%26product_idAPP_FAST_LOGIN%26scope%3Dkuaijie%26pid%3D%26target_id%3D726202b29c1876bee38b58cb0d95f536%26sign_type_%3DRSA2
     */

    private String msg;
    private String code;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
