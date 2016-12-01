package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;

import com.zhanghao.reader.api.gank.GankApi;
import com.zhanghao.reader.api.gank.GankService;
import com.zhanghao.reader.bean.GankVideoItem;
import com.zhanghao.reader.contract.GankVideoContract;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class GankVideoPresenterImpl extends BasePresenterImpl implements GankVideoContract.Presenter{

    @NonNull
    private final GankVideoContract.View mView;

    private GankService gankService;

    public GankVideoPresenterImpl(@NonNull GankVideoContract.View mView) {
        this.mView = mView;
        gankService=new GankApi().getService();
        mView.setPresenter(this);
    }

    @Override
    public void getGankVideoDily(int page) {
        mView.showDialog();
        Subscription subscription=gankService.getGankVideoDaliy(page)
                .map(new Func1<GankVideoItem, GankVideoItem>() {
                    @Override
                    public GankVideoItem call(GankVideoItem gankVideoItem) {
                        return gankVideoItem;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankVideoItem>() {
                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideDialog();
                        mView.showError(e);
                    }

                    @Override
                    public void onNext(GankVideoItem gankVideoItem) {
                        mView.setUpGankVideo(gankVideoItem);
                    }
                });
        addSubscription(subscription);
    }
}
