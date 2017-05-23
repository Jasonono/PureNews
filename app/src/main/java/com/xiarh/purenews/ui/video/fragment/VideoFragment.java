package com.xiarh.purenews.ui.video.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xiarh.purenews.base.AbsListAdapter;
import com.xiarh.purenews.base.AbsListFragment;
import com.xiarh.purenews.bean.VideoBean;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.ui.video.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 视频
 * Created by xiarh on 2017/5/22.
 */

public class VideoFragment extends AbsListFragment<VideoBean> {

    public static VideoFragment newInstance(String type) {
        VideoFragment videoFragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mID", type);
        videoFragment.setArguments(bundle);
        return videoFragment;
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
    protected AbsListAdapter<VideoBean> getAdapter() {
        return new VideoAdapter();
    }

    @Override
    protected void onClick(VideoBean videoBean, int position) {

    }

    @Override
    protected void getData(final String id, int index) {
        OkGo.get(Config.getVideoUrl(id, index))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            List<VideoBean> beans = new ArrayList<>();
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
                                        VideoBean bean = gson.fromJson(jo, VideoBean.class);
                                        beans.add(bean);
                                    }
                                    onDataSuccessReceived(beans, LOADSUCCESS);
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
