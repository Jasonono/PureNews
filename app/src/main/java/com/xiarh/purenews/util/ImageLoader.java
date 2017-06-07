package com.xiarh.purenews.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xiarh.purenews.R;

/**
 * 图片加载工具类
 * Created by xiarh on 2017/3/14.
 */

public class ImageLoader {

    private static ImageLoader mInstance;

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 加载图片
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    public void with(Context context, Object imgUrl, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 带默认图片
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    public void withDefault(Context context, Object imgUrl, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default)
                .into(imageView);
    }

    /**
     * 圆角图片
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    public void withRound(Context context, Object imgUrl, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(context))
                .placeholder(R.drawable.icon_news)
                .into(imageView);
    }
}
