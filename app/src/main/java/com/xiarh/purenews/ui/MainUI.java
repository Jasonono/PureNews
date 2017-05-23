package com.xiarh.purenews.ui;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseActivity;
import com.xiarh.purenews.ui.center.PersonCenterFragment;
import com.xiarh.purenews.ui.news.fragment.NewsHomeFragment;
import com.xiarh.purenews.ui.picture.fragment.PictureHomeFragment;
import com.xiarh.purenews.ui.video.fragment.VideoHomeFragment;

import butterknife.BindView;

public class MainUI extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigation;

    private NewsHomeFragment mNewsHomeFragment;
    private VideoHomeFragment mVideoHomeFragment;
    private PictureHomeFragment mPictureHomeFragment;
    private PersonCenterFragment mCenterFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.news, R.drawable.ic_new, R.color.black);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.video, R.drawable.ic_video, R.color.black);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.picture, R.drawable.ic_picture, R.color.black);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.person_center, R.drawable.ic_person_center, R.color.black);
        mBottomNavigation.addItem(item1);
        mBottomNavigation.addItem(item2);
        mBottomNavigation.addItem(item3);
        mBottomNavigation.addItem(item4);
        mBottomNavigation.setAccentColor(Color.parseColor("#000000"));
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        switchFragment(0);
                        break;
                    case 1:
                        switchFragment(1);
                        break;
                    case 2:
                        switchFragment(2);
                        break;
                    case 3:
                        switchFragment(3);
                        break;
                }
                return true;
            }
        });
        mBottomNavigation.setCurrentItem(0);
    }

    /**
     * 切换Fragment
     *
     * @param position
     */
    private void switchFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //首先隐藏所有的fragment,避免重叠
        hideFragments(ft);
        switch (position) {
            case 0:
                if (null != mNewsHomeFragment) {
                    ft.show(mNewsHomeFragment);
                } else {
                    mNewsHomeFragment = new NewsHomeFragment();
                    ft.add(R.id.content, mNewsHomeFragment, "新闻");
                }
                break;
            case 1:
                if (null != mVideoHomeFragment) {
                    ft.show(mVideoHomeFragment);
                } else {
                    mVideoHomeFragment = new VideoHomeFragment();
                    ft.add(R.id.content, mVideoHomeFragment, "视频");
                }
                break;
            case 2:
                if (null != mPictureHomeFragment) {
                    ft.show(mPictureHomeFragment);
                } else {
                    mPictureHomeFragment = new PictureHomeFragment();
                    ft.add(R.id.content, mPictureHomeFragment, "图片");
                }
                break;

            case 3:
                if (null != mCenterFragment) {
                    ft.show(mCenterFragment);
                } else {
                    mCenterFragment = new PersonCenterFragment();
                    ft.add(R.id.content, mCenterFragment, "我的");
                }
                break;
        }
        ft.commit();
    }

    /**
     * 当fragment已经被实例化，就隐藏起来
     *
     * @param ft
     */
    private void hideFragments(FragmentTransaction ft) {
        if (null != mNewsHomeFragment)
            ft.hide(mNewsHomeFragment);
        if (null != mVideoHomeFragment)
            ft.hide(mVideoHomeFragment);
        if (null != mPictureHomeFragment)
            ft.hide(mPictureHomeFragment);
        if (null != mCenterFragment)
            ft.hide(mCenterFragment);
    }
}
