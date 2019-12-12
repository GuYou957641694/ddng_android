package com.bigpumpkin.app.ddng_android.net;

import com.bigpumpkin.app.ddng_android.config.Urls;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HeaderMap;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Retrofits {

    private MyApiServices myApiService;

    public Retrofits() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //存放的头文件
                .baseUrl(Urls.BASEURL)
                .client(okHttpClient)
                .build();
        myApiService = retrofit.create(MyApiServices.class);
    }

    public static Retrofits getInstance() {
        return RetroHolder.OK_UTIL;
    }

    static class RetroHolder {
        private static final Retrofits OK_UTIL = new Retrofits();
    }


    public Retrofits get(String url, Map<String, Object> headmap, Map<String, Object> map) {
        //Schedulers 线程调度器
        myApiService.get(url, headmap, map).subscribeOn(Schedulers.io())//io就是子线程
                //在主线程调用
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return Retrofits.getInstance();
    }

    // 封装一个post的请求方式
    public Retrofits post(String url, Map<String, Object> headmap, Map<String, Object> map) {

        myApiService.post(url, headmap, map).subscribeOn(Schedulers.io())//io就是子线程
                //在主线程调用
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return Retrofits.getInstance();
    }


    // 上传头像
    public Retrofits image(String url, @HeaderMap Map<String, RequestBody> headmap) {

        myApiService.img(url, headmap).subscribeOn(Schedulers.io())//io就是子线程
                //在主线程调用
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return Retrofits.getInstance();
    }


    private Observer<ResponseBody> observer = new Observer<ResponseBody>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (onclick != null) {
                onclick.error(e.getMessage());
            }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            if (onclick != null) {
                try {
                    onclick.success(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public interface onclick {
        void success(String strjson);

        void error(String error);
    }

    public onclick onclick;

    public void getonclcked(onclick onclick) {
        this.onclick = onclick;
    }
}