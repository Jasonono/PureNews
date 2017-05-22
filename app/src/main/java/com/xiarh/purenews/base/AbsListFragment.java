package com.xiarh.purenews.base;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiarh.purenews.R;
import com.xiarh.purenews.bean.NewsBean;
import com.xiarh.purenews.util.WebUtil;

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

    protected String mID;

    protected AbsListAdapter<T> mAdapter;

    protected int mIndex = 0;

    protected int mDelayMillis = 500;

    protected abstract AbsListAdapter<T> getAdapter();

    protected abstract void onClick(T t, int position);

    protected abstract void getData(String id, int index);

    @Override
    protected int getLayoutId() {
        return R.layout.frg_base;
    }

    @Override
    protected void init() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = getAdapter();
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                T t = (T) adapter.getData().get(position);
                onClick(t, position);
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
        getData(mID, mIndex);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mIndex = 0;
        getData(mID, mIndex);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        mIndex = mIndex + 10;
        getData(mID, mIndex);
    }

    /**
     * @param tList
     * @param loadCode
     */
    protected final void onDataSuccessReceived(List<T> tList, int loadCode) {
        switch (loadCode) {
            case LOADFAIL:
                //加载失败
                mAdapter.loadMoreFail();
                break;
            case LOADSUCCESS:
                if (mIndex == 0) {
                    mAdapter.setNewData(tList);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }, mDelayMillis);
                } else {
                    mAdapter.addData(tList);
                    //加载完成
                    mAdapter.loadMoreComplete();
                }
                break;
            case LOADNOMORE:
                //全部加载完成
                mAdapter.loadMoreEnd();
                break;
        }
    }

    public static final int LOADFAIL = 0000;//加载失败

    public static final int LOADSUCCESS = 0001;//加载成功

    public static final int LOADNOMORE = 0002;//没有更多数据

}