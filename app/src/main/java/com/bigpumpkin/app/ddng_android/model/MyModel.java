package com.bigpumpkin.app.ddng_android.model;



import com.bigpumpkin.app.ddng_android.callback.MyCallBack;

import java.util.List;
import java.util.Map;

public interface MyModel {
    //post
    void setpost(String url, Map<String, Object> headmap, Map<String, Object> map, Class clas, MyCallBack callBack);

    //get
    void setget(String url, Map<String, Object> headmap, Map<String, Object> map, Class clas, MyCallBack callBack);

    void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, Class clas, MyCallBack callBack);
}
