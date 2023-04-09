package com.example.ShortsTrack.Module;

public
class Video {
private String VideoUrl,title,desc;
private int likes;

    public
    Video(String videoUrl, String title, String desc, int likes) {
        VideoUrl = videoUrl;
        this.title = title;
        this.desc = desc;
        this.likes = likes;
    }

    public
    String getVideoUrl() {
        return VideoUrl;
    }

    public
    void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public
    String getTitle() {
        return title;
    }

    public
    void setTitle(String title) {
        this.title = title;
    }

    public
    String getDesc() {
        return desc;
    }

    public
    void setDesc(String desc) {
        this.desc = desc;
    }

    public
    int getLikes() {
        return likes;
    }

    public
    void setLikes(int likes) {
        this.likes = likes;
    }
}
