package com.six.jdcom.cart.model;

import com.six.jdcom.cart.bean.DateGridBean;
import com.six.jdcom.cart.bean.Datebeanitem;
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

public class ThreeModel implements IThreeModel{
    List<DateGridBean.DatasBean.ClassListBean> three;
    private OnThreeFinish onFinish;

    public interface OnThreeFinish{
        void OnFinishListener(List<DateGridBean.DatasBean.ClassListBean> list);
    }

    public void setOnFinish(OnThreeFinish finish){
        this.onFinish=finish;
    }

    @Override
    public void getThreeJson(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<DateGridBean> three = apiService.getThree();
        three.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DateGridBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DateGridBean dateGridBean) {
                        List<DateGridBean.DatasBean.ClassListBean> class_list = dateGridBean.getDatas().getClass_list();
                        onFinish.OnFinishListener(class_list);
                    }
                });
    }
}
