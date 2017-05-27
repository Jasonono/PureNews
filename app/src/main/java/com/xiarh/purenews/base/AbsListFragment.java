package com.xiarh.purenews.base;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiarh.purenews.R;
import com.xiarh.purenews.animation.CustomAnimation;
import com.xiarh.purenews.config.Config;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xiarh on 2017/5/22.
 */

public abstract class AbsListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swipefreshlayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recyclerview_news)
    protected RecyclerView mRecyclerView;

    protected String mID = "";

    protected AbsListAdapter<T> mAdapter;

    protected int mIndex = 0;

    protected int mDelayMillis = 500;

    protected abstract AbsListAdapter<T> getAdapter();

    protected abstract void onClick(T t, int position);

    protected abstract void getData(String id, int index);

    public static final int LOADFAIL = 0000;//加载失败

    public static final int LOADSUCCESS = 0001;//加载成功

    public static final int LOADNOMORE = 0002;//没有更多数据

    @Override
    protected int getLayoutId() {
        return R.layout.frg_base;
    }

    @Override
    protected void init() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = getAdapter();
        // 添加动画
        mAdapter.openLoadAnimation(new CustomAnimation());
        mAdapter.isFirstOnly(true);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                T t = (T) adapter.getData().get(position);
                onClick(t, position);
            }
        });
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnAttachStateChangeListener(getChangeListener());
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (null != mSwipeRefreshLayout) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            }
        });
        mIndex = 0;
        getData(mID, mIndex);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        mIndex = 0;
        getData(mID, mIndex);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        getData(mID, mIndex);
    }

    @NonNull
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    protected View.OnAttachStateChangeListener getChangeListener() {
        return new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        };
    }

    /**
     * @param tList
     * @param loadCode
     */
    protected final void onDataSuccessReceived(List<T> tList, int loadCode) {
        switch (loadCode) {
            case LOADFAIL:
                mAdapter.loadMoreFail();
                mIndex = 0;
                break;
            case LOADSUCCESS:
                if (mIndex == 0) {
                    mAdapter.setNewData(tList);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (null != mSwipeRefreshLayout) {
//                                mSwipeRefreshLayout.setRefreshing(false);
//                                mAdapter.setEnableLoadMore(true);
//                            }
//                        }
//                    }, mDelayMillis);
                    mSwipeRefreshLayout.setRefreshing(false);
                    mAdapter.setEnableLoadMore(true);
                } else {
                    mAdapter.addData(tList);
                    mAdapter.loadMoreComplete();
                    mSwipeRefreshLayout.setEnabled(true);
                }
                mIndex = mIndex + 20;
                break;
            case LOADNOMORE:
                mAdapter.loadMoreEnd();
                mIndex = 0;
                break;
        }
    }
}