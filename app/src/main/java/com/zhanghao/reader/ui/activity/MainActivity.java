package com.zhanghao.reader.ui.activity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.zhanghao.reader.BuildConfig;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.AppInfo;
import com.zhanghao.reader.bean.ThemeChangeMessage;
import com.zhanghao.reader.contract.DownLoadAppContract;
import com.zhanghao.reader.presenter.DownLoadAppPresenterImpl;
import com.zhanghao.reader.ui.listener.PermissionListener;
import com.zhanghao.reader.ui.listener.RefreshUIListener;
import com.zhanghao.reader.utils.DayNight;
import com.zhanghao.reader.utils.FragmentUtil;
import com.zhanghao.reader.utils.FragmentConfig;
import com.zhanghao.reader.utils.MainMenu;
import com.zhanghao.reader.utils.SpUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,DownLoadAppContract.View{
    @BindView(R.id.toolbar_include)
    Toolbar toolbar;
    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.main_nav)
    NavigationView mainNav;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;
    private ActionBarDrawerToggle toggle;
    private FragmentUtil changeUtil;
    private long exitTime=0;
    private static final String TAG = "MainActivity";
    public RefreshUIListener uiListener;
    private final static String FILE_NAME="setting";
    private final static String URL_NAME="start_url";

    AlertDialog alertDialog;
    DownLoadAppContract.Presenter updatePresenter;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        refreshStatusBarTest();
        initView();
        initMenu();
        initUpdateVersion();

    }


    /**
     * 检查更新
     */
    private void initUpdateVersion() {
        requestRunTimePermissions(COMMON_PERMISSIONS, new PermissionListener() {
            @Override
            public void onGranted() {
                updatePresenter=new DownLoadAppPresenterImpl(MainActivity.this);
                updatePresenter.getAppInfo();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
                String deniedPermission="";
                for (String permission : deniedPermissions) {
                    deniedPermission+=permission;
                }
                alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setMessage(deniedPermission)
                        .setPositiveButton(R.string.sure,
                                ((dialog, which) ->
                                        alertDialog.dismiss()
                                ))
                        .setNegativeButton(R.string.setting,((dialog, which) ->
                        {
                            alertDialog.dismiss();
                            startAppSettings();
                        }
                        ))
                        .show();
            }
        });


    }



    private void initView() {
        toggle = new ActionBarDrawerToggle(this, drawerMain, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerMain.addDrawerListener(toggle);
        mainNav.setNavigationItemSelectedListener(this);
        changeUtil = new FragmentUtil(this);
    }

    private void setNavImg() {
        SpUtil.init(this,FILE_NAME);
        View view=mainNav.getHeaderView(0);
        ImageView navIv= (ImageView) view.findViewById(R.id.nav_iv);
        Picasso.with(this)
                .load(SpUtil.getSavedString(URL_NAME))
                .error(R.drawable.img_header_back)
                .into(navIv);
    }

    // TODO: 2016/12/11 动态添加Menu
    private void initMenu() {
        ArrayList<MainMenu> mainMenus = new ArrayList<>();
        mainMenus.add(MainMenu.ZHIHU);
        mainMenus.add(MainMenu.GANKDAILY);
        Menu menu = mainNav.getMenu();
        menu.clear();
        for (int i = 0; i < mainMenus.size(); i++) {
            int menuTitle = mainMenus.get(i).getTitle();
            System.out.println(menuTitle);
            int icon = mainMenus.get(i).getIcon();
            MenuItem menuItem = menu.add(0, i, 0, menuTitle);
            menuItem.setIcon(icon);
            menuItem.setCheckable(true);
            if (i == 0) menuItem.setChecked(true);
        }
        mainNav.inflateHeaderView(R.layout.nav_header);
        changeUtil.initFragment("zhihu");
        setNavImg();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String tag = FragmentConfig.getFragmentList().get(item.getItemId());
        changeUtil.initFragment(tag);
        drawerMain.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerMain != null&&drawerMain.isDrawerOpen(GravityCompat.START)){
                drawerMain.closeDrawer(GravityCompat.START);
        } else {
            doExitApp();
        }

    }

    private void doExitApp() {
       if ((System.currentTimeMillis()-exitTime)>2000){
           Toast.makeText(this,"再按一次图退出！",Toast.LENGTH_SHORT).show();
           exitTime=System.currentTimeMillis();
       }else
           finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        if (dayNightUtil.isNight()) menu.getItem(0).setTitle(R.string.day_mode);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dayNightUtil.isNight()&& item.getItemId()==R.id.menu_night)
            item.setTitle(R.string.day_mode);
        switch (item.getItemId()){
            case R.id.menu_night:
                changeTheme(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    /**
     * 改变为夜间主题
     */
    private void changeTheme(MenuItem menuItem) {
        showThemeChangeAnimation();
        toggleThemeSetting(menuItem);
        refreshStatusBarTest();
        TypedValue navColor=new TypedValue();
        getTheme().resolveAttribute(R.attr.colorCdlBackBackground,navColor,true);
        mainNav.setBackgroundResource(navColor.resourceId);
        EventBus.getDefault().post(new ThemeChangeMessage(true));
    }

    private void refreshStatusBarTest() {
        Resources.Theme theme=getTheme();
        TypedValue statusBarColor=new TypedValue();
        TypedValue toolbarColor=new TypedValue();
        theme.resolveAttribute(R.attr.colorPrimaryDark,statusBarColor,true);
        theme.resolveAttribute(R.attr.colorPrimary,toolbarColor,true);
        toolbar.setBackgroundResource(toolbarColor.resourceId);
        Log.d(TAG, "test: 改变主题之后："+statusBarColor.resourceId);
        if (Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(getResources().getColor(statusBarColor.resourceId));
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(SYSTEM_UI_FLAG_LAYOUT_STABLE);

        }
    }

    /**
     * 却换主题设置
     */
    private void toggleThemeSetting(MenuItem menuItem) {
        if (dayNightUtil.isDay()){
            dayNightUtil.setMode(DayNight.NIGHT);
            setTheme(R.style.NightThemeMaterial);
            menuItem.setTitle(R.string.day_mode);
        }else{
            dayNightUtil.setMode(DayNight.DAY);
            setTheme(R.style.DayThemeMaterial);
            menuItem.setTitle(R.string.nigth_mode);
        }
    }

    /**
     * 切换的动画
     */
    private void showThemeChangeAnimation() {
        final View decorView=getWindow().getDecorView();
        Bitmap cacheBitmap=getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap!=null){
            final View view=new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(),cacheBitmap));
            ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup)decorView).addView(view,layoutParams);
            ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(view,"alpha", 1f, 0f);
            objectAnimator.setDuration(500);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup)decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    /**
     * 获取一个View的缓存视图
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view){
        final boolean drawingCacheEnable=true;
        view.setDrawingCacheEnabled(drawingCacheEnable);
        view.buildDrawingCache(drawingCacheEnable);
        final Bitmap drawingCache=view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache!=null){
            bitmap=Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        }else {
            bitmap=null;
        }
        return bitmap;
    }


    @Override
    public void upDate(final AppInfo appInfo) {
        int buildVersionCode= BuildConfig.VERSION_CODE;
        int loadVersionCode= Integer.parseInt(appInfo.getBuild());
        final String url=appInfo.getInstall_url().replace("http://download.fir.im/","");
        if (loadVersionCode>buildVersionCode){
            alertDialog=new AlertDialog.Builder(this)
                    .setTitle("有更新")
                    .setMessage(appInfo.getChangelog())
                    .setNegativeButton(R.string.cancle, (dialog, which) -> alertDialog.cancel())
                    .setPositiveButton(R.string.sure, (dialog, which) -> updatePresenter.startDownLoadService(MainActivity.this,url))
                    .show();
        }
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
    public void setPresenter(DownLoadAppContract.Presenter presenter) {

    }
}
