package com.zhanghao.reader.ui.activity;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import com.zhanghao.reader.R;
import com.zhanghao.reader.ui.listener.PermissionListener;
import com.zhanghao.reader.utils.ActivityPool;
import com.zhanghao.reader.utils.ActivityUtil;
import com.zhanghao.reader.utils.DayNightUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghao on 2016/11/20.
 */

public abstract class BaseActivity extends AppCompatActivity{
    private static final String TAG = "BaseActivity";
    private static final int REQUEST_PERMISSION=101;
    abstract protected int setContentLayout();
    private static PermissionListener permissionListener;
    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private boolean mIsHidden=false;

    protected abstract boolean canBack();
    protected DayNightUtil dayNightUtil;

    protected String [] COMMON_PERMISSIONS={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPool.addActivity(this);
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
            setTheme(R.style.DayThemeMaterial);
        else
            setTheme(R.style.NightThemeMaterial);
    }


    protected void setUpToolBar(){
        setSupportActionBar(mToolbar);
        if (canBack()){
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    protected void beginShare(String title,String url){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,title+url+"\r\n(Reader测试)");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"分享到..."));
    }

    protected void beginShareImg(String title,String url){
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



    /**
     * 动态申请权限
     * @param permissions 所有的权限
     */
    public static void requestRunTimePermissions(String [] permissions, PermissionListener listener){
        permissionListener=listener;
        Activity activity= ActivityPool.getTopActivity();
        if (activity==null)
            return;
        else{
            List<String> permissionList=new ArrayList<>();
            for (String permission:permissions){
                if (ContextCompat.checkSelfPermission(activity,permission)!= PackageManager.PERMISSION_GRANTED)
                    permissionList.add(permission);
            }
            if (!permissionList.isEmpty()){
                ActivityCompat.requestPermissions(activity,permissionList.toArray(new String[permissionList.size()]),REQUEST_PERMISSION);
            }else
                permissionListener.onGranted();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION:
                if (grantResults.length>0){
                    List<String> deniedPermissions=new ArrayList<>();
                    for (int i=0;i<grantResults.length;i++){
                        int grantResult=grantResults[i];
                        String permission=permissions[i];
                        if (grantResult==PackageManager.PERMISSION_DENIED){
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty())
                        permissionListener.onGranted();
                    else
                        permissionListener.onDenied(deniedPermissions);
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityPool.removeActivity(this);
    }


    //进入设置界面
    protected void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:"+ getPackageName()));
        startActivity(intent);
    }
}
