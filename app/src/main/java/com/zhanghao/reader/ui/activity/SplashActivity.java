package com.zhanghao.reader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.ZhiHuStartImgBean;
import com.zhanghao.reader.contract.ZhiHuStartContract;
import com.zhanghao.reader.presenter.ZhiHuStartImgPresenterImpl;
import com.zhanghao.reader.utils.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/12/9.
 */

public class SplashActivity extends AppCompatActivity implements ZhiHuStartContract.View {
    private static final String TAG = "SplashActivity";
    @BindView(R.id.zhihu_start_img)
    ImageView zhihuStartImg;

    private ZhiHuStartContract.Presenter presenter;
    private ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        scaleAnimation=new ScaleAnimation(1.0f,1.2f,1.0f,1.2f,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ActivityUtil.toMainActivity(SplashActivity.this);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initData() {
        presenter=new ZhiHuStartImgPresenterImpl(this);
        presenter.getStartZhiHuStartImg();
    }

    @Override
    public void setUpStartImg(ZhiHuStartImgBean zhiHuStartImgBean) {
        Log.d(TAG, "setUpStartImg: "+zhiHuStartImgBean.getImg());
        Picasso.with(this)
                .load(zhiHuStartImgBean.getImg())
                .into(zhihuStartImg);
        zhihuStartImg.startAnimation(scaleAnimation);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void setPresenter(ZhiHuStartContract.Presenter presenter) {
        this.presenter=presenter;
    }
}
