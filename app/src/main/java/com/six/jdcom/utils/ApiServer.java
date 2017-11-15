package com.six.jdcom.utils;


import com.six.jdcom.cart.bean.DataleftBean;
import com.six.jdcom.cart.bean.DatarightBean;
import com.six.jdcom.cart.bean.DateGridBean;
import com.six.jdcom.cart.bean.Datebeanitem;
import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.mine.bean.Login;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {

    @GET("umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage")
    Observable<HomeBean> getHome();
     @POST
    Observable<Login> getLogin(@Url String url, @QueryMap Map<String, String> map);
    @POST
    Observable<Login> getRegister(@Url String url, @QueryMap Map<String, String> map);
    @GET("index.php?act=goods_class")
    Observable<DataleftBean> getLeft();

    @GET("index.php?act=goods_class")
    Observable<DatarightBean> getRight(@Query("gc_id") String gc_id );

    @GET("index.php?act=goods_class")
    Observable<DateGridBean> getThree();
}
