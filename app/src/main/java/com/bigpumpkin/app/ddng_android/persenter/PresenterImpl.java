package com.bigpumpkin.app.ddng_android.persenter;


import android.util.Log;

import com.bigpumpkin.app.ddng_android.model.ModelImpl;
import com.bigpumpkin.app.ddng_android.net.Contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class PresenterImpl implements Contract.Presenter {
    private ModelImpl model;
    private Contract.View view;

    public PresenterImpl(Contract.View view) {
        this.view = view;
        model = new ModelImpl();
    }

    @Override
    public void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.get(url, headmap, map, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    @Override
    public void post(String url,  Map<String, String> map, Class aClass) {
        model.post(url, map, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
                Log.d(TAG, "error: "+error);
            }
        });
    }

    @Override
    public void put(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.put(url, headmap, map, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    @Override
    public void delete(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.delete(url, headmap, map, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    @Override
    public void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, Class aClass) {
        model.img(url, headmap, map, list, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

}
