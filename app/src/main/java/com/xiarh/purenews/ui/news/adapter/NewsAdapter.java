package com.xiarh.purenews.ui.news.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.AbsListAdapter;
import com.xiarh.purenews.bean.NewsBean;
import com.xiarh.purenews.util.ImageLoader;

/**
 * Created by xiarh on 2017/5/10.
 */

public class NewsAdapter extends AbsListAdapter<NewsBean> {

    public NewsAdapter() {
        super(R.layout.item_news);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean item) {
        baseViewHolder.setText(R.id.tv_news_title, item.getTitle());
        baseViewHolder.setText(R.id.tv_news_source, item.getSource());
        baseViewHolder.setText(R.id.tv_news_date, item.getLmodify().substring(0, 10));
        ImageLoader.getInstance().with(mContext, item.getImgsrc(), (ImageView) baseViewHolder.getView(R.id.img_news));
    }
}
