package com.zhanghao.reader;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhanghao on 2016/11/24.
 */

public class MyApplication extends Application {
   static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}
