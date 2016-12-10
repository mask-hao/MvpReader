package com.zhanghao.reader.bean;

import com.google.gson.Gson;

/**
 * Created by zhanghao on 2016/12/9.
 */

public class ZhiHuStartImgBean {

    /**
     * text : Katie Barrett
     * img : https://pic2.zhimg.com/v2-4f0e9d2348214669d805a18ed153f731_xxdpi.jpg
     */

    private String text;
    private String img;

    public static ZhiHuStartImgBean objectFromData(String str) {

        return new Gson().fromJson(str, ZhiHuStartImgBean.class);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
