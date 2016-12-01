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
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zhanghao on 2016/11/27.
 */

public class PhotoActivity extends BaseActivity{
    private static final String TAG = "PhotoActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.photo_iv)
    ImageView photoIv;
    private String url;
    PhotoViewAttacher photoViewAttacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_display);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        setUpToolBar("",toolbar,true,true);
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
