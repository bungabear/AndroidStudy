package com.bungabear.androidstudy.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Minjae Son on 2018-07-29.
 */

public class Singleton {
    private static final Singleton ourInstance = new Singleton();
    private static final RetrofitService retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitService.class);
    public static final RetrofitLoginService retrofitLogin = new Retrofit.Builder().baseUrl(Config.loginUrl).addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitLoginService.class);

    public static Singleton getInstance() {
        return ourInstance;
    }

    public static RetrofitService getRetrofit() {
        return retrofit;
    }

    private Singleton() {

    }
}
