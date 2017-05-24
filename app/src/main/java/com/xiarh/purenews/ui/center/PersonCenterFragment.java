package com.xiarh.purenews.ui.center;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;
import com.xiarh.purenews.util.ShareUtil;
import com.xiarh.purenews.util.VersionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 * Created by xiarh on 2017/5/22.
 */

public class PersonCenterFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_center;
    }

    @Override
    protected void init() {
        tvTitle.setText(R.string.person_center);
        tvVersion.setText(getResources().getString(R.string.version) + VersionUtil.getVersionName(getActivity()));
    }

    @OnClick(R.id.tv_github)
    public void onTvGithubClicked() {
        Uri uri = Uri.parse("https://github.com/xiarunhao123/PureNews");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.tv_email)
    public void onTvEmailClicked() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "xiarunhao123@163.com", null));
        intent.putExtra(Intent.EXTRA_EMAIL, "xiarunhao123@163.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "意见反馈");
        startActivity(Intent.createChooser(intent, "意见反馈"));
    }

    @OnClick(R.id.tv_update)
    public void onTvUpdateClicked() {
        Uri uri = Uri.parse("https://fir.im/hueweather");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.tv_share)
    public void onTvShareClicked() {
        ShareUtil.share(getActivity(), "分享", "https://fir.im/hueweather");
    }
}
