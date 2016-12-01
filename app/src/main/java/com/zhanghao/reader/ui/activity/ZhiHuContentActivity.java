package com.zhanghao.reader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.ZhiHuContent;
import com.zhanghao.reader.contract.ZhiHuContentContract;
import com.zhanghao.reader.presenter.ZhiHuNewsContentPresenterImpl;
import com.zhanghao.reader.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/11/22.
 */

public class ZhiHuContentActivity extends BaseActivity implements ZhiHuContentContract.View {
    private static final String TAG = "ZhiHuContentActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.zhihu_content_vb)
    WebView zhihuContentWv;
    @BindView(R.id.content_top_iv)
    ImageView contentTopIv;
    @BindView(R.id.content_title_tv)
    TextView contentTitleTv;
    @BindView(R.id.content_clp)
    ContentLoadingProgressBar contentClp;
    private ZhiHuContentContract.Presenter presenter;
    private String id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_content);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        setUpToolBar("",toolbar, true, true);
        StatusBarUtil.setTransparent(this);
        WebSettings webSettings = zhihuContentWv.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadsImagesAutomatically(true);
        // zhihuContentWv.addJavascriptInterface(this,"");
    }

    private void initData() {
        id = getIntent().getStringExtra("id");
        Log.d(TAG, "initData: " + id);
        new ZhiHuNewsContentPresenterImpl(this);
        presenter.getZhiHuContent(id);
    }


    @Override
    public void setUpZhiHuContent(ZhiHuContent zhiHuContent) {
        Picasso.with(this)
                .load(zhiHuContent.getImage())
                .into(contentTopIv);
        contentTitleTv.setText(zhiHuContent.getTitle());
        loadHtml(zhiHuContent.getBody());
    }

    private void loadHtml(String body) {
        StringBuilder htmlSb = new StringBuilder("<!doctype html>\n<html><head>\n<meta charset=\"utf-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width,user-scalable=no\">");
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">\n";

        htmlSb.append(css)
                .append("</head><body className=\"\"")
                .append(" >")
                .append(body);
        htmlSb.append("</body></html>");
        String html = htmlSb.toString();

        html = html.replace("<div class=\"img-place-holder\">", "");
        Log.e("html1", html);
        Log.e("html2", html);
        zhihuContentWv.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    @Override
    public void showDialog() {
       contentClp.show();
    }

    @Override
    public void hideDialog() {
      contentClp.hide();
    }

    @Override
    public void showError(Throwable error) {
        Log.d(TAG, "showError: " + error.getMessage());
        showDia(error.getMessage());
    }

    @Override
    public void setPresenter(ZhiHuContentContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
