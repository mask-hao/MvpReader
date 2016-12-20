package com.zhanghao.reader.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zhanghao.reader.MyApplication;
import com.zhanghao.reader.api.zhihu.ZhiHuApi;
import com.zhanghao.reader.api.zhihu.ZhiHuService;
import com.zhanghao.reader.bean.ZhiHuDailyItem;
import com.zhanghao.reader.bean.ZhiHuLatestItem;
import com.zhanghao.reader.bean.ZhiHuStories;
import com.zhanghao.reader.bean.ZhiHuTopStories;
import com.zhanghao.reader.contract.ZhiHuDailyContract;
import com.zhanghao.reader.utils.NetWorkUtil;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by zhanghao on 2016/11/16.
 */

public class ZhiHuNewsPresenterImpl extends BasePresenterImpl implements ZhiHuDailyContract.Presenter{


    private static final String TAG = "ZhiHuNewsPresenterImpl";

    @NonNull
    private final ZhiHuDailyContract.View mView;


    private ZhiHuService zhiHuApiService;

    public ZhiHuNewsPresenterImpl(@NonNull ZhiHuDailyContract.View view) {
        mView =checkNotNull(view);
        zhiHuApiService=new ZhiHuApi().getService();
        mView.setPresenter(this);
    }

    @Override
    public void getLatestZhiHuNews(final boolean isRefresh) {
                if (!isRefresh) mView.showDialog();
        Subscription subscription=zhiHuApiService.getLatestNews()
                .map(new Func1<ZhiHuLatestItem, ZhiHuLatestItem>() {
                    @Override
                    public ZhiHuLatestItem call(ZhiHuLatestItem zhiHuDaily) {
                        return zhiHuDaily;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuLatestItem>() {
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
                    public void onNext(ZhiHuLatestItem zhiHuDailyItem) {
                            List<ZhiHuStories> storiesBeen=zhiHuDailyItem.getStories();
                            List<ZhiHuTopStories> topStoriesBeanList=zhiHuDailyItem.getTop_stories();
//                            Log.d(TAG, "onNext: "+topStoriesBeanList);

                            mView.setUpZhiHuNewsLastestList(storiesBeen,topStoriesBeanList,isRefresh);
                    }
                });
                addSubscription(subscription);

    }

    @Override
    public void getDailyNews(String date) {
        Subscription subscription=zhiHuApiService.getDailyNews(date)
                .map(new Func1<ZhiHuDailyItem,ZhiHuDailyItem>() {
                    @Override
                    public ZhiHuDailyItem call(ZhiHuDailyItem zhiHuDailyItem) {
                        return zhiHuDailyItem;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuDailyItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                        mView.showError(e);
                    }

                    @Override
                    public void onNext(ZhiHuDailyItem zhiHuDailyItem) {
                        List<ZhiHuStories> storiesBeen=zhiHuDailyItem.getStories();
                        mView.UpDateZhiHuNewsList(storiesBeen);
                    }
                });
        addSubscription(subscription);
    }

}
