package com.six.jdcom.home.model;


import android.util.Log;

import com.six.jdcom.home.bean.KindBean;
import com.six.jdcom.home.bean.RightBean;
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
 * Created by admin on 2017/11/14.
 */

public class Kmodel implements IKmod{
    public interface onFinshkind{
        void Finshkind(List<KindBean.DataBean> data);
    }
    private onRightkind onrightkind;

    public void SetRightkind(onRightkind onrightkind) {
        this.onrightkind = onrightkind;
    }
    public interface onRightkind{
        void Rigthkind(List<RightBean.DataBean> data);
    }
    private onFinshkind onfinshkind;

    public void Setkind(onFinshkind onfinshkind) {
        this.onfinshkind = onfinshkind;
    }

    @Override
    public void getKind(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.SHOP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        final Observable<KindBean> getkd = apiService.getkd();
        getkd.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onEroor","11");
                    }

                    @Override
                    public void onNext(KindBean kindBean) {
                        List<KindBean.DataBean> data = kindBean.getData();
                        onfinshkind.Finshkind(data);
                    }
                });
    }

    @Override
    public void getKindrig(Map<String,String> msp, String url) {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.SHOP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        final Observable<RightBean> kindBeanObservable = apiServer.getkdRight(url,msp);
        kindBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RightBean rightBean) {
                        List<RightBean.DataBean> data = rightBean.getData();
                        onrightkind.Rigthkind(data);
                    }
                });
    }
}
