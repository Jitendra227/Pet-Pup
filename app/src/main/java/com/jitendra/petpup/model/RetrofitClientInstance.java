package com.jitendra.petpup.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    public static final String BASE_URL = "https://dog.ceo/api/breeds/list/";
    public static Retrofit retrofit = null;
    public static RetrofitClientInstance retrofitClient;

    public static RetrofitClientInstance getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClientInstance();
        }
        return retrofitClient;
    }


    public static Retrofit getRetrofitInstance() {

        Gson gson = new GsonBuilder().serializeNulls().create();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
