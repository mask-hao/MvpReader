package com.zhanghao.reader.ui.activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.StartImgBean;
import com.zhanghao.reader.contract.StartImgContract;
import com.zhanghao.reader.presenter.StartImgPresenterImpl;
import com.zhanghao.reader.utils.ActivityUtil;
import com.zhanghao.reader.utils.NetWorkUtil;
import com.zhanghao.reader.utils.SpUtil;
import com.zhanghao.reader.utils.TimeUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/12/9.
 */

public class SplashActivity extends AppCompatActivity implements StartImgContract.View {
    private static final String TAG = "SplashActivity";
    @BindView(R.id.zhihu_start_img)
    ImageView startImg;
    @BindView(R.id.zhihu_start_tv)
    TextView startTv;

    private StartImgContract.Presenter presenter;
    private AlphaAnimation alphaAnimation;



    private final static String FILE_NAME = "setting";
    private final static String URL_NAME = "start_url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

//        if (Build.VERSION.SDK_INT>=21){
//            View decorView=getWindow().getDecorView();
//            int option=View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        ButterKnife.bind(this);
        SpUtil.init(this, FILE_NAME);



        initView();
        initData();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initView() {
//        StatusBarUtil.setTransparent(this);
        alphaAnimation=new AlphaAnimation(1.0f,1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setDuration(1000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                goToMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void initData() {



        presenter = new StartImgPresenterImpl(this);

        String savedUrl = SpUtil.getSavedString(URL_NAME);

        if (NetWorkUtil.isNetWorkAvailable(this))
        {
            setUpStartImage(savedUrl);
            presenter.getStartImg();
        }else{
            if ("".equals(savedUrl) || null == savedUrl)
                goToMainActivity();
            else
                setUpStartImage(savedUrl);
        }

    }

    @Override
    public void setUpStartImg(StartImgBean zhiHuStartImgBean) {

        if (zhiHuStartImgBean==null)
            goToMainActivity();
        else {
            String url = zhiHuStartImgBean.getImg();
            Log.d(TAG, "setUpStartImg: "+url);
            if (!url.equals(SpUtil.getSavedString(URL_NAME))){
                SpUtil.saveString(URL_NAME, url);
            }

//            setUpStartImage(url);
        }
        startTv.setText(getDateText());
        startImg.startAnimation(alphaAnimation);
    }


    private void setUpStartImage(String url) {
        Picasso.with(this)
                .load(url)
                .into(startImg);
//        startImg.setAlpha(0.5f);
    }


    private void goToMainActivity() {
        ActivityUtil.toMainActivity(this);
        finish();
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showError(Throwable e) {
        goToMainActivity();
    }

    @Override
    public void setPresenter(StartImgContract.Presenter presenter) {
        this.presenter = presenter;
    }


    private String getDateText() {
        return TimeUtils.getCurrentDate("YYYY-MM-dd");
    }
}
