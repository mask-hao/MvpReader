package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.GankDailyItem;
import com.zhanghao.reader.bean.GankPicItem;
import com.zhanghao.reader.presenter.BasePresenter;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/27.
 */

public interface GankDailyContract {
    interface View extends BaseView<Presenter>{
        void setUpGankItemDaily(List<GankDailyItem> gankDailyItems, boolean firstLoad,boolean isRefresh);
    }

    interface Presenter extends BasePresenter {
        void getGankDaliy(int page,boolean firstLoad,boolean isRefresh);
    }
}
