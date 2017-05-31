package com.xiarh.purenews.ui.weather.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiarh.purenews.R;
import com.xiarh.purenews.bean.WeatherListBean;


/**
 * Created by xiarh on 2017/5/31.
 */

public class WeatherAdapter extends BaseQuickAdapter<WeatherListBean, BaseViewHolder> {

    public WeatherAdapter() {
        super(R.layout.item_weather);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WeatherListBean item) {
        baseViewHolder.setText(R.id.tv_weather_type, item.getType());
        baseViewHolder.setText(R.id.tv_weather_brf, item.getBrf());
        baseViewHolder.setImageResource(R.id.img_weather_item, item.getResId());
    }
}
