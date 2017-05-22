package com.xiarh.purenews.base;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by xiarh on 2017/5/22.
 */

public abstract class AbsListAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public AbsListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }
}
