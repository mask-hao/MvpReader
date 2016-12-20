package com.zhanghao.reader.api.fir;

import com.zhanghao.reader.api.WebApi;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import retrofit2.Retrofit;

/**
 * Created by zhanghao on 2016/12/16.
 */

public class FirApi extends WebApi{

    private String baseUrl="http://api.fir.im/";
    private Retrofit retrofit=getApi(baseUrl,new OkHttpClient());

    @Override
    public <T> T getService() {
        return (T) retrofit.create(FirService.class);
    }


}
