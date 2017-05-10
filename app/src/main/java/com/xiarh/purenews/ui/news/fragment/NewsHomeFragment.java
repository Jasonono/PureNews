package com.xiarh.purenews.ui.news.fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
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

    private String[] mTitles = {"军事", "体育", "科技", "教育", "娱乐", "财经", "旅游", "女人"};

    private TitleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_news_home;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void init() {
        mFragments.add(NewsFragment.newInstance(Config.NEWS_WAR));
        mFragments.add(NewsFragment.newInstance(Config.NEWS_SPORT));
        mFragments.add(NewsFragment.newInstance(Config.NEWS_TECH));
        mFragments.add(NewsFragment.newInstance(Config.NEWS_EDU));
        mFragments.add(NewsFragment.newInstance(Config.NEWS_ENT));
        mFragments.add(NewsFragment.newInstance(Config.NEWS_MONEY));
        mFragments.add(NewsFragment.newInstance(Config.NEWS_TRAVEL));
        mFragments.add(NewsFragment.newInstance(Config.NEWS_LADY));
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
