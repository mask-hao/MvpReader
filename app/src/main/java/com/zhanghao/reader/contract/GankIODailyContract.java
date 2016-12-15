package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.GankItem;
import com.zhanghao.reader.presenter.BasePresenter;

/**
 * Created by zhanghao on 2016/11/19.
 */

public interface GankIODailyContract {
    interface View extends BaseView<Presenter>{
        void setUpGankData(GankItem gankItem,boolean firstLoad,boolean refresh);
    }

    interface Presenter extends BasePresenter{
        void getGankDaliy(String type,int page,boolean firstLoad,boolean refresh);
    }
}
