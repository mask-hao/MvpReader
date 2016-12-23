package com.zhanghao.reader.ui.activity;
import android.Manifest;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.zhanghao.reader.R;
import com.zhanghao.reader.contract.BaseView;
import com.zhanghao.reader.presenter.BasePresenter;
import com.zhanghao.reader.presenter.BasePresenterImpl;
import com.zhanghao.reader.utils.ActivityUtil;
import com.zhanghao.reader.utils.DayNightUtil;
import com.zhanghao.reader.utils.PermissionUtil;
import com.zhanghao.reader.utils.StatusBarUtil;


/**
 * Created by zhanghao on 2016/11/20.
 */

public abstract class BaseActivity extends AppCompatActivity{
    private static final String TAG = "BaseActivity";

    abstract protected int setContentLayout();

    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private boolean mIsHidden=false;

    protected abstract boolean canBack();
    protected DayNightUtil dayNightUtil;

    protected String [] COMMON_PERMISSIONS={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setContentView(setContentLayout());
       // refreshStatusBar();
        mToolbar=(Toolbar) findViewById(R.id.toolbar_include);
        mAppBarLayout= (AppBarLayout) findViewById(R.id.app_bar_layout);
        if (mToolbar==null||mAppBarLayout==null) throw new IllegalStateException("the BaseActivity must be contain a toolbar and appbar");
        setUpToolBar();

    }

    /**
     * 初始化主题
     */
    protected void initTheme() {
        Log.d(TAG, "initTheme: 执行了");
        dayNightUtil=new DayNightUtil(this);
        if (dayNightUtil.isDay())
            setTheme(R.style.DayTheme);
        else
            setTheme(R.style.NightTheme);
    }

    /**
     * 设置toolbar
     */
//    protected void refreshStatusBar(){
//        Theme theme=getTheme();
//        TypedValue statusBarColor=new TypedValue();
//        TypedValue toolbarColor=new TypedValue();
//        theme.resolveAttribute(R.attr.colorPrimaryDark,statusBarColor,true);
//        theme.resolveAttribute(R.attr.colorPrimary,toolbarColor,true);
//        mToolbar.setBackgroundResource(toolbarColor.resourceId);
////        if (Build.VERSION.SDK_INT>=21){
////            Log.d(TAG, "refreshStatusBar: 设置statusBar的颜色");
////            getWindow().setStatusBarColor(getResources().getColor(statusBarColor.resourceId));
////        }
//        StatusBarUtil.setTranslucent(this,0);
//
//    };


    protected void setUpToolBar(){
        setSupportActionBar(mToolbar);
        if (canBack()){
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        }
//        if (Build.VERSION.SDK_INT>=21)
//            mAppBarLayout.setElevation(10.6f);
    }

    protected void beginShare(String title,String url){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,title+url+"\r\n(Reader测试)");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"分享到..."));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_setting:
                ActivityUtil.toSettingActivity(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }





    protected void setAppBarAlpha(float alpha){
        mAppBarLayout.setAlpha(alpha);
    }


    protected void hideOrShowToolBar(){
        mAppBarLayout.animate()
                .translationY(mIsHidden?0:-mAppBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden=!mIsHidden;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        View view=getWindow().getDecorView();
        if (requestCode == PermissionUtil.REQUEST_PERMISSION) {
            if (!PermissionUtil.verifyPermissions(grantResults)) {
                Snackbar.make(view, "缺少必要的权限！请点击设置",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("设置", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(BaseActivity.this, permissions,
                                        PermissionUtil.REQUEST_PERMISSION);
                            }
                        }).show();
            }
        }
    }



    protected void initPermission(String [] PERMISSIONS){
        boolean permissionCheck= PermissionUtil.hasPermissions(this,PERMISSIONS);
        if (!permissionCheck)
            ActivityCompat.requestPermissions(this,PERMISSIONS,PermissionUtil.REQUEST_PERMISSION);
    }






}
