package com.custchina.shequdemo.dto;


/**
 * Created by codedrinker on 2019/4/24.
 */

public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar_url() {
        return avatarUrl;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatarUrl = avatar_url;
    }
}
