package com.zhanghao.reader.utils;

import com.zhanghao.reader.R;

/**
 * Created by zhanghao on 2016/11/20.
 */

public enum MainMenu{
    ZHIHU(R.string.frag_zhihu_title,R.drawable.ic_zhihu_24dp),
    GANKDAILY(R.string.frag_gankio_title,R.drawable.ic_photo_24dp),
    GANKANDROID(R.string.frag_gank_android_title,R.drawable.ic_gank_24dp),
    TEST(R.string.frag_zhihu_title,R.drawable.ic_gank_24dp);
    private int title;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    private int icon;

    MainMenu(int title,int icon){
        this.title=title;
        this.icon=icon;
    }
}
