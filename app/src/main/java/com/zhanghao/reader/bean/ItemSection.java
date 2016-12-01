package com.zhanghao.reader.bean;

/**
 * Created by zhanghao on 2016/11/25.
 */

public class ItemSection implements DisplayItem ,GankDisplayItem{
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public ItemSection(String date) {
        this.section = date;
    }

    private String section;

    @Override
    public String getDesc() {
        return null;
    }
    @Override
    public String getUrl() {
        return null;
    }
}
