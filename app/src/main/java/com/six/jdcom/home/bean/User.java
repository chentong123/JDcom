package com.six.jdcom.home.bean;

/**
 * Created by chentong on 2017/11/10.
 */

public class User {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "User{" +
                "url='" + url + '\'' +
                '}';
    }

    public User() {
        super();
    }
}
