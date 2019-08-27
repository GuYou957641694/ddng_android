package com.bigpumpkin.app.ddng_android.net;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;


import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.utils.LogInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.bigpumpkin.app.ddng_android.config.Urls.BASEURL;

public class RetrofitUtils {

    private MyApiService myApiService;
    private static final int DEFAULT_TIME_OUT = 60;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 60;


    private RetrofitUtils() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());//创建拦截对象

        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这一句一定要记得写，否则没有数据输出
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间
        builder.addNetworkInterceptor(logInterceptor);//设置打印拦截日志
        builder .addInterceptor(new LogInterceptor());
        //初始化Retrofit 并结合各种操作
        Retrofit retrofit = new Retrofit.Builder()
                //结合Gson解析
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                //结合Rxjava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASEURL)
                .build();
        //通过Retrofit创建完 这个ApiService 就可以调用方法了
        myApiService = retrofit.create(MyApiService.class);
    }

    public static RetrofitUtils getInstance() {
        return RetroHolder.retro;
    }

    private static class RetroHolder {
        private static final RetrofitUtils retro = new RetrofitUtils();
    }

    //封装Get方式
    public RetrofitUtils get(String url, Map<String, Object> headmap,
                             Map<String, Object> querymap) {
        if (headmap == null) {
            headmap = new HashMap<>();
        }
        if (querymap == null) {
            querymap = new HashMap<>();
        }
        myApiService.get(url, headmap, querymap).subscribeOn(Schedulers.io())//io就是子线程
                //在主线程调用
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtils.getInstance();
    }


    //封装post方式
    public RetrofitUtils post(String url,
                              Map<String, String> querymap) {
      /*  if (headmap == null) {
            headmap = new HashMap<>();
        }*/
        if (querymap == null) {
            querymap = new HashMap<>();
        }

        myApiService.post(url,  querymap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtils.getInstance();
    }

    //封装post2方式
    @SuppressLint("NewApi")
    public RetrofitUtils post2(String url, ArrayMap<String, String> headmap) {
        if (headmap == null) {
            headmap = new ArrayMap<>();
        }


        myApiService.post2(url, headmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtils.getInstance();
    }


    //封装put方式
    public RetrofitUtils put(String url, Map<String, Object> headmap,
                             Map<String, Object> querymap) {
        if (headmap == null) {
            headmap = new HashMap<>();
        }
        if (querymap == null) {
            querymap = new HashMap<>();
        }

        myApiService.put(url, headmap, querymap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtils.getInstance();
    }

    //封装delete方式
    public RetrofitUtils delete(String url, Map<String, Object> headmap,
                                Map<String, Object> querymap) {
        if (headmap == null) {
            headmap = new HashMap<>();
        }
        if (querymap == null) {
            querymap = new HashMap<>();
        }

        myApiService.delete(url, headmap, querymap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtils.getInstance();
    }

    //封装一个上传图片

    public RetrofitUtils image(String url, Map<String, Object> headmap, Map<String, Object> map,
                               List<Object> list) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (headmap == null) {
            headmap = new HashMap<>();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map.isEmpty()) {
            if (list.size() < 2) {
                for (int i = 0; i < list.size(); i++) {
                    File file = new File((String) list.get(i));
                    builder.addFormDataPart("image", file.getName(),
                            RequestBody.create(MediaType.parse("multipart/octet-stream"), file));
                }
            } else {

            }
        } else {
            if (!String.valueOf(map.get("content")).equals("")) {
                builder.addFormDataPart("content", String.valueOf(map.get("content")));
            } else {
                builder.addFormDataPart("content", "你个小拉给");
            }
            if (list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    File file = new File((String) list.get(i));
                    Log.e("file", (String) list.get(i));
                    builder.addFormDataPart("file", file.getName(),
                            RequestBody.create(MediaType.parse("multipart/octet-stream"), file));
                }
            }
        }
        myApiService.image(url, headmap, builder.build())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtils.getInstance();
    }


    //子类使用
    private Subscriber<ResponseBody> subscriber = new Subscriber<ResponseBody>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {

        }
    };
    //重写一个观察者对象
    private Observer observer = new Observer<ResponseBody>() {

        @Override
        public void onCompleted() {

        }

        //网络处理失败
        @Override
        public void onError(Throwable e) {
            if (httpListener != null) {
                httpListener.onError(e.getMessage());
            }
        }

        //网络处理成功
        @Override
        public void onNext(ResponseBody responseBody) {
            if (httpListener != null) {
                try {
                    httpListener.onSuccess(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public interface HttpListener {

        void onSuccess(String jsonStr);

        void onError(String error);
    }

    private HttpListener httpListener;

    public void setHttpListener(HttpListener listener) {
        this.httpListener = listener;
    }
}
