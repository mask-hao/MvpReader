package com.zhanghao.reader.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhanghao.reader.BuildConfig;
import com.zhanghao.reader.R;
import com.zhanghao.reader.bean.AppInfo;
import com.zhanghao.reader.contract.DownLoadAppContract;
import com.zhanghao.reader.presenter.DownLoadAppPresenterImpl;
import com.zhanghao.reader.ui.listener.PermissionListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanghao on 2016/12/11.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener,DownLoadAppContract.View{
    private static final String TAG = "SettingActivity";
    @BindView(R.id.update_rl)
    RelativeLayout updateRl;
    AlertDialog alertDialog;
    DownLoadAppContract.Presenter updatePresenter;

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
        updateRl.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_rl:
                checkUpdate();
                break;

        }


    }

    /**
     * 检查跟新
     */
    private void checkUpdate() {
        requestRunTimePermissions(COMMON_PERMISSIONS, new PermissionListener() {
            @Override
            public void onGranted() {
                updatePresenter =new DownLoadAppPresenterImpl(SettingActivity.this);
                updatePresenter.getAppInfo();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
                String deniedPermission="";
                for (String permission : deniedPermissions) {
                    deniedPermission+=permission;
                }
                alertDialog=new AlertDialog.Builder(SettingActivity.this)
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


    @Override
    public void upDate(AppInfo appInfo) {
        int buildVersionCode= BuildConfig.VERSION_CODE;
        int loadVersionCode= Integer.parseInt(appInfo.getBuild());
        final String url=appInfo.getInstall_url().replace("http://download.fir.im/","");
        if (loadVersionCode>buildVersionCode){
            alertDialog=new AlertDialog.Builder(this)
                    .setTitle("有更新")
                    .setMessage(appInfo.getChangelog())
                    .setPositiveButton(R.string.sure,(dialog, which) -> updatePresenter.startDownLoadService(SettingActivity.this,url))
                    .setNegativeButton(R.string.cancle, (dialog, which) -> alertDialog.cancel())
                    .show();
        }
        if (loadVersionCode==buildVersionCode) Toast.makeText(this,"您已经安装最新版本App",Toast.LENGTH_SHORT).show();

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
