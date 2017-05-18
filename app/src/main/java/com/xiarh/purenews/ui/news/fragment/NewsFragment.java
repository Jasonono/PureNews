package com.xiarh.purenews.ui.news.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;
import com.xiarh.purenews.bean.NewsBean;
import com.xiarh.purenews.bean.NewsResponse;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.ui.news.adapter.NewsAdapter;
import com.xiarh.purenews.util.WebUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by xiarh on 2017/5/9.
 */

public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swipefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview_news)
    RecyclerView mRecyclerView;

    private String mType;

    private NewsAdapter mAdapter;

    private int page = 1;

    private int page_size = 10;

    private int delayMillis = 500;

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
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = new NewsAdapter();
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsBean bean = (NewsBean) adapter.getData().get(position);
                WebUtil.openWeb(getActivity(), bean.getTitle(), bean.getUrl());
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    getNewsData(page, true);
                }
            }
        }, delayMillis);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        page = 1;
        getNewsData(page, true);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        page++;
        getNewsData(page, false);
    }

    /**
     * 加载数据
     *
     * @param page      页数
     * @param isRefresh true 下拉刷新 false 上拉加载
     */
    private void getNewsData(int page, final boolean isRefresh) {
        OkHttpUtils
                .get()
                .url(Config.NEWS_URL + mType + "/")
                .addParams("page", page + "")
                .addParams("num", page_size + "")
                .addParams("key", Config.KEY)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.normal(getActivity(), e.getMessage()).show();
                        if (!isRefresh) {
                            //加载失败
                            mAdapter.loadMoreFail();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        NewsResponse newsResponse = gson.fromJson(response, NewsResponse.class);
                        if (null != newsResponse) {
                            if (isRefresh) {
                                if (newsResponse.getCode() == 200) {
                                    mAdapter.setNewData(newsResponse.getNewslist());
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing()) {
                                                mSwipeRefreshLayout.setRefreshing(false);
                                            }
                                        }
                                    }, delayMillis);
                                }
                            } else {
                                if (newsResponse.getCode() == 200) {
                                    mAdapter.addData(newsResponse.getNewslist());
                                    //加载完成
                                    mAdapter.loadMoreComplete();
                                } else {
                                    //全部加载完毕
                                    mAdapter.loadMoreEnd();
                                }
                            }
                        }
                    }
                });
    }
}