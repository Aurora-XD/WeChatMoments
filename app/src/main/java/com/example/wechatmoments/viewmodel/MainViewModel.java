package com.example.wechatmoments.viewmodel;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.wechatmoments.model.Profile;
import com.example.wechatmoments.model.Tweet;
import com.example.wechatmoments.repository.MainRepository;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MainRepository mainRepository;
    private MutableLiveData<Profile> myProfile = new MutableLiveData<>();
    private MutableLiveData<List<Tweet>> allTweets = new MutableLiveData<>();

    private static final String TAG = "MainViewModel";

    public void observeMyProfile(LifecycleOwner lifecycleOwner, Observer<Profile> observer) {
        myProfile.observe(lifecycleOwner, observer);
    }

    public void observeAllTweets(LifecycleOwner lifecycleOwner, Observer<List<Tweet>> observer) {
        allTweets.observe(lifecycleOwner, observer);
    }

    public void setUserRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void getProfile() {
        Disposable getProfileDisposable = mainRepository.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(TAG, "getProfile failed!"))
                .subscribe(new Consumer<Profile>() {
                    @Override
                    public void accept(Profile profile) throws Exception {
                        Log.d(TAG, "accept: " + profile.toString());
                        myProfile.setValue(profile);
                    }
                });

        compositeDisposable.add(getProfileDisposable);
    }

    public void getTweets() {
        Disposable getTweetsDisposable = mainRepository.getTweets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(TAG, "getProfile failed!"))
                .subscribe(new Consumer<List<Tweet>>() {
                    @Override
                    public void accept(List<Tweet> tweets) throws Exception {
                        allTweets.setValue(tweets.stream().filter(item -> !item.isEmpty()).collect(Collectors.toList()));
                    }
                });

        compositeDisposable.add(getTweetsDisposable);
    }

    @Override
    protected void onCleared() {
        mainRepository = null;
        compositeDisposable.clear();
        super.onCleared();
    }
}
