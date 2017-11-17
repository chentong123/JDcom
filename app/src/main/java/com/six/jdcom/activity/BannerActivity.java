package com.six.jdcom.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.six.jdcom.R;
import com.six.jdcom.home.bean.Ad_type_dynamic_data;
import com.six.jdcom.home.bean.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BannerActivity extends AppCompatActivity {

    @InjectView(R.id.web2)
    WebView web2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.inject(this);
        //注册事件
        EventBus.getDefault().register(BannerActivity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getData(Ad_type_dynamic_data user){

        String url = user.getAd_type_dynamic_data();
        web2.loadUrl(url);
    }
}
