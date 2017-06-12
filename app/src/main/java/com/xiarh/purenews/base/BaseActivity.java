package com.xiarh.purenews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.xiarh.purenews.config.BaseApplication;

import butterknife.ButterKnife;

/**
 * Activity基类
 * Created by xiarh on 2017/5/8.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayout();

    protected abstract void init(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        init(savedInstanceState);
        BaseApplication.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getInstance().removeActivity(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 回退
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}