package com.xiarh.purenews.ui.news.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;
import com.xiarh.purenews.config.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 新闻
 * Created by xiarh on 2017/5/9.
 */

public class NewsHomeFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.news_home_viewpager_)
    ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"社会新闻", "国内新闻", "国际新闻", "娱乐新闻", "体育新闻", "NBA新闻", "足球新闻", "科技新闻"
            , "创业新闻", "苹果新闻", "军事新闻", "移动互联", "旅游资讯", "健康知识", "奇闻异事", "美女图片", "VR科技", "IT资讯"};

    private TitleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_news_home;
    }

    @Override
    protected void init() {
        mFragments.add(NewsFragment.newInstance("social"));
        mFragments.add(NewsFragment.newInstance("guonei"));
        mFragments.add(NewsFragment.newInstance("world"));
        mFragments.add(NewsFragment.newInstance("huabian"));
        mFragments.add(NewsFragment.newInstance("tiyu"));
        mFragments.add(NewsFragment.newInstance("nba"));
        mFragments.add(NewsFragment.newInstance("football"));
        mFragments.add(NewsFragment.newInstance("keji"));
        mFragments.add(NewsFragment.newInstance("startup"));
        mFragments.add(NewsFragment.newInstance("apple"));
        mFragments.add(NewsFragment.newInstance("military"));
        mFragments.add(NewsFragment.newInstance("mobile"));
        mFragments.add(NewsFragment.newInstance("travel"));
        mFragments.add(NewsFragment.newInstance("health"));
        mFragments.add(NewsFragment.newInstance("qiwen"));
        mFragments.add(NewsFragment.newInstance("meinv"));
        mFragments.add(NewsFragment.newInstance("vr"));
        mFragments.add(NewsFragment.newInstance("it"));
        mAdapter = new TitleAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class TitleAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        private String[] titles;

        public TitleAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
