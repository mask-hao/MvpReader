package com.zhanghao.reader.contract;

/**
 * Created by zhanghao on 2016/11/16.
 */

public interface BaseView<T>{
    void showDialog();
    void hideDialog();
    void showError(Throwable e);
    void setPresenter(T presenter);
}
