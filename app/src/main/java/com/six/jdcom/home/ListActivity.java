package com.six.jdcom.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.six.jdcom.R;
import com.six.jdcom.home.adapter.ListAdapter;
import com.six.jdcom.home.bean.Shop;
import com.six.jdcom.mine.LoginActivity;
import com.six.jdcom.utils.Api;
import com.six.jdcom.utils.ApiServer;
import com.six.jdcom.utils.OnItemClickLitener;

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

public class ListActivity extends AppCompatActivity {
    private String pcid;
    private RecyclerView recyclerView;
    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        EventBus.getDefault().register(ListActivity.this);
        recyclerView= (RecyclerView) findViewById(R.id.id_recyclerview);

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void ononMoonStickyEvent(Shop1 shop1){
        pcid=shop1.date;
        Map<String,String> map=new HashMap<>();
        map.put("pscid",pcid);
        map.put("page","2");
        map.put("sort","1");
        Log.d("fdfdfdfdfdf",map.toString());
        Retrofit build = new Retrofit.Builder().baseUrl(Api.SHOP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiServer apishopservice = build.create(ApiServer.class);
        Observable<Linear> getchuan = apishopservice.getList("product/getProducts", map);
        getchuan.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Linear>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("33333","1111111");
                    }

                    @Override
                    public void onNext(final Linear chuan) {
                        Log.d("111111",pcid+chuan.getMsg());
                         listAdapter=new ListAdapter(ListActivity.this,chuan.getData());
                        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));//list
                        recyclerView.setAdapter(listAdapter);
                        listAdapter.setOnItemClickLitener(new OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent=new Intent(ListActivity.this, XiangqingActivty.class);
                                 EventBus.getDefault().postSticky(new Shop2(chuan.getData().get(position).getPid()+""));
                                 startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                    }
                });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
}
