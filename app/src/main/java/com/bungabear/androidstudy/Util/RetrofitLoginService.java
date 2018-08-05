package com.bungabear.androidstudy.Util;

import com.bungabear.androidstudy.Model.LoginResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Minjae Son on 2018-07-29.
 */
public interface RetrofitLoginService {

    @FormUrlEncoded
    @POST("/login")
    public Call<LoginResult> login(@Field("id") String id, @Field("passwd") String passwd);

}
