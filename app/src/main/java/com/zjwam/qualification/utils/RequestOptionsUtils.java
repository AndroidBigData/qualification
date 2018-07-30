package com.zjwam.qualification.utils;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class RequestOptionsUtils {
    public static RequestOptions roundTransform(int radius) {
        return new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .transform(new GlideRoundTransform(radius));
    }
    public static RequestOptions circleTransform() {
        return new RequestOptions()
                .centerCrop()
                .transform(new GlideCircleTransform());
    }
    public static RequestOptions commonTransform() {
        return new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }
}
