package com.xiarh.purenews.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.xiarh.purenews.R;

/**
 * Created by xiarh on 2017/1/10.
 */

public class SnackBarUtil {

    public static void showSnackBar(CharSequence text, View view, Context context) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
                .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setActionTextColor(context.getResources().getColor(R.color.white))
                .show();
    }

    public static void showSnackBar(int resId, View view, Context context) {
        showSnackBar(context.getResources().getText(resId), view, context);
    }
}
