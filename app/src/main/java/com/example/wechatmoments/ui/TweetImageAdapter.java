package com.example.wechatmoments.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wechatmoments.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetImageAdapter extends RecyclerView.Adapter<TweetImageAdapter.ImageViewHolder> {

    public List<String> images = new ArrayList<>();

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tweet_image_item)
        ImageView mImage;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public TweetImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_image, parent, false);
        return new ImageViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetImageAdapter.ImageViewHolder holder, int position) {
        String image = images.get(position);
        Glide.with(holder.itemView.getContext())
                .load(image)
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return Math.min(images.size(), 9);
    }
}
