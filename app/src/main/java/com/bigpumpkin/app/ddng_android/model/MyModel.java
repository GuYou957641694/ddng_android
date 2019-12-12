package com.bigpumpkin.app.ddng_android.model;


import com.bigpumpkin.app.ddng_android.callback.MyCallBack;

import java.util.Map;

import okhttp3.RequestBody;

public interface MyModel {
    //post
    void setpost(String url, Map<String, Object> headmap, Map<String, Object> map, Class clas, MyCallBack callBack);

    //get
    void setget(String url, Map<String, Object> headmap, Map<String, Object> map, Class clas, MyCallBack callBack);

    void img(String url, Map<String, RequestBody> headmap, Class clas, MyCallBack callBack);
}
