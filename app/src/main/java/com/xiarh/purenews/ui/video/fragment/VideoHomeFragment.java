package com.xiarh.purenews.ui.video.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;
import com.xiarh.purenews.base.TitleAdapter;
import com.xiarh.purenews.config.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 视频主页
 * Created by xiarh on 2017/5/22.
 */

public class VideoHomeFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.news_home_viewpager_)
    ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"精品视频", "娱乐视频", "搞笑视频", "热点视频"};

    private TitleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_video;
    }

    @Override
    protected void init() {
        mFragments.add(VideoFragment.newInstance(Config.VIDEO_CHOICE_ID));
        mFragments.add(VideoFragment.newInstance(Config.VIDEO_ENTERTAINMENT_ID));
        mFragments.add(VideoFragment.newInstance(Config.VIDEO_FUN_ID));
        mFragments.add(VideoFragment.newInstance(Config.VIDEO_HOT_ID));
        mAdapter = new TitleAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
