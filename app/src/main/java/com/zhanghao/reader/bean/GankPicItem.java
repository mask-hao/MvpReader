package com.zhanghao.reader.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class GankPicItem{

    /**
     * error : false
     * results : [{"_id":"56cc6d29421aa95caa708214","createdAt":"2016-01-26T06:42:17.769Z","desc":"1.27","publishedAt":"2016-01-27T05:14:00.966Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1f0cw7swd9tj20hy0qogoo.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa708205","createdAt":"2016-01-25T09:14:17.609Z","desc":"1.26","publishedAt":"2016-01-26T04:02:34.316Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1f0buzmnacoj20f00liwi2.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7081f5","createdAt":"2016-01-25T01:59:46.62Z","desc":"1.25","publishedAt":"2016-01-25T06:59:09.1Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f0bifjrh39j20v018gwtj.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7081e4","createdAt":"2016-01-22T02:26:06.396Z","desc":"1.22","publishedAt":"2016-01-22T05:14:47.832Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f082c0b6zyj20f00f0gnr.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7081d5","createdAt":"2016-01-21T04:37:11.978Z","desc":"meizi","publishedAt":"2016-01-21T04:50:36.905Z","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f070hyadzkj20p90gwq6v.jpg","used":true,"who":"daimajia"},{"_id":"56cc6d29421aa95caa7081c6","createdAt":"2016-01-20T01:25:05.568Z","desc":"1.20","publishedAt":"2016-01-20T04:59:02.792Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f05pbp0p0yj20go0mu77b.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7081b1","createdAt":"2016-01-19T02:49:42.929Z","desc":"1.19","publishedAt":"2016-01-19T04:06:04.448Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f04m5ngwwaj20dw0kmwgn.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7081a3","createdAt":"2016-01-18T01:43:36.291Z","desc":"1.18","publishedAt":"2016-01-18T04:58:00.931Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f03emebr4jj20ez0qoadk.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa708179","createdAt":"2016-01-14T12:32:05.400Z","desc":"1.14","publishedAt":"2016-01-15T04:30:14.280Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1ezzaw04857j20p00gp40w.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa70816a","createdAt":"2016-01-14T01:57:03.789Z","desc":"1.14","publishedAt":"2016-01-14T05:39:45.154Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1ezysj9ytj5j20f00m8wh0.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa708160","createdAt":"2016-01-13T02:50:07.584Z","desc":"1.13","publishedAt":"2016-01-13T04:49:14.827Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezxog636o8j20du0kujsg.jpg","used":true,"who":"张涵宇"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public static GankPicItem objectFromData(String str) {

        return new Gson().fromJson(str, GankPicItem.class);
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements DisplayItem{
        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    '}';
        }

        /**
         * _id : 56cc6d29421aa95caa708214
         * createdAt : 2016-01-26T06:42:17.769Z
         * desc : 1.27
         * publishedAt : 2016-01-27T05:14:00.966Z
         * type : 福利
         * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1f0cw7swd9tj20hy0qogoo.jpg
         * used : true
         * who : 张涵宇
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public static ResultsBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultsBean.class);
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
