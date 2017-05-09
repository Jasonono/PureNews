package com.xiarh.purenews.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by xiarh on 2017/5/9.
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.news_viewpager)
    ViewPager mViewPager;

    private String type;

    public static NewsFragment newInstance(String type) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getString("type");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_news;
    }

    @Override
    protected void init() {

    }
}
