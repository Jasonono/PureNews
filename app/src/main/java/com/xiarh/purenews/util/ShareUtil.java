package com.xiarh.purenews.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by xiarh on 2017/3/30.
 */

public class ShareUtil {
    /**
     * 系统分享
     *
     * @param mContext
     * @param subject
     * @param content
     */
    public static void share(Context mContext, String subject, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(Intent.createChooser(intent, "分享"));
    }
}
