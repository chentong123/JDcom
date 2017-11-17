package com.six.jdcom.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.six.jdcom.R;
import com.six.jdcom.home.bean.Shop;
import com.six.jdcom.utils.Api;
import com.six.jdcom.utils.ApiServer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class XiangqingActivty extends AppCompatActivity {

    TextView textView;
    private String pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing_activty);
        textView = (TextView) findViewById(R.id.textView3);
        //注册事件
        EventBus.getDefault().register(this);
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        Retrofit build = new Retrofit.Builder().baseUrl(Api.SHOP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiServer apishopservice = build.create(ApiServer.class);
        Observable<Shop> getchuan = apishopservice.getchuan("product/getProductDetail", map);
        getchuan.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Shop>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("33333","1111111");
                    }

                    @Override
                    public void onNext(Shop chuan) {
                        textView.setText(chuan.getData().getSubhead());
                        Log.d("33333",chuan.getData().getSubhead());
                    }
                });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void ononMoonStickyEvent(Shop1 shop1){
        pid=shop1.date;
        Log.d("22222",pid);
    }

}
