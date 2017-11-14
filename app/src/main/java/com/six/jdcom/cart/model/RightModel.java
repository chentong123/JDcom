package com.six.jdcom.cart.model;

import com.six.jdcom.cart.bean.DataleftBean;
import com.six.jdcom.cart.bean.DatarightBean;
import com.six.jdcom.utils.ApiServer;

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

public class RightModel implements IRightModel{

    private OnRightFinish onFinish;

    public interface OnRightFinish{
        void OnFinishListener(List<DatarightBean.DatasBean.ClassListBean> right);
    }

    public void setOnFinish(OnRightFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void getRight(String url) {
        Retrofit retrofit = new Retrofit
                .Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        final Observable<DatarightBean> right = apiService.getRight();
        right.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DatarightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DatarightBean datarightBean) {
                        List<DatarightBean.DatasBean.ClassListBean> class_list = datarightBean.getDatas().getClass_list();
                        onFinish.OnFinishListener(class_list);
                    }
                });

    }
}
