package com.zhanghao.reader.api.gank;

import com.zhanghao.reader.api.WebApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class StartImgApi extends WebApi{
    private String baseUrl="http://maskhao.cn";
    private Retrofit retrofit=getApi(baseUrl,new OkHttpClient());
    @Override
    public <T> T getService() {
        return (T)retrofit.create(StartImgService.class);
    }
}
