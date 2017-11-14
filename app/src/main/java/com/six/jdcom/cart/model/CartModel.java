package com.six.jdcom.cart.model;

import android.util.Log;

import com.six.jdcom.cart.bean.DataleftBean;
import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.utils.ApiServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chentong on 2017/11/14.
 */

public class CartModel implements ICartModel{
    List<DataleftBean.DatasBean.ClassListBean> list;
    private OnLeftFinish onFinish;

    public interface OnLeftFinish{
        void OnFinishListener(List<DataleftBean.DatasBean.ClassListBean> list);
    }

    public void setOnFinish(OnLeftFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void getJson(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<DataleftBean> left = apiService.getLeft();
        left.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataleftBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DataleftBean dataleftBean) {
                            list= (List<DataleftBean.DatasBean.ClassListBean>) dataleftBean.getDatas().getClass_list();
                      //  Log.d("aaa",dataleftBean.toString());
                        onFinish.OnFinishListener(list);
                    }
                });
    }
}
