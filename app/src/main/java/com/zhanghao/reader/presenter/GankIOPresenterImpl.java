package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zhanghao.reader.api.gank.GankApi;
import com.zhanghao.reader.api.gank.GankService;
import com.zhanghao.reader.bean.GankItem;
import com.zhanghao.reader.contract.GankIODailyContract;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class GankIOPresenterImpl extends BasePresenterImpl implements GankIODailyContract.Presenter{

    private static final String TAG = "GankIOPresenterImpl";
    private GankService gankService;



    @NonNull
    private final GankIODailyContract.View mView;

    public GankIOPresenterImpl(@NonNull GankIODailyContract.View view){
        this.mView=view;
        gankService=new GankApi().getService();
        mView.setPresenter(this);
    }


    @Override
    public void getGankDaliy(String type, int page , final boolean upadate ) {
        if (!upadate) mView.showDialog();
        Subscription subscription=gankService.getGankDaliy(type,page)
                .map(new Func1<GankItem, GankItem>() {
                    @Override
                    public GankItem call(GankItem gankItem) {
                        return gankItem;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankItem>() {
                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                        e.printStackTrace();
                        mView.hideDialog();
                        mView.showError(e);
                    }

                    @Override
                    public void onNext(GankItem gankItem) {
                        mView.setUpGankData(gankItem,upadate);
                    }
                });
        addSubscription(subscription);
    }






}
