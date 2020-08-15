package com.example.wechatmoments.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wechatmoments.R;
import com.example.wechatmoments.model.Profile;
import com.example.wechatmoments.model.Tweet;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        private void bindToHeaderViewHolder(Profile myProfile) {
            this.mNickName.setText(myProfile.getNick());
            Glide.with(this.itemView.getContext())
                    .load(myProfile.getProfileImage())
                    .into(this.mBackground);
            Glide.with(this.itemView.getContext())
                    .load(myProfile.getAvatar())
                    .into(this.mAvatar);
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

        private TweetImageAdapter tweetImageAdapter;
        private TweetCommentAdapter tweetCommentAdapter;

        public TweetViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initTweetsViewAdapter(mTweetImages);
            initTweetCommentViewAdapter(mTweetCommits);
        }

        private void initTweetsViewAdapter(RecyclerView mTweetImages) {
            mTweetImages.setHasFixedSize(true);
            mTweetImages.setLayoutManager(new GridLayoutManager(mTweetImages.getContext(), 3));

            tweetImageAdapter = new TweetImageAdapter();
            mTweetImages.setAdapter(tweetImageAdapter);
        }

        private void initTweetCommentViewAdapter(RecyclerView mTweetCommits) {
            mTweetCommits.setHasFixedSize(true);
            mTweetCommits.setLayoutManager(new LinearLayoutManager(mTweetCommits.getContext()));

            tweetCommentAdapter = new TweetCommentAdapter();
            mTweetCommits.setAdapter(tweetCommentAdapter);
        }

        private void bindToTweetsViewHolder(Tweet tweet) {
            Glide.with(this.itemView.getContext())
                    .load(tweet.getSender().getAvatar())
                    .into(this.mSenderAvatar);
            this.mSenderName.setText(tweet.getSender().getNick());
            if (!StringUtils.isEmpty(tweet.getContent())) {
                this.mSenderContent.setText(tweet.getContent());
            }else {
                this.mSenderContent.setVisibility(View.GONE);
            }
            if (Objects.nonNull(tweet.getImages()) && tweet.getImages().size() > 0) {
                this.tweetImageAdapter.setImages(tweet.getImages());
            }else {
                this.mTweetImages.setVisibility(View.GONE);
            }
            if (Objects.nonNull(tweet.getComments()) && tweet.getComments().size() > 0) {
                this.tweetCommentAdapter.setComments(tweet.getComments());
            }else {
                this.mTweetCommits.setVisibility(View.GONE);
            }
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
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.bindToHeaderViewHolder(myProfile);
        } else {
            TweetViewHolder tweetViewHolder = (TweetViewHolder) holder;
            tweetViewHolder.bindToTweetsViewHolder(tweets.get(position - 1));
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
