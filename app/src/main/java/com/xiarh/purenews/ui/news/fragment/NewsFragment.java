package com.xiarh.purenews.ui.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;
import com.xiarh.purenews.bean.NewsResponse;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.ui.news.adapter.NewsAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by xiarh on 2017/5/9.
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.recyclerview_news)
    RecyclerView mRecyclerView;

    private String mType;

    private NewsAdapter mAdapter;

    public static NewsFragment newInstance(String type) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mType", type);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mType = args.getString("mType");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_news;
    }

    @Override
    protected void init() {
        mAdapter = new NewsAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setAdapter(mAdapter);
        getNewsData();
    }

    private void getNewsData() {
        OkHttpUtils
                .get()
                .url(Config.NEWS_URL)
                .addParams("type", mType)
                .addParams("page", "1")
                .addParams("limit", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.normal(getActivity(), e.getMessage()).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        NewsResponse newsResponse = gson.fromJson(response, NewsResponse.class);
                        if (null != newsResponse) {
                            mAdapter.setNewData(newsResponse.getList());
                        }
                    }
                });
    }
}