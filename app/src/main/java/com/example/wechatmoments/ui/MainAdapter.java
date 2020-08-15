package com.example.wechatmoments.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wechatmoments.R;
import com.example.wechatmoments.model.Profile;
import com.example.wechatmoments.model.Tweet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.wechatmoments.util.Constants.MAIN_HEADER;
import static com.example.wechatmoments.util.Constants.MAIN_TWEET;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Profile myProfile = new Profile();
    List<Tweet> tweets = new ArrayList<>();

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_background)
        ImageView mBackground;

        @BindView(R.id.header_avatar)
        ImageView mAvatar;

        @BindView(R.id.header_nick_name)
        TextView mNickName;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class TweetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tweet_sender_avatar)
        ImageView mSenderAvatar;

        @BindView(R.id.tweet_sender_nick_name)
        TextView mSenderName;

        @BindView(R.id.tweet_content)
        TextView mSenderContent;

        @BindView(R.id.tweet_images)
        RecyclerView mTweetImages;

        @BindView(R.id.tweet_comments)
        RecyclerView mTweetCommits;

        public TweetViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setMyProfile(Profile myProfile) {
        this.myProfile = myProfile;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case MAIN_HEADER:
                View header = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_header, parent, false);
                HeaderViewHolder headerViewHolder = new HeaderViewHolder(header);
                return headerViewHolder;
            case MAIN_TWEET:
                View tweets = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_tweet, parent, false);
                TweetViewHolder tweetViewHolder = new TweetViewHolder(tweets);
                return tweetViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).mNickName.setText(myProfile.getNick());
            Glide.with(holder.itemView.getContext())
                    .load(myProfile.getProfileImage())
                    .into(((HeaderViewHolder) holder).mBackground);
            Glide.with(holder.itemView.getContext())
                    .load(myProfile.getAvatar())
                    .into(((HeaderViewHolder) holder).mAvatar);
        }else {
            TweetViewHolder tweetViewHolder = (TweetViewHolder) holder;
            Tweet tweet = tweets.get(position - 1);
            Glide.with(tweetViewHolder.itemView.getContext())
                    .load(tweet.getSender().getAvatar())
                    .into(tweetViewHolder.mSenderAvatar);
            tweetViewHolder.mSenderName.setText(tweet.getSender().getNick());
            tweetViewHolder.mSenderContent.setText(tweet.getContent());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MAIN_HEADER;
        } else {
            return MAIN_TWEET;
        }
    }

    @Override
    public int getItemCount() {
        return 1 + tweets.size();
    }
}
