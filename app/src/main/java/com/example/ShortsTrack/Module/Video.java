package com.example.ShortsTrack.Module;

public
class Video {
private String videoUrl,title,desc;
private int likes;

    public
    Video(String videoUrl, String title, String desc, int likes) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.desc = desc;
        this.likes = likes;
    }
    public Video(String videoUrl){
        this.videoUrl = videoUrl;
    }

    public
    String getVideoUrl() {
        return videoUrl;
    }

    public
    void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
