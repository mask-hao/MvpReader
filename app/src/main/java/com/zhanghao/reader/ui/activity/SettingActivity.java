package com.zhanghao.reader.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.zhanghao.reader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/12/11.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "SettingActivity";
    @BindView(R.id.nightTheme_st)
    Switch nightThemeSt;

    @Override
    protected int setContentLayout() {
        return R.layout.setting_layout;
    }

    @Override
    protected boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle(R.string.setting);
        initView();
    }

    private void initView() {
        nightThemeSt.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nightTheme_st:
                changeTheme();
                break;
        }


    }

    private void changeTheme() {
        int uiMode=getResources().getConfiguration().uiMode& Configuration.UI_MODE_NIGHT_MASK;
        switch (uiMode){
            case Configuration.UI_MODE_NIGHT_NO:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }
        recreate();
    }




}
