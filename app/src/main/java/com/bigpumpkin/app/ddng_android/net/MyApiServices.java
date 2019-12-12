package com.bigpumpkin.app.ddng_android.net;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface MyApiServices {
    @GET
    Observable<ResponseBody> get(@Url String url, @HeaderMap Map<String, Object> headmap, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @HeaderMap Map<String, Object> headmap, @FieldMap Map<String, Object> map);

    @Multipart
    @POST
    Observable<ResponseBody> img(@Url String url, @PartMap Map<String, RequestBody> params);

    @PUT
    Observable<ResponseBody> put(@Url String url, @HeaderMap Map<String, Object> headmap, @QueryMap Map<String, Object> map);

    @DELETE
    Observable<ResponseBody> delete(@Url String url, @HeaderMap Map<String, Object> headmap, @QueryMap Map<String, Object> map);


}
