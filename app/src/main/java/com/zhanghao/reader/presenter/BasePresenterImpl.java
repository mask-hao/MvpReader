package com.zhanghao.reader.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhanghao on 2016/11/19.
 * 所有presenter的父类 控制事件
 */

public class BasePresenterImpl implements BasePresenter{

    private CompositeSubscription compositeSubscription;

    protected void addSubscription(Subscription subscription){
        if (compositeSubscription == null)
            compositeSubscription=new CompositeSubscription();
        this.compositeSubscription.add(subscription);
    }

    @Override
    public void unSubScribe() {
        if (this.compositeSubscription!=null)
            this.compositeSubscription.unsubscribe();
    }
}
