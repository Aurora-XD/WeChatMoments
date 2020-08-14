package com.example.wechatmoments.ui;

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
import com.example.wechatmoments.repository.MainRepository;
import com.example.wechatmoments.viewmodel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

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