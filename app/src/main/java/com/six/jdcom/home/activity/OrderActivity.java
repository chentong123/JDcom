package com.six.jdcom.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.six.jdcom.R;
import com.six.jdcom.home.bean.Add;
import com.six.jdcom.utils.Api;
import com.six.jdcom.utils.ApiServer;
import com.six.jdcom.utils.SharePresenters;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity {
    @InjectView(R.id.addrecy)
    RecyclerView addrecy;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.inject(this);
        uid = (int) SharePresenters.get("yonghu",2565);
       HashMap<String,String> map=new HashMap<>();
        map.put("uid",uid+"");
        Retrofit retrofit=new Retrofit
                .Builder().baseUrl(Api.SHOP)
                .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        //通过动态代理得到网络接口对象
        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<AddSelect> add = apiService.getadd("product/getOrders", map);
        add.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddSelect>() {
                    @Override
                    public void onCompleted() {
                        Log.d("aaaaaaaaaaaaaaa","ccccccccccccccccccc");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("aaaaaaaaaaaaaaa","ddddddddddddddddddddd");
                    }

                    @Override
                    public void onNext(AddSelect add) {
                        SelectAdapter adpater = new SelectAdapter(add.getData(),OrderActivity.this);
                        addrecy.setLayoutManager(new LinearLayoutManager(OrderActivity.this));//list
                        addrecy.setAdapter(adpater);

                    }
                });
    }
}
