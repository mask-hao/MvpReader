package com.zhanghao.reader.bean;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/27.
 */

public class GankDailyItem implements DisplayItem{

    private String url;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String title;
}
