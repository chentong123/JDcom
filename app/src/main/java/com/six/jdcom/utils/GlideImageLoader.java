package com.six.jdcom.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;


/**
 * Created by lenovo on 2017/10/13.
 */

public class GlideImageLoader  implements ImageLoaderInterface {

    /*public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }*/

    @Override
    public void displayImage(Context context, Object path, View imageView) {
        Glide.with(context).load(path).into((ImageView) imageView);
    }

    @Override
    public View createImageView(Context context) {
        return null;
    }
}
