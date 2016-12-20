package com.zhanghao.reader.api.fir;

import com.zhanghao.reader.api.WebApi;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by zhanghao on 2016/12/16.
 */

public class FirDownLoadApi extends WebApi{


    private String baseUrl="http://download.fir.im/";
    private Retrofit retrofit=getApi(baseUrl,new OkHttpClient());


    @Override
    public <T> T getService() {
        return (T) retrofit.create(FirService.class);
    }

}
