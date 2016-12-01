package com.zhanghao.reader.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhanghao on 2016/11/20.
 */

public class ZhiHuTopStories implements Serializable{

    @Override
    public String toString() {
        return "ZhiHuTopStories{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    /**
     * image : http://pic2.zhimg.com/c22188790004863f1081b4f7c4702cf5.jpg
     * type : 0
     * id : 8995052
     * ga_prefix : 112017
     * title : 知乎好问题 · 以 iPhone 为例，手机拍照有哪些技巧？
     */

    @SerializedName("image")
    private String image;
    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private int id;
    @SerializedName("ga_prefix")
    private String ga_prefix;
    @SerializedName("title")
    private String title;

    public static ZhiHuTopStories objectFromData(String str) {

        return new Gson().fromJson(str, ZhiHuTopStories.class);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
