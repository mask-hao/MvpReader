package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zhanghao.reader.api.gank.GankApi;
import com.zhanghao.reader.api.gank.GankService;
import com.zhanghao.reader.bean.GankDailyAllItem;
import com.zhanghao.reader.contract.GankDailyAllContract;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/11/28.
 */

public class GankDailyAllPresenterImpl extends BasePresenterImpl implements GankDailyAllContract.Presenter{

    private static final String TAG = "GankDailyAllPresenterIm";


    @NonNull
    private final GankDailyAllContract.View mView;

    private GankService gankService;

    public GankDailyAllPresenterImpl(@NonNull GankDailyAllContract.View view) {
        this.mView = view;
        gankService=new GankApi().getService();
        view.setPresenter(this);
    }

    @Override
    public void getGankDailyAll(String date, final boolean firstload) {
        if (firstload) mView.showDialog();
        Subscription subscription=gankService.getGankItemAll(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GankDailyAllItem, GankDailyAllItem>() {
                    @Override
                    public GankDailyAllItem call(GankDailyAllItem gankDailyAllItem) {
                        return gankDailyAllItem;
                    }
                })
                .subscribe(new Observer<GankDailyAllItem>() {
                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e(TAG, "onError: "+e.getMessage());
                        mView.hideDialog();
                        mView.showError(e);
                    }

                    @Override
                    public void onNext(GankDailyAllItem gankDailyAllItem) {

                        Log.e(TAG, "onNext: "+gankDailyAllItem.getResults().getAndroid().get(0).toString());

                        mView.setUpGankDailyAll(gankDailyAllItem,firstload);
                    }
                });
        addSubscription(subscription);
    }

}
