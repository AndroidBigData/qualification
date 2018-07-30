package com.zjwam.qualification.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class GlideImageUtil {

    public static void setImageView(Context context, String url, ImageView view,RequestOptions options) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
    }
}
