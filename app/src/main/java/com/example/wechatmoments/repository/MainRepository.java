package com.example.wechatmoments.repository;

import com.example.wechatmoments.api.ApiService;
import com.example.wechatmoments.model.Profile;
import com.example.wechatmoments.model.Tweet;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class MainRepository {
    private ApiService apiService;

    public MainRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Maybe<Profile> getProfile() {
        return apiService.getProfile();
    }

    public Observable<List<Tweet>> getTweets(){
        return apiService.getTweets();
    }
}
