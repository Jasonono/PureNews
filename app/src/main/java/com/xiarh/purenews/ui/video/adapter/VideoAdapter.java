package com.xiarh.purenews.ui.video.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.AbsListAdapter;
import com.xiarh.purenews.bean.VideoBean;
import com.xiarh.purenews.util.ImageLoader;

/**
 * Created by xiarh on 2017/5/22.
 */

public class VideoAdapter extends AbsListAdapter<VideoBean> {

    public VideoAdapter() {
        super(R.layout.item_video);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, VideoBean item) {
        baseViewHolder.setText(R.id.tv_video_source, item.getTopicName());
        baseViewHolder.setText(R.id.tv_video_playcount, item.getPlayCount() + "次播放");
        ImageLoader.getInstance().with(mContext, item.getCover(), (ImageView) baseViewHolder.getView(R.id.img_topic));
        ImageLoader.getInstance().withRound(mContext, item.getTopicImg(), (ImageView) baseViewHolder.getView(R.id.img_video_logo));
    }
}
