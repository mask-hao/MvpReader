package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.GankPicItem;
import com.zhanghao.reader.presenter.BasePresenter;

/**
 * Created by zhanghao on 2016/11/19.
 */

public interface GankPicContract {

    interface View extends BaseView<Presenter>{
        void setUpGankPicDaily(GankPicItem picDaily,boolean firstLoad);
    }

    interface Presenter extends BasePresenter{
        void getGankPicDaliy(int page,boolean firstLoad);
    }
}
