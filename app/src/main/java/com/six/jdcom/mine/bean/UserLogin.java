package com.six.jdcom.mine.bean;

/**
 * Created by chentong on 2017/11/13.
 */

public class UserLogin {
    private String uname;

    public UserLogin(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "uname='" + uname + '\'' +
                '}';
    }

    public UserLogin() {
        super();
    }
}
