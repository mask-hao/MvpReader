package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;

import com.zhanghao.reader.api.gank.GankApi;
import com.zhanghao.reader.api.gank.GankService;
import com.zhanghao.reader.bean.GankItem;
import com.zhanghao.reader.bean.GankPicItem;
import com.zhanghao.reader.contract.GankPicContract;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class GankPicPresentImpl extends BasePresenterImpl implements GankPicContract.Presenter{

    @NonNull
    private final GankPicContract.View mView;

    private GankService gankService;

    public GankPicPresentImpl(@NonNull GankPicContract.View view) {
        this.mView=view;
        gankService=new GankApi().getService();
        mView.setPresenter(this);
    }

    @Override
    public void getGankPicDaliy(int page, final boolean firstLoad) {
        if (firstLoad) mView.showDialog();
        Subscription subscription=gankService.getGankPicDaliy(page)
                .map(new Func1<GankPicItem, GankPicItem>() {
                    @Override
                    public GankPicItem call(GankPicItem gankPicItem) {
                        return gankPicItem;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankPicItem>() {
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
                    public void onNext(GankPicItem gankPicItem) {
                        mView.setUpGankPicDaily(gankPicItem,firstLoad);
                    }
                });
        addSubscription(subscription);
    }
}
