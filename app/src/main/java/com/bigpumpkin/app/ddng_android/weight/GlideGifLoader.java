package com.bigpumpkin.app.ddng_android.weight;

import android.content.Context;
import android.widget.ImageView;

import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

public class GlideGifLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(Urls.BASEURL + path).into(imageView);
    }
}
