package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.GankVideoItem;
import com.zhanghao.reader.presenter.BasePresenter;

/**
 * Created by zhanghao on 2016/11/19.
 */

public interface GankVideoContract{

    interface View extends BaseView<BasePresenter>{
        void setUpGankVideo(GankVideoItem videoItem);
    }

    interface Presenter extends BasePresenter{
        void getGankVideoDily(int page);
    }
}
