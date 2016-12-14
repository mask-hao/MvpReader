package com.zhanghao.reader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.contract.GankDailyContract;
import com.zhanghao.reader.presenter.GankDailyPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zhanghao on 2016/11/27.
 */

public class PhotoActivity extends BaseActivity{
    private static final String TAG = "PhotoActivity";
    @BindView(R.id.photo_dis_iv)
    PhotoView photoIv;
    private String url;
    PhotoViewAttacher photoViewAttacher;

    @Override
    protected int setContentLayout() {
        return R.layout.photo_display;
    }

    @Override
    protected boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
        initView();
        setTitle("");
    }

    private void initView() {
        Picasso.with(this).load(url).into(photoIv);
        setupPhotoAttacher();
    }

    private void setupPhotoAttacher() {
        photoViewAttacher=new PhotoViewAttacher(photoIv);
        photoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {

            }
        });
    }

    private void initData() {
        Intent intent=getIntent();
        url=intent.getStringExtra("url");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoViewAttacher.cleanup();
    }
}
