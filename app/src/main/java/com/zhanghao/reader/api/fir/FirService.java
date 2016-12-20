package com.zhanghao.reader.api.fir;

import com.zhanghao.reader.bean.AppInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by zhanghao on 2016/12/16.
 */

public interface FirService {
     @GET("apps/latest/5853fd48959d6934b200107f?api_token=e44958025ce1b0425d8649b2231b494d")
     Observable<AppInfo> getAppInfo();

     @GET
     @Streaming
     Observable<ResponseBody> downloadNewApp(@Url String url);


     @GET
     @Streaming
     Call<ResponseBody> downloadNewApp2(@Url String url);

}
