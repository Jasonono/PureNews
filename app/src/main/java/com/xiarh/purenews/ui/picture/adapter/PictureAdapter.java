package com.xiarh.purenews.ui.picture.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.AbsListAdapter;
import com.xiarh.purenews.bean.PictureBean;
import com.xiarh.purenews.util.ImageLoader;

/**
 * Created by xiarh on 2017/5/23.
 */

public class PictureAdapter extends AbsListAdapter<PictureBean> {

    public PictureAdapter() {
        super(R.layout.item_picture);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PictureBean item) {
        ImageLoader.getInstance().with(mContext, item.getThumbLargeUrl(), (ImageView) baseViewHolder.getView(R.id.img_picture));
    }
}
