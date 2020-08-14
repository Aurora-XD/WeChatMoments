package com.example.wechatmoments.api;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class NetworkService {

    private static OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(5000L, TimeUnit.MILLISECONDS)
            .readTimeout(5000L, TimeUnit.MILLISECONDS)
            .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))
            .build();

    static Retrofit getRetrofit(String path, Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(path)
                .client(OK_HTTP_CLIENT)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
