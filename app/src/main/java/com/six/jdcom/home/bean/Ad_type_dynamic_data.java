package com.six.jdcom.home.bean;

/**
 * Created by chentong on 2017/11/15.
 */

public class Ad_type_dynamic_data {
    private String ad_type_dynamic_data;

    public Ad_type_dynamic_data(String ad_type_dynamic_data) {
        this.ad_type_dynamic_data = ad_type_dynamic_data;
    }

    public String getAd_type_dynamic_data() {
        return ad_type_dynamic_data;
    }

    public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
        this.ad_type_dynamic_data = ad_type_dynamic_data;
    }

    @Override
    public String toString() {
        return "Ad_type_dynamic_data{" +
                "ad_type_dynamic_data='" + ad_type_dynamic_data + '\'' +
                '}';
    }

    public Ad_type_dynamic_data() {
        super();
    }
}
