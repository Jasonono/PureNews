package com.xiarh.purenews.ui.news.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiarh.purenews.R;
import com.xiarh.purenews.bean.NewsBean;
import com.xiarh.purenews.util.ImageLoader;

/**
 * Created by xiarh on 2017/5/10.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {

    public NewsAdapter() {
        super(R.layout.item_news);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean item) {
        baseViewHolder.setText(R.id.tv_news, item.getTitle());
        ImageLoader.getInstance().with(mContext, item.getPicUrl(), (ImageView) baseViewHolder.getView(R.id.img_news));
    }
}
