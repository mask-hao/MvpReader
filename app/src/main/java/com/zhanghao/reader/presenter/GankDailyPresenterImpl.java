package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zhanghao.reader.api.gank.GankApi;
import com.zhanghao.reader.api.gank.GankService;
import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.bean.GankPicItem;
import com.zhanghao.reader.bean.GankVideoItem;
import com.zhanghao.reader.contract.GankDailyContract;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghao on 2016/11/27.
 */

public class GankDailyPresenterImpl extends BasePresenterImpl implements GankDailyContract.Presenter{


    private static final String TAG = "GankDailyPresenterImpl";

    @NonNull
    private final GankDailyContract.View mView;


    private GankService gankService;


    public GankDailyPresenterImpl(@NonNull GankDailyContract.View view) {
        this.mView = view;
        gankService=new GankApi().getService();
        view.setPresenter(this);
    }


    //// TODO: 2016/11/27 to do a lamada

    @Override
    public void getGankDaliy(int page, final boolean firstLoad, final boolean isRefresh) {

        Log.d(TAG, "getGankDaliy: "+page);

        if (firstLoad) mView.showDialog();
        Observable<GankVideoItem> gankVideoItemObservable=gankService.getGankVideoDaliy(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<GankPicItem> gankPicItemObservable=gankService.getGankPicDaliy(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<List<GankDailyItem>> gankDailyItemObservable=Observable.zip(gankPicItemObservable, gankVideoItemObservable, new Func2<GankPicItem, GankVideoItem, List<GankDailyItem>>() {
            @Override
            public List<GankDailyItem> call(GankPicItem gankPicItem, GankVideoItem gankVideoItem) {
                List<GankPicItem.ResultsBean> gankPics=gankPicItem.getResults();
                List<GankVideoItem.ResultsBean> gankVide=gankVideoItem.getResults();
                List<GankDailyItem> gankDailyItems=new ArrayList<>();
                for (int i=0;i<10;i++){
                    GankDailyItem gankDailyItem=new GankDailyItem();
                    gankDailyItem.setDate(gankPics.get(i).getPublishedAt());
                    gankDailyItem.setUrl(gankPics.get(i).getUrl());
                    gankDailyItem.setTitle(gankVide.get(i).getDesc());
                    gankDailyItems.add(gankDailyItem);
                }
                return gankDailyItems;
            }
        });

        Subscription subscription=gankDailyItemObservable.subscribe(new Observer<List<GankDailyItem>>() {
            @Override
            public void onCompleted() {
              mView.hideDialog();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.hideDialog();
                mView.showError(e);
            }

            @Override
            public void onNext(List<GankDailyItem> gankDailyItems) {
                mView.setUpGankItemDaily(gankDailyItems,firstLoad,isRefresh);
            }
        });
        addSubscription(subscription);
    }

}
