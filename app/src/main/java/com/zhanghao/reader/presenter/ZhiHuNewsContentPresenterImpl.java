package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;

import com.zhanghao.reader.api.zhihu.ZhiHuApi;
import com.zhanghao.reader.api.zhihu.ZhiHuService;
import com.zhanghao.reader.bean.ZhiHuContent;
import com.zhanghao.reader.contract.ZhiHuContentContract;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class ZhiHuNewsContentPresenterImpl extends BasePresenterImpl implements ZhiHuContentContract.Presenter{
    @NonNull
    private final ZhiHuContentContract.View mView;
    private ZhiHuService zhiHuService;

    public ZhiHuNewsContentPresenterImpl(@NonNull ZhiHuContentContract.View view){
        this.mView=view;
        zhiHuService=new ZhiHuApi().getService();
        mView.setPresenter(this);
    }

    @Override
    public void getZhiHuContent(String id) {
        mView.showDialog();
        Subscription subscription=zhiHuService.getNewContent(id)
                .map(new Func1<ZhiHuContent, ZhiHuContent>() {
                    @Override
                    public ZhiHuContent call(ZhiHuContent zhiHuContent) {
                        return zhiHuContent;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuContent>() {
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
                    public void onNext(ZhiHuContent zhiHuContent) {
                        mView.setUpZhiHuContent(zhiHuContent);
                    }
                });
        addSubscription(subscription);
    }
}
