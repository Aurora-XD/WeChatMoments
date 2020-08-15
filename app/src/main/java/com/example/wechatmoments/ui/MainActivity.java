package com.example.wechatmoments.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.wechatmoments.MainApplication;
import com.example.wechatmoments.R;
import com.example.wechatmoments.model.Profile;
import com.example.wechatmoments.model.Tweet;
import com.example.wechatmoments.repository.MainRepository;
import com.example.wechatmoments.viewmodel.MainViewModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.main_smart_refresh)
    SmartRefreshLayout mSmartRefreshLayout;

    private MainViewModel mainViewModel;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainViewModel = obtainViewModel();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter = new MainAdapter();
        mRecyclerView.setAdapter(mainAdapter);

        mainViewModel.getProfile();

        mainViewModel.observeMyProfile(this, new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                Log.d("TAG", "onChanged: "+profile.toString());
                mainAdapter.setMyProfile(profile);
                mainAdapter.notifyDataSetChanged();
            }
        });

        mainViewModel.getTweets();

        mainViewModel.observeAllTweets(this, new Observer<List<Tweet>>() {
            @Override
            public void onChanged(List<Tweet> tweets) {
                Log.d("TAG", "Tweets on Changed!" + tweets.get(0).toString());
                mainAdapter.setTweets(tweets);
                mainAdapter.notifyDataSetChanged();
            }
        });

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mainViewModel.getProfile();
                mainViewModel.getTweets();
                refreshLayout.finishRefresh(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mainViewModel.getTweets();
                refreshLayout.finishRefresh(true);
            }
        });
    }

    private MainViewModel obtainViewModel() {
        MainRepository mainRepository = (((MainApplication) getApplicationContext())).getMainRepository();
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.setUserRepository(mainRepository);
        return mainViewModel;
    }

}