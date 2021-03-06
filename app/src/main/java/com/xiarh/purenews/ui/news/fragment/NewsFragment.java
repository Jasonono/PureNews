package com.xiarh.purenews.ui.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xiarh.purenews.base.AbsListAdapter;
import com.xiarh.purenews.base.AbsListFragment;
import com.xiarh.purenews.bean.NewsBean;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.ui.news.activity.NewsDetailActivity;
import com.xiarh.purenews.ui.news.adapter.NewsAdapter;
import com.xiarh.purenews.util.SnackBarUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 新闻
 * Created by xiarh on 2017/5/9.
 */

public class NewsFragment extends AbsListFragment<NewsBean> {

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
    protected AbsListAdapter getAdapter() {
        return new NewsAdapter();
    }

    @Override
    protected void onClick(NewsBean bean, int position) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("postid", bean.getPostid());
        intent.putExtra("title", bean.getTitle());
        intent.putExtra("imgsrc", bean.getImgsrc());
        getActivity().startActivity(intent);
    }

    @Override
    protected void getData(final String id, int index) {
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
                            if (null == jsonElement) {
                                onDataSuccessReceived(null, LOADNOMORE);
                            } else {
                                JsonArray jsonArray = jsonElement.getAsJsonArray();
                                if (jsonArray.size() == 0) {
                                    onDataSuccessReceived(null, LOADNOMORE);
                                } else {
                                    for (int i = 1; i < jsonArray.size(); i++) {
                                        JsonObject jo = jsonArray.get(i).getAsJsonObject();
                                        NewsBean bean = gson.fromJson(jo, NewsBean.class);
                                        if (null != bean.getUrl_3w()) {
                                            beans.add(bean);
                                        }
                                    }
                                    onDataSuccessReceived(beans, LOADSUCCESS);
                                }
                            }
                        } catch (Exception e) {
                            SnackBarUtil.showSnackBar(e.getMessage(), mSwipeRefreshLayout, getActivity());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        SnackBarUtil.showSnackBar(e.getMessage(), mSwipeRefreshLayout, getActivity());
                        onDataSuccessReceived(null, LOADFAIL);
                    }
                });
    }

    @Override
    protected void handleScrollEvent(String id) {
        super.handleScrollEvent(id);
        if (mID.equals(id)) {
            mRecyclerView.getLayoutManager().scrollToPosition(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }
}
