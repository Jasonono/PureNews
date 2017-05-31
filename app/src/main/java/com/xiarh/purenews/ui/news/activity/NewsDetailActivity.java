package com.xiarh.purenews.ui.news.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.xiarh.purenews.util.WebUtil;
import com.zzhoujay.richtext.RichText;

import java.lang.reflect.Method;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by xiarh on 2017/5/25.
 */

public class NewsDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.news_detail_body)
    TextView tvDetail;
    @BindView(R.id.swipefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private String postId;

    private String title;

    private NewsDetailBean bean;

    @Override
    protected int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void init() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black));
        swipeRefreshLayout.setOnRefreshListener(this);
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
                        if (null != swipeRefreshLayout) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        SnackBarUtil.showSnackBar(e.getMessage(), toolbar, NewsDetailActivity.this);
                        if (null != swipeRefreshLayout) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
    }

    public void setDetail(NewsDetailBean bean) {
        RichText.from(handleRichText(bean))
                .placeHolder(R.drawable.ic_default)
                .into(tvDetail);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_webview:
                WebUtil.openWeb(NewsDetailActivity.this, bean.getTitle(), bean.getShareLink());
                break;
            case R.id.item_browser:
                Uri uri = Uri.parse(bean.getShareLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        setIconEnable(menu, true);//让在overflow中的menuitem的icon显示
        return super.onPrepareOptionsPanel(view, menu);
    }

    /**
     * 利用反射机制调用MenuBuilder中的setOptionIconsVisable（），
     * 如果是集成自AppCompatActivity则不行,需要在onPreareOptionPanel（）中调用该方法
     *
     * @param menu   该menu实质为MenuBuilder，该类实现了Menu接口
     * @param enable enable为true时，菜单添加图标有效，enable为false时无效。因为4.0系统之后默认无效
     */
    private void setIconEnable(Menu menu, boolean enable) {
        if (menu != null) {
            try {
                Class clazz = menu.getClass();
                if (clazz.getSimpleName().equals("MenuBuilder")) {
                    Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
                    m.invoke(menu, enable);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理富文本包含图片的情况
     *
     * @param newsDetailBean 原始数据
     */
    private String handleRichText(NewsDetailBean newsDetailBean) {
        String detailBody;
        if (!ListUtils.isEmpty(newsDetailBean.getImg())) {
            String body = newsDetailBean.getBody();
            for (NewsDetailBean.ImgBean imgEntity : newsDetailBean.getImg()) {
                String ref = imgEntity.getRef();
                String src = imgEntity.getSrc();
                String img = HTML_IMG_TEMPLATE.replace("http", src);
                body = body.replaceAll(ref, img);
            }
            detailBody = body;
        } else {
            detailBody = newsDetailBean.getBody();
        }
        return detailBody;
    }

    private static final String HTML_IMG_TEMPLATE = "<img src=\"http\" />";

    @Override
    public void onRefresh() {
        getDetail();
    }
}
