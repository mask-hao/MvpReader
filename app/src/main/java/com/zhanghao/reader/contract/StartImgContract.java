package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.StartImgBean;
import com.zhanghao.reader.presenter.BasePresenter;

/**
 * Created by zhanghao on 2016/12/9.
 */

public interface StartImgContract {
    interface View extends BaseView<Presenter>{
        void setUpStartImg(StartImgBean startImg);
    }
    interface Presenter extends BasePresenter{
        void getStartImg();
    }
}
