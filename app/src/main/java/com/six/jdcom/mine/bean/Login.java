package com.six.jdcom.mine.bean;

/**
 * Created by chentong on 2017/11/10.
 */

public class Login {
    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"createtime":"2017-10-14T00:00:00","gender":0,"icon":null,"mobile":"15727377159","money":0,"nickname":null,"password":"666666","uid":600,"username":"15727377159"}
     */

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
