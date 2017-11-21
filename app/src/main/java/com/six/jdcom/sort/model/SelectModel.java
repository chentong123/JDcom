package com.six.jdcom.sort.model;

import com.six.jdcom.sort.bean.Select;
import com.six.jdcom.utils.Api;
import com.six.jdcom.utils.ApiServer;

import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chentong on 2017/11/17.
 */

public class SelectModel implements ISelectModel{


    public interface Setdate{
        void getCartdate(Select cartGoods);
    }
    Setdate setdate;

    public SelectModel(Setdate setdate) {
        this.setdate = setdate;
    }


    @Override
    public void GetCartDate(String uid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.SHOP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<Select> getcartgoods = apiService.getcartgoods(uid);
        getcartgoods.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Select>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Select select) {
                        setdate.getCartdate(select);
                    }
                });
    }

}
