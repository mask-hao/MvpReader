package com.zhanghao.reader.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zhanghao.reader.R;
import com.zhanghao.reader.utils.FragmentUtil;
import com.zhanghao.reader.utils.FragmentConfig;
import com.zhanghao.reader.utils.MainMenu;
import com.zhanghao.reader.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.toolbar)
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
        initView();
        initMenu();

    }

    private void initView() {
        toggle = new ActionBarDrawerToggle(this, drawerMain, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerMain.addDrawerListener(toggle);
        mainNav.setNavigationItemSelectedListener(this);
        changeUtil = new FragmentUtil(this);
        StatusBarUtil.setTransparent(this);
    }


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
        mainNav.inflateMenu(R.menu.menu_main);
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
}
