package com.six.jdcom.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.six.jdcom.R;
import com.six.jdcom.home.bean.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        webView= (WebView) findViewById(R.id.web1);
        //注册事件
        EventBus.getDefault().register(SecondActivity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getData(User user){

        String url = user.getUrl();
        webView.loadUrl(url);
    }
}
