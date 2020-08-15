package com.example.wechatmoments.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class Tweet {
    @SerializedName("content")
    private String content;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("sender")
    private Sender sender;

    @SerializedName("comments")
    private List<Comments> comments;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Sender getSender() {
        return sender;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public Boolean isEmpty(){
        return StringUtils.isEmpty(content) && (Objects.isNull(images) || images.size()==0);
    }

    @Override
    public String toString() {
        return "TweetsDto{" +
                "content='" + content + '\'' +
                ", images=" + images +
                ", sender=" + sender +
                ", comments=" + comments +
                '}';
    }

    public static class Sender {
        @SerializedName("username")
        private String username;

        @SerializedName("nick")
        private String nick;

        @SerializedName("avatar")
        private String avatar;

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getNick() {
            return nick;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return avatar;
        }

        @Override
        public String toString() {
            return "Sender{" +
                    "username='" + username + '\'' +
                    ", nick='" + nick + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }
    }

    public static class Comments {
        @SerializedName("content")
        private String content;

        @SerializedName("sender")
        private Sender sender;

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setSender(Sender sender) {
            this.sender = sender;
        }

        public Sender getSender() {
            return sender;
        }

        @Override
        public String toString() {
            return "Comments{" +
                    "content='" + content + '\'' +
                    ", sender=" + sender +
                    '}';
        }
    }

}
