package com.xiarh.purenews.ui.picture.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xiarh.purenews.base.AbsListAdapter;
import com.xiarh.purenews.base.AbsListFragment;
import com.xiarh.purenews.bean.PictureBean;
import com.xiarh.purenews.bean.PictureResponse;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.ui.picture.adapter.PictureAdapter;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by xiarh on 2017/5/23.
 */

public class PictureFragment extends AbsListFragment<PictureBean> {

    public static PictureFragment newInstance(String type) {
        PictureFragment pictureFragment = new PictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mID", type);
        pictureFragment.setArguments(bundle);
        return pictureFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mID = args.getString("mID");
        }
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected AbsListAdapter<PictureBean> getAdapter() {
        return new PictureAdapter();
    }

    @Override
    protected void onClick(PictureBean pictureBean, int position) {

    }

    @Override
    protected void getData(String id, int index) {
        OkGo.get(Config.getPictureUrl(id, index))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        PictureResponse pictureResponse = gson.fromJson(s, PictureResponse.class);
                        List<PictureBean> beans = pictureResponse.getImgs();
                        if (beans.isEmpty()) {
                            onDataSuccessReceived(null, LOADNOMORE);
                        } else {
                            onDataSuccessReceived(beans, LOADSUCCESS);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        onDataSuccessReceived(null, LOADFAIL);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }
}
