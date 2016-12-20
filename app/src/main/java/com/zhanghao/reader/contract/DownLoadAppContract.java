package com.zhanghao.reader.contract;

import android.content.Context;

import com.zhanghao.reader.bean.AppInfo;
import com.zhanghao.reader.presenter.BasePresenter;

import okhttp3.ResponseBody;

/**
 * Created by zhanghao on 2016/12/16.
 */

public interface DownLoadAppContract {
    interface View extends BaseView<Presenter>{
        void upDate(AppInfo appInfo);
    }


    interface ServiceView{
        void writeIntoDisk(ResponseBody responseBody);
    }

    interface Presenter extends BasePresenter{
        void getAppInfo();
        void startDownLoadService(Context context,String downLoad_url);
        void downLoadNewApp(String downLoad_url);
    }
}
