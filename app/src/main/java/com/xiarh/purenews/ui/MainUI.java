package com.xiarh.purenews.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseActivity;
import com.xiarh.purenews.config.BaseApplication;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.ui.center.PersonCenterFragment;
import com.xiarh.purenews.ui.news.fragment.NewsHomeFragment;
import com.xiarh.purenews.ui.video.fragment.VideoHomeFragment;
import com.xiarh.purenews.ui.weather.fragment.WeatherFragment;
import com.xiarh.purenews.util.SnackBarUtil;

import butterknife.BindView;
import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.UpdateKey;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainUI extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigation;
    @BindView(R.id.content)
    FrameLayout mContent;

    private NewsHomeFragment mNewsHomeFragment;

    private VideoHomeFragment mVideoHomeFragment;

    private WeatherFragment mWeatherFragment;

    private PersonCenterFragment mCenterFragment;

    // 记录第一次点击的时间
    private long clickTime = 0;

    // 记录item位置
    private int currentPosition = 0;

    private static final String ITEM_POSITION = "item_position";

    private String[] TAGS = {"新闻", "视频", "天气", "我的"};

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 此处填上在http://fir.im/注册账号后获得的API_TOKEN以及APP的应用ID
        UpdateKey.API_TOKEN = Config.API_FIRE_TOKEN;
        UpdateKey.APP_ID = Config.APP_FIRE_ID;
        // UpdateKey.DialogOrNotification = UpdateKey.WITH_DIALOG;//通过Dialog来进行下载
        UpdateKey.DialogOrNotification = UpdateKey.WITH_NOTIFITION;//通过通知栏来进行下载(默认)
        UpdateFunGO.init(this);
        initBottomNavigation();
        initFragment(savedInstanceState);
    }

    /**
     * 初始化碎片
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mNewsHomeFragment = (NewsHomeFragment) getSupportFragmentManager().findFragmentByTag(TAGS[0]);
            mVideoHomeFragment = (VideoHomeFragment) getSupportFragmentManager().findFragmentByTag(TAGS[1]);
            mWeatherFragment = (WeatherFragment) getSupportFragmentManager().findFragmentByTag(TAGS[2]);
            mCenterFragment = (PersonCenterFragment) getSupportFragmentManager().findFragmentByTag(TAGS[3]);
            currentPosition = savedInstanceState.getInt(ITEM_POSITION);
        } else {
            currentPosition = 0;
        }
        switchFragment(currentPosition);
        mBottomNavigation.setCurrentItem(currentPosition);
    }

    /**
     * 初始化底部导航
     */
    private void initBottomNavigation() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.news, R.drawable.ic_new, R.color.black);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.video, R.drawable.ic_video, R.color.black);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.weather, R.drawable.ic_weather, R.color.black);
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
                switchFragment(position);
                return true;
            }
        });
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
                    ft.add(R.id.content, mNewsHomeFragment, TAGS[0]);
                }
                break;
            case 1:
                if (null != mVideoHomeFragment) {
                    ft.show(mVideoHomeFragment);
                } else {
                    mVideoHomeFragment = new VideoHomeFragment();
                    ft.add(R.id.content, mVideoHomeFragment, TAGS[1]);
                }
                break;
            case 2:
                if (null != mWeatherFragment) {
                    ft.show(mWeatherFragment);
                } else {
                    mWeatherFragment = new WeatherFragment();
                    ft.add(R.id.content, mWeatherFragment, TAGS[2]);
                }
                break;

            case 3:
                if (null != mCenterFragment) {
                    ft.show(mCenterFragment);
                } else {
                    mCenterFragment = new PersonCenterFragment();
                    ft.add(R.id.content, mCenterFragment, TAGS[3]);
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
        if (null != mWeatherFragment)
            ft.hide(mWeatherFragment);
        if (null != mCenterFragment)
            ft.hide(mCenterFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 奔溃前保存位置
        outState.putInt(ITEM_POSITION, mBottomNavigation.getCurrentItem());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 监听全屏视频时返回键
            if (JCVideoPlayer.backPress()) {
                clickTime = 0;
            } else {
                if ((System.currentTimeMillis() - clickTime) > 2000) {
                    SnackBarUtil.showSnackBar(R.string.exit, mContent, MainUI.this);
                    clickTime = System.currentTimeMillis();
                } else {
                    BaseApplication.getInstance().exitApp();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }
}
