package com.zhanghao.reader.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zhanghao.reader.api.fir.FirApi;
import com.zhanghao.reader.api.fir.FirDownLoadApi;
import com.zhanghao.reader.api.fir.FirService;
import com.zhanghao.reader.bean.AppInfo;
import com.zhanghao.reader.contract.DownLoadAppContract;
import com.zhanghao.reader.service.DownLoadUpdateService;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/12/16.
 */

public class DownLoadAppPresenterImpl extends BasePresenterImpl implements DownLoadAppContract.Presenter{

    private static final String TAG = "DownLoadAppPresenterImp";

    private  DownLoadAppContract.View mView;


    private  DownLoadAppContract.ServiceView mServiceView;


    private FirService firService;

    public DownLoadAppPresenterImpl(DownLoadAppContract.View mView) {
        this.mView = mView;
        firService=new FirApi().getService();
    }

    public DownLoadAppPresenterImpl(DownLoadAppContract.ServiceView mServiceView) {
        this.mServiceView = mServiceView;
        firService=new FirDownLoadApi().getService();
    }


    @Override
    public void getAppInfo() {
        Subscription subscription=firService.getAppInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<AppInfo, AppInfo>() {
                    @Override
                    public AppInfo call(AppInfo appInfo) {
                        return appInfo;
                    }
                })
                .subscribe(new Observer<AppInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onNext(AppInfo appInfo) {
                        mView.upDate(appInfo);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void startDownLoadService(Context context,String downLoad_url) {
        //启动下载服务
        Intent intent=new Intent(context,DownLoadUpdateService.class);
        intent.putExtra("download_url",downLoad_url);
        context.startService(intent);
    }

    @Override
    public void downLoadNewApp(String downLoad_url) {
        Subscription subscription=firService.downloadNewApp(downLoad_url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ResponseBody, ResponseBody>() {
                    @Override
                    public ResponseBody call(ResponseBody responseBody) {
                        return responseBody;
                    }
                })
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mServiceView.writeIntoDisk(responseBody);
                    }
                });
        addSubscription(subscription);
    }


}
