package com.bungabear.androidstudy.Util;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Minjae Son on 2018-07-29.
 */
public interface RetrofitService {

    @GET("/posts")
    public Call<JsonArray> getPosts();

    @GET("/posts/{id}")
    public Call<JsonArray> getPost(@Query("id") int id);

    @GET("/photos")
    public Call<JsonArray> getPhotos();

    @GET("/photos/{id}")
    public Call<JsonArray> getPhoto(@Query("id") int id);
}
