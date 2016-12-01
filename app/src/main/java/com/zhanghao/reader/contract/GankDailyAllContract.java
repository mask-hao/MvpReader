package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.GankDailyAllItem;
import com.zhanghao.reader.presenter.BasePresenter;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/28.
 */

public interface GankDailyAllContract {
     interface View extends BaseView<Presenter>{
        void setUpGankDailyAll(GankDailyAllItem gankDailyAll,boolean firstload);
    }

    interface Presenter extends BasePresenter{
        void getGankDailyAll(String date,boolean firstload);
    }
}
