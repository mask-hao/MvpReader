package com.zhanghao.reader.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.zhanghao.reader.contract.BaseView;

/**
 * Created by zhanghao on 2016/11/20.
 */

public class BaseActivity extends AppCompatActivity{

    private ProgressDialog progressDialog;

    public void setUpToolBar(String toolbarTitle,Toolbar toolbar, boolean HomeButtonEnable, boolean HomeAsUpEnable){
        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(HomeButtonEnable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(HomeAsUpEnable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void showDia(String massage){
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage(massage);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    protected void hideDia(){
        if (progressDialog!=null)
            progressDialog.dismiss();
    }

    protected void showErr(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
