package com.zhanghao.reader.api.gank;

import com.zhanghao.reader.bean.StartImgBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zhanghao on 2017/3/12.
 */

public interface StartImgService {
    @GET("/GankWeb/api/start-image")
    Observable<StartImgBean> getStartImg();
}
