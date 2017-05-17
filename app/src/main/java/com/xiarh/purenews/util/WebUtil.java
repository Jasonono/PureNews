package com.xiarh.purenews.util;

import android.content.Context;
import android.content.Intent;

import com.xiarh.purenews.ui.WebUI;

/**
 * Created by xiarh on 2017/5/15.
 */

public class WebUtil {

    /**
     * 打开网页
     *
     * @param context
     * @param title
     * @param url
     */
    public static void openWeb(Context context, String title, String url) {
        Intent intent = new Intent(context, WebUI.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
