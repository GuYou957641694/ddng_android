package com.bigpumpkin.app.ddng_android.persenter;


import com.bigpumpkin.app.ddng_android.callback.MyCallBack;
import com.bigpumpkin.app.ddng_android.model.MyModelImpl;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.List;
import java.util.Map;

public class MyPresenterImpl implements MyPresenter {
    private MyView view;
    private MyModelImpl model;

    public MyPresenterImpl(MyView view) {
        this.view = view;
        model = new MyModelImpl();
    }

    /*
     * post
     * */
    @Override
    public void getpost(String url, Map<String, Object> headmap, Map<String, Object> map, Class clas) {
        model.setpost(url, headmap, map, clas, new MyCallBack() {
            @Override
            public void success(Object data) {
                view.success(data);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    /*
     * get
     * */
    @Override
    public void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class clas) {
        model.setget(url, headmap, map, clas, new MyCallBack() {

            @Override
            public void success(Object data) {
                view.success(data);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    @Override
    public void image(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, Class clas) {
        model.img(url, headmap, map, list, clas, new MyCallBack() {

            @Override
            public void success(Object data) {
                view.success(data);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }
}
