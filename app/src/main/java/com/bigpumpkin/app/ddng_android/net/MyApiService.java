package com.bigpumpkin.app.ddng_android.net;

import android.util.ArrayMap;

import com.bigpumpkin.app.ddng_android.bean.Focus_Bean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface MyApiService {

    @GET
    Observable<ResponseBody> get(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> queryMap);

    @POST
    Observable<ResponseBody> post(@Url String url,  @QueryMap Map<String, String> queryMap);

    @PUT
    Observable<ResponseBody> put(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> queryMap);

    @DELETE
    Observable<ResponseBody> delete(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> image(@Url String url, @HeaderMap Map<String, Object> headermap, @Body MultipartBody body);

    @POST
    Observable<ResponseBody> post2(@Url String url, @HeaderMap ArrayMap<String, String> headmap);

}
