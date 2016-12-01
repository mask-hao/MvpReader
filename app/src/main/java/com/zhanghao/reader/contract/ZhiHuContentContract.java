package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.ZhiHuContent;
import com.zhanghao.reader.presenter.BasePresenter;

/**
 * Created by zhanghao on 2016/11/19.
 */

public interface ZhiHuContentContract {
    interface View extends BaseView<Presenter>{
        void setUpZhiHuContent(ZhiHuContent zhiHuContent);
    }

    interface Presenter extends BasePresenter{
        void getZhiHuContent(String id);
    }
}
