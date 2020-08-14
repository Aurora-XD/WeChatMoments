package com.example.wechatmoments.api;

import com.example.wechatmoments.model.Profile;
import com.example.wechatmoments.util.Constants;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    @GET("profile.json")
    Maybe<Profile> getProfile();

    public static ApiService getServiceInstance() {
        return NetworkService.getRetrofit(Constants.BASE_PATH, GsonConverterFactory.create()).create(ApiService.class);
    }
}
