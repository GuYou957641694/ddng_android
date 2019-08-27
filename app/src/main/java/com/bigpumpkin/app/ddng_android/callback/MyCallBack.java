package com.bigpumpkin.app.ddng_android.callback;

public interface MyCallBack<T> {
    //成功
    void success(T data);
    //失败
    void error(String error);
}
