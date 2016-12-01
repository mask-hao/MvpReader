package com.zhanghao.reader.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhanghao on 2016/11/20.
 */

public class ZhiHuStories implements Serializable,DisplayItem{

    @Override
    public String toString() {
        return "ZhiHuStories{" +
                "type=" + type +
                ", id=" + id +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                ", images=" + images +
                '}';
    }



    /**
     * images : ["http://pic3.zhimg.com/b53ad5494d565ca2e7aafb8f8bbe16ca.jpg"]
     * type : 0
     * id : 8924887
     * ga_prefix : 102721
     * title : 一部融合了人类智慧和精神的科幻史诗，让无数美国人痴迷
     */

    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private int id;
    @SerializedName("ga_prefix")
    private String ga_prefix;
    @SerializedName("title")
    private String title;
    @SerializedName("images")
    private List<String> images;

    public static ZhiHuStories objectFromData(String str) {

        return new Gson().fromJson(str, ZhiHuStories.class);
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
