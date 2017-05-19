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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;
import com.xiarh.purenews.bean.NewsBean;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.ui.news.adapter.NewsAdapter;
import com.xiarh.purenews.util.WebUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by xiarh on 2017/5/9.
 */

public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swipefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview_news)
    RecyclerView mRecyclerView;

    private String mID;

    private NewsAdapter mAdapter;

    private int mIndex = 0;

    private int mDelayMillis = 500;


    public static NewsFragment newInstance(String type) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mID", type);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mID = args.getString("mID");
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
                WebUtil.openWeb(getActivity(), bean.getTitle(), bean.getUrl_3w());
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != mSwipeRefreshLayout) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            }
        }, mDelayMillis);
        getNewsData(0, mID, mIndex);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mIndex = 0;
        getNewsData(0, mID, mIndex);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        mIndex = mIndex + 10;
        getNewsData(1, mID, mIndex);
    }

    /**
     * 加载数据
     *
     * @param index    页数
     * @param loadType 数据加载方式 0：下拉刷新 1：上拉加载
     * @param id
     */
    private void getNewsData(final int loadType, final String id, int index) {
        OkGo.get(Config.getNewsUrl(id, index))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            List<NewsBean> beans = new ArrayList<>();
                            Gson gson = new Gson();
                            JsonParser parser = new JsonParser();
                            JsonObject jsonObj = parser.parse(s).getAsJsonObject();
                            JsonElement jsonElement = jsonObj.get(id);
                            if (jsonElement == null) {
                                //全部加载完毕
                                mAdapter.loadMoreEnd();
                            } else {
                                JsonArray jsonArray = jsonElement.getAsJsonArray();
                                for (int i = 1; i < jsonArray.size(); i++) {
                                    JsonObject jo = jsonArray.get(i).getAsJsonObject();
                                    NewsBean bean = gson.fromJson(jo, NewsBean.class);
                                    beans.add(bean);
                                }
                                if (loadType == 0) {
                                    mAdapter.setNewData(beans);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing()) {
                                                mSwipeRefreshLayout.setRefreshing(false);
                                            }
                                        }
                                    }, mDelayMillis);
                                } else if (loadType == 1) {
                                    mAdapter.addData(beans);
                                    //加载完成
                                    mAdapter.loadMoreComplete();
                                }
                            }
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mAdapter.loadMoreFail();
                    }
                });
    }
}
