package com.zhanghao.reader.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class GankVideoItem {

    /**
     * error : false
     * results : [{"_id":"575bf111421aa9579d64b5b9","createdAt":"2016-06-11T19:08:01.68Z","desc":"【文曰小强】8分钟速读《魔兽》电影原著之《最后的守护者》（上部）","publishedAt":"2016-06-12T12:04:04.308Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av4775435/","used":true,"who":"LHF"},{"_id":"5756adfc421aa97587887c66","createdAt":"2016-06-07T19:20:28.541Z","desc":"报考风暴","publishedAt":"2016-06-08T12:39:36.270Z","source":"chrome","type":"休息视频","url":"http://weibo.com/p/2304447241a681a674f463d92126d3e07ca164","used":true,"who":"AndWang"},{"_id":"575599b0421aa9758defcefa","createdAt":"2016-06-06T23:41:36.90Z","desc":"想象一下所有人类突然消失，地球会变成什么样？脑洞巨大[笑cry]","publishedAt":"2016-06-07T11:43:18.947Z","source":"chrome","type":"休息视频","url":"http://weibo.com/p/23044463f7ecc4b9345f8ec3a069b5aec8a410","used":true,"who":"LHF"},{"_id":"574efe45421aa910a742e7a1","createdAt":"2016-06-01T23:24:53.555Z","desc":"黑洞里面到底是什么样的？@柚子木字幕组","publishedAt":"2016-06-06T12:24:22.149Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av4813148/","used":true,"who":"LHF"},{"_id":"56f154ae677659164d5643de","createdAt":"2016-03-22T22:20:30.111Z","desc":"九分钟看完日本历史，好欢乐的节奏，你们感受下！ ","publishedAt":"2016-06-03T11:42:44.370Z","source":"chrome","type":"休息视频","url":"http://weibo.com/p/230444cec5d77290457c1a2f6429fd26e418de","used":true,"who":"LHF"},{"_id":"574efc86421aa910abe2bfb4","createdAt":"2016-06-01T23:17:26.143Z","desc":"【阿斗】几分钟解说高分励志电影《当幸福来敲门》威尔史密斯力作","publishedAt":"2016-06-02T11:30:26.566Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av4798701/","used":true,"who":"LHF"},{"_id":"574e5b78421aa910a742e792","createdAt":"2016-06-01T11:50:16.415Z","desc":"我的童年","publishedAt":"2016-06-01T12:01:44.959Z","source":"chrome","type":"休息视频","url":"http://www.acfun.tv/v/ac1450535?from-baifendian","used":true,"who":"代码家"},{"_id":"573745576776591c9fd0cd1b","createdAt":"2016-05-14T23:33:43.432Z","desc":"哈哈哈哈哈哈，又一部经典被毁了！有点虐，有点醉，又尼玛笑出了腹肌","publishedAt":"2016-05-31T12:12:06.424Z","source":"chrome","type":"休息视频","url":"http://www.miaopai.com/show/k~snfSzpnxdDY6NEXBXpdg__.htm","used":true,"who":"LHF"},{"_id":"56cc6d1d421aa95caa7077a9","createdAt":"2015-08-18T09:58:44.232Z","desc":"有些事只有女生才能做，男生做的话会。。","publishedAt":"2016-05-30T08:55:19.194Z","type":"休息视频","url":"http://video.weibo.com/show?fid=1034:87ae0031088d66e5d3fdab3a059c9104","used":true,"who":"张涵宇"},{"_id":"573fd1d86776590999ef0e9b","createdAt":"2016-05-21T11:11:20.992Z","desc":"那些女生做起来很正常，男生做起来很奇怪的事...[笑cry]","publishedAt":"2016-05-27T11:56:25.219Z","source":"chrome","type":"休息视频","url":"http://weibo.com/p/2304442cb9324b3d20b3bd16210c9ebb159bb0","used":true,"who":"lxxself"},{"_id":"5745342a6776594b08b2d6f9","createdAt":"2016-05-25T13:12:10.473Z","desc":"Facebook 二号人物 Sheryl Sandberg 在伯克利的 2016 毕业演讲","publishedAt":"2016-05-26T11:52:42.430Z","source":"web","type":"休息视频","url":"http://v.youku.com/v_show/id_XMTU3ODUwNTY5Mg==.html?from=s1.8-1-1.2","used":true,"who":"Leo"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public static GankVideoItem objectFromData(String str) {

        return new Gson().fromJson(str, GankVideoItem.class);
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

    public static class ResultsBean implements DisplayItem {
        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    '}';
        }

        /**
         * _id : 575bf111421aa9579d64b5b9
         * createdAt : 2016-06-11T19:08:01.68Z
         * desc : 【文曰小强】8分钟速读《魔兽》电影原著之《最后的守护者》（上部）
         * publishedAt : 2016-06-12T12:04:04.308Z
         * source : chrome
         * type : 休息视频
         * url : http://www.bilibili.com/video/av4775435/
         * used : true
         * who : LHF
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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
