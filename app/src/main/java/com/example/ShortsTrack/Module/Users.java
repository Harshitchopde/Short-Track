package com.example.ShortsTrack.Module;

public
class Users {
    private String username,usepic,userId,profilepic;
    private int posts;

    public
    Users(String username, String usepic, String userId,String profilepic, int posts) {
        this.username = username;
        this.usepic = usepic;
        this.userId = userId;
        this.posts = posts;
        this.profilepic = profilepic;
    }

    public
    String getProfilepic() {
        return profilepic;
    }

    public
    void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public
    String getUsername() {
        return username;
    }

    public
    void setUsername(String username) {
        this.username = username;
    }

    public
    String getUsepic() {
        return usepic;
    }

    public
    void setUsepic(String usepic) {
        this.usepic = usepic;
    }

    public
    String getUserId() {
        return userId;
    }

    public
    void setUserId(String userId) {
        this.userId = userId;
    }

    public
    int getPosts() {
        return posts;
    }

    public
    void setPosts(int posts) {
        this.posts = posts;
    }
}
