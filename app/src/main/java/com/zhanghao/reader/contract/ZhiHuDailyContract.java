package com.zhanghao.reader.contract;

import com.zhanghao.reader.bean.ZhiHuDailyItem;
import com.zhanghao.reader.bean.ZhiHuLatestItem;
import com.zhanghao.reader.bean.ZhiHuStories;
import com.zhanghao.reader.bean.ZhiHuTopStories;
import com.zhanghao.reader.presenter.BasePresenter;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/16.
 * 知乎日报的契约类
 */

public interface ZhiHuDailyContract {

    interface View extends BaseView<Presenter>{
        void setUpZhiHuNewsLastestList(List<ZhiHuStories> storiesBeanList,List<ZhiHuTopStories> topNewsList,boolean isRefresh);
        //void setUpZhiHuTopNewsList();
        void UpDateZhiHuNewsList(List<ZhiHuStories> storiesBeanList);
    }

    interface Presenter extends BasePresenter{
        void getLatestZhiHuNews(boolean isRefresh);
        void getDailyNews(String date);
    }
}
