package com.x1opya.news;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {
    @GET("v2/top-headlines")
    Call<NewRespons> getNews(@QueryMap Map<String,String> map);
}
