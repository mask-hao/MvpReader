package com.zhanghao.reader.ui.activity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.ThemeChangeMessage;
import com.zhanghao.reader.ui.listener.RefreshUIListener;
import com.zhanghao.reader.utils.DayNight;
import com.zhanghao.reader.utils.FragmentUtil;
import com.zhanghao.reader.utils.FragmentConfig;
import com.zhanghao.reader.utils.MainMenu;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
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
        initTheme();
        initView();
        initMenu();

    }

    private void initView() {
        toggle = new ActionBarDrawerToggle(this, drawerMain, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerMain.addDrawerListener(toggle);
        mainNav.setNavigationItemSelectedListener(this);
        changeUtil = new FragmentUtil(this);
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

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_night:
                changeTheme();
                break;
        }

        return super.onOptionsItemSelected(item);
    }




    /**
     * 改变为夜间主题
     */
    private void changeTheme() {
        showThemeChangeAnimation();
        toggleThemeSetting();
        refreshStatusBar();
        EventBus.getDefault().post(new ThemeChangeMessage(true));
    }

    /**
     * 却换主题设置
     */
    private void toggleThemeSetting() {
        if (dayNightUtil.isDay()){
            dayNightUtil.setMode(DayNight.NIGHT);
            setTheme(R.style.NightTheme);
        }else{
            dayNightUtil.setMode(DayNight.DAY);
            setTheme(R.style.DayTheme);
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


}
