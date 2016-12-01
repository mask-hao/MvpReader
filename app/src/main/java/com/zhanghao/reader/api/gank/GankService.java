package com.zhanghao.reader.api.gank;

import com.zhanghao.reader.bean.GankDailyAllItem;
import com.zhanghao.reader.bean.GankItem;
import com.zhanghao.reader.bean.GankPicItem;
import com.zhanghao.reader.bean.GankVideoItem;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhanghao on 2016/11/19.
 */

public interface GankService{

    /*
    *
    *   分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
        数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
        请求个数： 数字，大于0
        第几页：数字，大于0
    *
    *
    * */


    //技术干货
    @GET("/api/data/{type}/10/{page}")
    Observable<GankItem> getGankDaliy(
            @Path("type") String type,
            @Path("page")int page );

    //休息视频
    @GET("/api/data/休息视频/10/{page}")
    Observable<GankVideoItem> getGankVideoDaliy(
            @Path("page")int page
    );

    //福利图片
    @GET("/api/data/福利/10/{page}")
    Observable<GankPicItem> getGankPicDaliy(
            @Path("page")int page
    );


    //每天数据
    @GET("/api/day/{date}")
    Observable<GankDailyAllItem> getGankItemAll(
            @Path("date")String date
    );

}
