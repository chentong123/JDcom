package com.six.jdcom.home.model;

import android.util.Log;

import com.six.jdcom.activity.MainActivity;
import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.utils.ApiServer;
import com.six.jdcom.utils.RetroFactory;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chentong on 2017/11/8.
 */

public class UserMode implements IUserMode{

    public static final String  TAG="MainActivity";

    HomeBean.DataBean list;
    private OnFinish onFinish;
    public interface OnFinish{
        void OnFinishListener(HomeBean.DataBean list);
    }
    public void setOnFinish(OnFinish finish){
        this.onFinish=finish;
    }
    @Override
    public void getJson(String url) {
       // list=new ArrayList<>();
      /* Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        final Observable<HomeBean> home = apiService.getHome();*/
       Observable<HomeBean> home= RetroFactory.getInstance().getHome();
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(HomeBean homeBean) {
                        list=homeBean.getData();
                        Log.d("111","12344"+list.toString());
                        onFinish.OnFinishListener(list);
                    }

                    });
    }
}
