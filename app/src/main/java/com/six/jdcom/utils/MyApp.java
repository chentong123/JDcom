package com.six.jdcom.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by chentong on 2017/11/9.
 */

public class MyApp extends Application{
    public static Context context;
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
        context=getApplicationContext();
        ImageLoaderConfiguration de=ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(de);
    }
    public static Context getcontext(){
        return context;
    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
