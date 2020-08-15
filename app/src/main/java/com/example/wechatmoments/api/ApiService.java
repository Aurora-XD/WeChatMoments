package com.example.wechatmoments.api;

import com.example.wechatmoments.model.Profile;
import com.example.wechatmoments.model.Tweet;
import com.example.wechatmoments.util.Constants;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.example.wechatmoments.util.Constants.PROFILE;
import static com.example.wechatmoments.util.Constants.TWEETS;

public interface ApiService {

    @GET(PROFILE)
    Maybe<Profile> getProfile();

    @GET(TWEETS)
    Observable<List<Tweet>> getTweets();

    public static ApiService getServiceInstance() {
        return NetworkService.getRetrofit(Constants.BASE_PATH, GsonConverterFactory.create()).create(ApiService.class);
    }
}
