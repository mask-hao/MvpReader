package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zhanghao.reader.api.gank.StartImgApi;
import com.zhanghao.reader.api.gank.StartImgService;
import com.zhanghao.reader.bean.StartImgBean;
import com.zhanghao.reader.contract.StartImgContract;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/12/9.
 */

public class StartImgPresenterImpl extends BasePresenterImpl implements StartImgContract.Presenter{

    private static final String TAG = "StartImgPresenterImpl";

    @NonNull
    private final StartImgContract.View mView;

    private StartImgService startImgService;

    public StartImgPresenterImpl(@NonNull StartImgContract.View mView) {
        this.mView=mView;
        startImgService=new StartImgApi().getService();
        mView.setPresenter(this);
    }

    @Override
    public void getStartImg() {
        Subscription subscription=startImgService.getStartImg()
                .map(zhiHuStartImgBean -> zhiHuStartImgBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartImgBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onError: "+e.getMessage());
                        mView.setUpStartImg(null);
                    }

                    @Override
                    public void onNext(StartImgBean zhiHuStartImgBean) {
                        mView.setUpStartImg(zhiHuStartImgBean);
                    }
                });
//        Subscription subscription=zhiHuService.getStartImg()
//                .map(zhiHuStartImgBean -> zhiHuStartImgBean)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ZhiHuStartImgBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ZhiHuStartImgBean zhiHuStartImgBean) {
//                        mView.setUpStartImg(zhiHuStartImgBean);
//                    }
//                });
        addSubscription(subscription);
    }


}
