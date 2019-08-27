package com.bigpumpkin.app.ddng_android.view;

public interface MyView<T> {
    //成功
    void success(T data);
    //失败
    void error(String error);
}
