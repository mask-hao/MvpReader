package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.ZhiHuStartImgBean;
import com.zhanghao.reader.presenter.BasePresenter;

/**
 * Created by zhanghao on 2016/12/9.
 */

public interface ZhiHuStartContract {
    interface View extends BaseView<Presenter>{
        void setUpStartImg(ZhiHuStartImgBean zhiHuStartImgBean);
    }
    interface Presenter extends BasePresenter{
        void getStartZhiHuStartImg();
    }
}
