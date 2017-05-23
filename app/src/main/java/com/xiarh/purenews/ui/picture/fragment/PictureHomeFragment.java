package com.xiarh.purenews.ui.picture.fragment;

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
 * 图片
 * Created by xiarh on 2017/5/22.
 */

public class PictureHomeFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.news_home_viewpager_)
    ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"美女", "动漫", "摄影"};

    private TitleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_picture;
    }

    @Override
    protected void init() {
        mFragments.add(PictureFragment.newInstance(Config.BEAUTY_ID));
        mFragments.add(PictureFragment.newInstance(Config.COMIC_ID));
        mFragments.add(PictureFragment.newInstance(Config.PHOTPGRAPHY_ID));
        mAdapter = new TitleAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
