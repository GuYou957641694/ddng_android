package com.bigpumpkin.app.ddng_android.net;

import com.bigpumpkin.app.ddng_android.config.Urls;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static String BASE_URL = Urls.BASEURL;
    private Retrofit mRetrofit;

    private static class SingleHolder {
        private static final RetrofitManager _INSTANT = new RetrofitManager(BASE_URL);
    }

    public static RetrofitManager getDefault() {
        return SingleHolder._INSTANT;
    }

    private RetrofitManager(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkhttpClinet())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private OkHttpClient buildOkhttpClinet() {
        return new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public <T> T create(Class<T> Clazz) {
        return mRetrofit.create(Clazz);
    }
}
