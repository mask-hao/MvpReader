package com.zhanghao.reader.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.contract.GankDailyContract;
import com.zhanghao.reader.presenter.GankDailyPresenterImpl;
import com.zhanghao.reader.utils.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zhanghao on 2016/11/27.
 */

public class PhotoActivity extends BaseActivity{
    private static final String TAG = "PhotoActivity";
    @BindView(R.id.photo_dis_iv)
    PhotoView photoIv;
    private Bitmap mBitmap;
    private String url;
    private String date;
    PhotoViewAttacher photoViewAttacher;
    public static final String TRANSIT_PIC = "picture";
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
        ViewCompat.setTransitionName(photoIv,TRANSIT_PIC);
        initView();
        setTitle(date);
    }

    private void initView() {
        Picasso.with(this)
                .load(url)
                .into(photoIv);
        setupPhotoAttacher();
    }


    private Observable<Bitmap> getBitmap(){
    return   Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap=null;
                try {
                    bitmap=Picasso.with(PhotoActivity.this).load(url).get();
                    if (bitmap!=null) subscriber.onNext(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(new Exception("无法下载图片"));
                    finish();
                }
            }
        }).flatMap(new Func1<Bitmap, Observable<Bitmap>>() {
            @Override
            public Observable<Bitmap> call(Bitmap bitmap) {
                File appPath=new File(Environment.getExternalStorageDirectory(),"Reader");
                if (!appPath.exists()) appPath.mkdir();
                String fileName=date.replace("/","-")+".jpg";
                File file=new File(appPath,fileName);
                try {
                    FileOutputStream outputStream=new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return Observable.just(bitmap);
            }
        }).subscribeOn(Schedulers.newThread());
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
        date=intent.getStringExtra("date");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoViewAttacher.cleanup();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch (id){
            case R.id.photo_save:
                savePhoto();
                break;
            case R.id.photo_share:
                beginShare(null,null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void savePhoto() {
        initPermission(COMMON_PERMISSIONS);
        getBitmap().observeOn(AndroidSchedulers.mainThread());

    }
}
