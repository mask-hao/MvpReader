package com.zhanghao.reader.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.ZhiHuStartImgBean;
import com.zhanghao.reader.contract.ZhiHuStartContract;
import com.zhanghao.reader.presenter.ZhiHuStartImgPresenterImpl;
import com.zhanghao.reader.utils.ActivityUtil;
import com.zhanghao.reader.utils.NetWorkUtil;
import com.zhanghao.reader.utils.SpUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/12/9.
 */

public class SplashActivity extends AppCompatActivity implements ZhiHuStartContract.View {
    private static final String TAG = "SplashActivity";
    @BindView(R.id.zhihu_start_img)
    ImageView zhihuStartImg;
    @BindView(R.id.zhihu_start_tv)
    TextView zhihuStartTv;

    private ZhiHuStartContract.Presenter presenter;
    private ScaleAnimation scaleAnimation;

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
        initView();
        initData();
    }

    private void initView() {
//        StatusBarUtil.setTransparent(this);
        scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
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

        SpUtil.init(this, FILE_NAME);

        presenter = new ZhiHuStartImgPresenterImpl(this);
        if (NetWorkUtil.isNetWorkAvailable(this)) presenter.getStartZhiHuStartImg();
        else {
            String savedUrl = SpUtil.getSavedString(URL_NAME);
            if ("".equals(savedUrl) || null == savedUrl)
                goToMainActivity();
            else
                setUpStartImage(savedUrl);
        }
    }

    @Override
    public void setUpStartImg(ZhiHuStartImgBean zhiHuStartImgBean) {

        String url = zhiHuStartImgBean.getImg();


        SpUtil.saveString(URL_NAME, url);

        setUpStartImage(url);
        zhihuStartTv.setText(zhiHuStartImgBean.getText());
    }


    private void setUpStartImage(String url) {
        Picasso.with(this)
                .load(url)
                .into(zhihuStartImg);
        zhihuStartImg.startAnimation(scaleAnimation);

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
    public void setPresenter(ZhiHuStartContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
