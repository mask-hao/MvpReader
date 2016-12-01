package com.zhanghao.reader.api.gank;

import com.zhanghao.reader.api.WebApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class GankApi extends WebApi{
    private String baseUrl="http://gank.io";
    private Retrofit retrofit=getApi(baseUrl,new OkHttpClient());
    @Override
    public <T> T getService() {
        return (T)retrofit.create(GankService.class);
    }
}
