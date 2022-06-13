package com.erhuo.pojo;

public class WxAccessToken {

    public WxAccessToken() {
    }

    public WxAccessToken(Integer id, String token, String expires, Integer userId) {
        this.id = id;
        this.token = token;
        this.expires = expires;
        this.userId = userId;
    }

    private Integer id;

    private String token;

    private String expires;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Integer getuserId() {
        return userId;
    }

    public void setuserId(Integer userId) {
        this.userId = userId;
    }
}