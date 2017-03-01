package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;

import com.zhanghao.reader.api.zhihu.ZhiHuApi;
import com.zhanghao.reader.api.zhihu.ZhiHuService;
import com.zhanghao.reader.bean.ZhiHuStartImgBean;
import com.zhanghao.reader.contract.ZhiHuStartContract;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/12/9.
 */

public class ZhiHuStartImgPresenterImpl extends BasePresenterImpl implements ZhiHuStartContract.Presenter{

    @NonNull
    private final ZhiHuStartContract.View mView;


    private ZhiHuService zhiHuService;

    public ZhiHuStartImgPresenterImpl(@NonNull ZhiHuStartContract.View mView) {
        this.mView=mView;
        zhiHuService=new ZhiHuApi().getService();
        mView.setPresenter(this);
    }

    @Override
    public void getStartZhiHuStartImg() {
        Subscription subscription=zhiHuService.getZhiHuFlashImg()
                .map(new Func1<ZhiHuStartImgBean, ZhiHuStartImgBean>() {
                    @Override
                    public ZhiHuStartImgBean call(ZhiHuStartImgBean zhiHuStartImgBean) {
                        return zhiHuStartImgBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuStartImgBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setUpStartImg(null);
                    }

                    @Override
                    public void onNext(ZhiHuStartImgBean zhiHuStartImgBean) {
                        mView.setUpStartImg(zhiHuStartImgBean);
                    }
                });
//        Subscription subscription=zhiHuService.getZhiHuFlashImg()
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
