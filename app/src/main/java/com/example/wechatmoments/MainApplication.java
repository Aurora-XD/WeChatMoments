package com.example.wechatmoments;

import android.app.Application;

import com.example.wechatmoments.api.ApiService;
import com.example.wechatmoments.repository.MainRepository;

public class MainApplication extends Application {
    private MainRepository mainRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mainRepository = new MainRepository(ApiService.getServiceInstance());
    }

    public MainRepository getMainRepository() {
        return mainRepository;
    }
}
