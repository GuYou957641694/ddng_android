package com.bigpumpkin.app.ddng_android.persenter;

import java.util.Map;

import okhttp3.RequestBody;

public interface MyPresenter {
    //post
    void getpost(String url, Map<String, Object> headmap, Map<String, Object> map, Class clas);

    //get
    void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class clas);
    //上传头像
    void image(String url, Map<String, RequestBody> headmap, Class clas);

}
