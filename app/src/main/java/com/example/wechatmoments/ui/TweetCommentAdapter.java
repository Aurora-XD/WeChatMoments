package com.example.wechatmoments.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wechatmoments.R;
import com.example.wechatmoments.model.Tweet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.wechatmoments.util.Constants.BASE_COMMENT;

public class TweetCommentAdapter extends RecyclerView.Adapter<TweetCommentAdapter.CommentViewHolder> {

    private List<Tweet.Comments> comments = new ArrayList<>();

    public void setComments(List<Tweet.Comments> comments) {
        this.comments = comments;
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tweet_comment_name)
        TextView mCommentName;

        @BindView(R.id.tweet_comment_content)
        TextView mCommentContent;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public TweetCommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_comment, parent, false);
        return new CommentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetCommentAdapter.CommentViewHolder holder, int position) {
        Tweet.Comments comment = this.comments.get(position);
        holder.mCommentName.setText(comment.getSender().getNick());
        holder.mCommentContent.setText(BASE_COMMENT+comment.getContent());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
