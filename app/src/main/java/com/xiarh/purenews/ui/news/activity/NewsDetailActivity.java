package com.xiarh.purenews.ui.news.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseActivity;
import com.xiarh.purenews.bean.NewsDetailBean;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.util.ListUtils;
import com.xiarh.purenews.util.SnackBarUtil;

import java.io.InputStream;
import java.net.URL;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by xiarh on 2017/5/25.
 */

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.news_detail_body)
    TextView tvDetail;

    private String postId;

    private String title;

    private NewsDetailBean bean;

    @Override
    protected int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void init() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_toolbar_back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        postId = getIntent().getStringExtra("postid");
        title = getIntent().getStringExtra("title");
        tvTitle.setText(title);
        getDetail();
    }

    public void getDetail() {
        OkGo.get(Config.getNewsDetailUrl(postId))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            Gson gson = new Gson();
                            JsonParser parser = new JsonParser();
                            JsonObject jsonObj = parser.parse(s).getAsJsonObject();
                            JsonElement jsonElement = jsonObj.get(postId);
                            if (null == jsonElement) {
                                SnackBarUtil.showSnackBar(R.string.loadfail, toolbar, NewsDetailActivity.this);
                            } else {
                                JsonObject jsonObject = jsonElement.getAsJsonObject();
                                bean = gson.fromJson(jsonObject, NewsDetailBean.class);
                                setDetail(bean);
                            }
                        } catch (Exception e) {
                            SnackBarUtil.showSnackBar(R.string.loadfail, toolbar, NewsDetailActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        SnackBarUtil.showSnackBar(e.getMessage(), toolbar, NewsDetailActivity.this);
                    }
                });
    }

    public void setDetail(NewsDetailBean bean) {
        if (handleRichTextWithImg(bean)) {
            tvDetail.setText(Html.fromHtml(bean.getBody(), imageGetter, null));
        }
    }

    Html.ImageGetter imageGetter = new Html.ImageGetter() {
        Drawable drawable = null;

        @Override
        public Drawable getDrawable(String source) {
            //加载中提示图片
            drawable = getResources().getDrawable(R.drawable.icon_news);
            try {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return drawable;
        }
    };

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 处理富文本包含图片的情况
     *
     * @param newsDetailBean 原始数据
     */
    private Boolean handleRichTextWithImg(NewsDetailBean newsDetailBean) {
        Boolean isSuccess = false;
        if (!ListUtils.isEmpty(newsDetailBean.getImg())) {
            String body = newsDetailBean.getBody();
            for (NewsDetailBean.ImgBean imgEntity : newsDetailBean.getImg()) {
                String ref = imgEntity.getRef();
                String src = imgEntity.getSrc();
                String img = HTML_IMG_TEMPLATE.replace("http", src);
                body = body.replaceAll(ref, img);
            }
            newsDetailBean.setBody(body);
            isSuccess = true;
        }
        Log.i("123456", newsDetailBean.getBody());
        return isSuccess;
    }

    private static final String HTML_IMG_TEMPLATE = "<img src=\"http\" />";
}
