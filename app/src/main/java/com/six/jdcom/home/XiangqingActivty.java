package com.six.jdcom.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.six.jdcom.R;
import com.six.jdcom.home.activity.OrderActivity;
import com.six.jdcom.home.bean.Add;
import com.six.jdcom.home.bean.Order;
import com.six.jdcom.home.bean.Shop;
import com.six.jdcom.home.common.PlayerManager;
import com.six.jdcom.home.widget.media.IjkVideoView;
import com.six.jdcom.utils.Api;
import com.six.jdcom.utils.ApiServer;
import com.six.jdcom.utils.SharePresenters;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class XiangqingActivty extends AppCompatActivity implements PlayerManager.PlayerStateListener {


    @InjectView(R.id.video_view)
    IjkVideoView videoView;
    @InjectView(R.id.imageView3)
    ImageView imageView3;
    @InjectView(R.id.textView3)
    TextView textView3;
    @InjectView(R.id.textView4)
    TextView textView4;
    @InjectView(R.id.button3)
    Button button3;
    @InjectView(R.id.button4)
    Button button4;
    @InjectView(R.id.activity_xiangqing_activty)
    RelativeLayout activityXiangqingActivty;
    private String pid;

    private String url4 = "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    private PlayerManager player;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing_activty);
        ButterKnife.inject(this);
        //注册事件
        EventBus.getDefault().register(this);
        Map<String, String> map = new HashMap<>();
        map.put("pid", pid);
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
                        Log.d("33333", "1111111");
                    }

                    @Override
                    public void onNext(Shop chuan) {
                        textView3.setText(chuan.getData().getSubhead());
                        price = chuan.getData().getPrice();
                        textView4.setText(price);
                        String images = chuan.getData().getImages();
                        String[] split = images.split("[|]");
                        String[] split1 = split[0].split("[!]");
                        ImageLoader.getInstance().displayImage(split1[0], imageView3);
                        Log.d("33333", chuan.getData().getImages());
                    }
                });

        initPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void ononMoonStickyEvent(Shop2 shop1) {
        pid = shop1.data;

        Log.d("22222", pid);
    }
    private void initPlayer() {

        //初始化播放器
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(this);
        player.play(url4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    @Override
    public void onComplete() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onPlay() {

    }

    @OnClick({R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //创建订单
            case R.id.button3:
                    int yonghu1= (int) SharePresenters.get("yonghu",2565);
                final Map<String,String> map1=new HashMap<>();
                map1.put("price",price);
                map1.put("uid",yonghu1+"");
                Retrofit build1 = new Retrofit.Builder().baseUrl(Api.SHOP)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
                ApiServer apishopservice1 = build1.create(ApiServer.class);
                Observable<Order> order = apishopservice1.getOrder("product/createOrder", map1);
                order.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Order>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Order order) {
                                String msg = order.getMsg();
                                if ("订单创建成功".equals(msg))
                                {
                                    Toast.makeText(XiangqingActivty.this,"成功了",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(XiangqingActivty.this, OrderActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(XiangqingActivty.this,"创建失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.button4:
                //添加到购物车
                int yonghu = (int) SharePresenters.get("yonghu",2565);
                Toast.makeText(XiangqingActivty.this,"yonghu"+yonghu,Toast.LENGTH_SHORT).show();
                Map<String, String> map = new HashMap<>();
                map.put("pid", pid);
                map.put("uid",yonghu+"");
               // Log.d("qqqqqqqqqqqqqqqqqqqqqqqqq",map.toString());
                Retrofit build = new Retrofit.Builder().baseUrl(Api.SHOP)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
                ApiServer apishopservice = build.create(ApiServer.class);
                Observable<Add> getchuan = apishopservice.getAdd("product/addCart", map);
                getchuan.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Add>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("33333", "1111111");
                            }

                            @Override
                            public void onNext(Add chuan) {
                                String msg = chuan.getMsg();
                                if ("加购成功".equals(msg))
                                {
                                    Toast.makeText(XiangqingActivty.this,"成功了",Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                break;
        }
    }
}
