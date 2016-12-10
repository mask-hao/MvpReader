package com.zhanghao.reader.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zhanghao.reader.R;
import com.zhanghao.reader.contract.BaseView;

/**
 * Created by zhanghao on 2016/11/20.
 */

public abstract class BaseActivity extends AppCompatActivity{
    private static final String TAG = "BaseActivity";
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    protected abstract int setContentLayout();
    protected abstract boolean canBack();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout());
        try {
            toolbar= (Toolbar) findViewById(R.id.toolbar);
            appBarLayout= (AppBarLayout) findViewById(R.id.appbar_layout);
            setUpToolBar();
        }catch (NullPointerException e){
            Log.e(TAG, "onCreate: "+"the BaseActivity must be contain a toolbar and appbar");
        }
    }


    protected void setUpToolBar(){
        setSupportActionBar(toolbar);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
