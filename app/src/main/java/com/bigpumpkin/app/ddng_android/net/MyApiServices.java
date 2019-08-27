package com.bigpumpkin.app.ddng_android.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface MyApiServices {
    @GET
    Observable<ResponseBody> get(@Url String url, @HeaderMap Map<String, Object> headmap, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @HeaderMap Map<String, Object> headmap, @FieldMap Map<String, Object> map);

    @POST
    Observable<ResponseBody> img(@Url String url, @HeaderMap Map<String, Object> headmap, @Body MultipartBody body);

    @PUT
    Observable<ResponseBody> put(@Url String url, @HeaderMap Map<String, Object> headmap, @QueryMap Map<String, Object> map);

    @DELETE
    Observable<ResponseBody> delete(@Url String url, @HeaderMap Map<String, Object> headmap, @QueryMap Map<String, Object> map);
    //上传头像  带头部
    @Multipart
    @POST
    Observable<ResponseBody> Veido(@Url String url, @HeaderMap Map<String, Object> HeadMap, @QueryMap Map<String, String> Object, @Part MultipartBody.Part file);
}
