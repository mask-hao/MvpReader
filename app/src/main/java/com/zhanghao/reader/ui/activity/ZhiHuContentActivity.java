package com.zhanghao.reader.ui.activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ZhiHuContentActivity extends BaseActivity implements ZhiHuContentContract.View, View.OnClickListener {
    private static final String TAG = "ZhiHuContentActivity";
    @BindView(R.id.zhihu_content_vb)
    WebView zhihuContentWv;
    @BindView(R.id.content_top_iv)
    ImageView contentTopIv;
    @BindView(R.id.content_title_tv)
    TextView contentTitleTv;
    @BindView(R.id.content_clp)
    ContentLoadingProgressBar contentClp;
    @BindView(R.id.content_nsl)
    NestedScrollView contentNsl;
    @BindView(R.id.share_bt)
    FloatingActionButton shareBt;
    private ZhiHuContentContract.Presenter presenter;
    private String id;
    private String title;
    private String url;


    @Override
    protected int setContentLayout() {
        return R.layout.zhihu_content;
    }

    @Override
    protected boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //setAppBarAlpha(0.7f);
        setTitle("");
        initData();
        initView();
    }

    private void initView() {
       // StatusBarUtil.setTransparent(this);
//        if (dayNightUtil.isNight()){
//            TypedValue typedValue=new TypedValue();
//            getTheme().resolveAttribute(R.attr.colorCdlBackBackground,typedValue,true);
//            zhihuContentWv.setBackgroundResource(typedValue.resourceId);
//        }

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
        shareBt.setOnClickListener(this);
        new ZhiHuNewsContentPresenterImpl(this);
        presenter.getZhiHuContent(id);
    }

    private void initData() {
        id = getIntent().getStringExtra("id");
        Log.d(TAG, "initData: " + id);

    }


    @Override
    public void setUpZhiHuContent(ZhiHuContent zhiHuContent) {
        title=zhiHuContent.getTitle();
        url=zhiHuContent.getShare_url();
        Picasso.with(this)
                .load(zhiHuContent.getImage())
                .into(contentTopIv);
        contentTitleTv.setText(zhiHuContent.getTitle());
        setTitle(zhiHuContent.getTitle());
        loadHtml(zhiHuContent.getBody());
    }

    private void loadHtml(String body) {

        StringBuilder stringBuilder=new StringBuilder("<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width,user-scalable=no\">\n"
        );

        String css="<link rel=\"stylesheet\" href=\"file:///android_asset/news_qa.min.css\" type=\"text/css\">\n";

        String nightJS="<script src=\"file:///android_asset/night.js\"></script>\n";


        if (dayNightUtil.isNight())
            stringBuilder.append(nightJS);

        stringBuilder
                .append(css)
                .append("</head>")
                .append(body)
                .append("</body></html>");

//        StringBuilder htmlSb = new StringBuilder("<!doctype html>\n<html><head>\n<meta charset=\"utf-8\">\n" +
//                "\t<meta name=\"viewport\" content=\"width=device-width,user-scalable=no\">");

        // TODO: 2016/12/13 夜间模式的调整

//        String cssDay = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">\n";
//
//        String cssNight = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/newsNight.css\" type=\"text/css\">\n";
//
//        if (dayNightUtil.isNight()){
//            htmlSb.append(cssNight);
//        }
//        if (dayNightUtil.isDay()){
//            htmlSb.append(cssDay);
//        }
//       htmlSb.append("</head><body className=\"\"")
//             .append(" >")
//             .append(body);
//       htmlSb.append("</body></html>");
//       String html = htmlSb.toString();
//
//       html = html.replace("<div class=\"img-place-holder\">", "");
//       Log.e("html1", html);
//       Log.e("html2", html);

        Log.d(TAG, "loadHtml: "+stringBuilder.toString());
       zhihuContentWv.loadDataWithBaseURL("x-data://base", stringBuilder.toString(), "text/html", "UTF-8", null);
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
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ZhiHuContentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.share_bt:
               beginShare(title,url);
                //hideOrShowToolBar();
                break;
        }
    }


}
