package com.zhanghao.reader.api.zhihu;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.zhanghao.reader.MyApplication;
import com.zhanghao.reader.api.WebApi;
import com.zhanghao.reader.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by zhanghao on 2016/11/16.
 */

public class ZhiHuApi extends WebApi{


    private static final String TAG = "ZhiHuApi";


    private String baseUrl="http://news-at.zhihu.com";
    private Retrofit retrofit;
    private Cache cache;
    private  static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originResponse=chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())){
                //如果有网络
                Log.d(TAG, "intercept: 有网络");
                int maxAge=60;//一分钟
                return  originResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }else{
                Log.d(TAG, "intercept: 无网络");
                int maxAge=60*60*24*7;//一周
                return originResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxAge)
                        .build();
            }
        }
    };




    private void initApi(){
        initCache();
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request=chain.request();
                        if (!NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())){
                           request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
                .build();
       retrofit=getApi(baseUrl,okHttpClient);
    }



    private void initCache() {
        File file=new File(MyApplication.getContext().getCacheDir(),"zhihuCache");
        cache=new Cache(file,1024*1024*10);
    }


    @Override
    public  <T> T getService() {
        initApi();
        return (T) retrofit.create(ZhiHuService.class);
    }

}