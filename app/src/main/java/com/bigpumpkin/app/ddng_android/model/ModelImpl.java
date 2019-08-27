package com.bigpumpkin.app.ddng_android.model;

import com.bigpumpkin.app.ddng_android.net.Contract;
import com.bigpumpkin.app.ddng_android.net.RetrofitUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.POST;


public class ModelImpl implements Contract.Model{
    @Override
    public void get(String url, Map<String, Object> headmap, Map<String, Object> map, final Class aClass, final Contract.MyCallBack myCallBack) {
        RetrofitUtils.getInstance().get(url, headmap, map).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, aClass);
                myCallBack.success(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.error(error);
            }
        });
    }

    @Override
    public void post(String url,  Map<String, String> map, final Class aClass, final Contract.MyCallBack myCallBack) {
        RetrofitUtils.getInstance().post(url, map).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, aClass);
                myCallBack.success(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.error(error);
            }
        });
    }

    @Override
    public void put(String url, Map<String, Object> headmap, Map<String, Object> map, final Class aClass, final Contract.MyCallBack myCallBack) {
        RetrofitUtils.getInstance().put(url, headmap, map).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, aClass);
                myCallBack.success(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.error(error);
            }
        });
    }

    @Override
    public void delete(String url, Map<String, Object> headmap, Map<String, Object> map, final Class aClass, final Contract.MyCallBack myCallBack) {
        RetrofitUtils.getInstance().delete(url, headmap, map).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, aClass);
                myCallBack.success(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.error(error);
            }
        });
    }

    @Override
    public void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, final Class aClass, final Contract.MyCallBack myCallBack) {
        RetrofitUtils.getInstance().image(url, headmap, map, list).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, aClass);
                myCallBack.success(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.error(error);
            }
        });
    }

}
