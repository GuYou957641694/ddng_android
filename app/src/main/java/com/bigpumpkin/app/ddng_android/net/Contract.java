package com.bigpumpkin.app.ddng_android.net;

import java.util.List;
import java.util.Map;

public interface Contract {

    interface Model {

        void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass,
                 MyCallBack myCallBack);

        void post(String url, Map<String, String> map, Class aClass,
                  MyCallBack myCallBack);

        void put(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass, MyCallBack myCallBack);

        void delete(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass, MyCallBack myCallBack);

        void img(String url, Map<String, Object> headmap, Map<String, Object> map,
                 List<Object> list, Class aClass, MyCallBack myCallBack);

    }


    interface View<T> {
        void success(T success);

        void error(String error);
    }

    interface Presenter {
        void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass);

        void post(String url, Map<String, String> map, Class aClass);

        void put(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass);

        void delete(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass);

        void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, Class aClass);
    }

    interface MyCallBack<T> {
        void success(T success);

        void error(String error);
    }

}
