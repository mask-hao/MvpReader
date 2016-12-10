package com.zhanghao.reader.api.zhihu;
import com.zhanghao.reader.bean.ZhiHuContent;
import com.zhanghao.reader.bean.ZhiHuDailyItem;
import com.zhanghao.reader.bean.ZhiHuStartImgBean;
import com.zhanghao.reader.bean.ZhiHuLatestItem;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhanghao on 2016/11/16.
 * 知乎Service
 */

public interface ZhiHuService {

    @GET("/api/4/news/latest")
    Observable<ZhiHuLatestItem> getLatestNews();

    @GET("/api/4/news/before/{date}")
    Observable<ZhiHuDailyItem> getDailyNews(@Path("date")String date);

    @GET("/api/4/news/{id}")
    Observable<ZhiHuContent> getNewContent(@Path("id")String id);

    @GET("/api/4/start-image/1920*1080")
    Observable<ZhiHuStartImgBean> getZhiHuFlashImg();

}
