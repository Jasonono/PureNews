package com.xiarh.purenews.ui;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseActivity;
import com.xiarh.purenews.ui.news.fragment.NewsHomeFragment;

import butterknife.BindView;

public class MainUI extends BaseActivity {
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigation;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.news, R.drawable.ic_new, R.color.black);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.video, R.drawable.ic_video, R.color.black);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.star, R.drawable.ic_star, R.color.black);
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
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content, new NewsHomeFragment(), "首页");
                        ft.commit();
                        break;
                }
                return true;
            }
        });
    }
}
