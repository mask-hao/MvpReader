package com.zhanghao.reader.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhanghao on 2016/11/16.
 */

public class ZhiHuLatestItem implements Serializable{

    /**
     * date : 20161116
     * stories : [{"images":["http://pic2.zhimg.com/8465e9fbfdd9376fccb7565690ea573d.jpg"],"type":0,"id":8983126,"ga_prefix":"111616","title":"写了好多畅销悬疑故事，这是我的一点经验"},{"images":["http://pic2.zhimg.com/c78c08294d4037c4b90485dcfc611685.jpg"],"type":0,"id":8983184,"ga_prefix":"111615","title":"Snap 居然估值 250 亿美元，可能是阿里之后美国最大 IPO"},{"images":["http://pic4.zhimg.com/0d2d2d09f12f0e38be394920582a6a63.jpg"],"type":0,"id":8983053,"ga_prefix":"111614","title":"京东金融被分拆，又一个 A 股千亿市值的梦想要升起来了"},{"images":["http://pic1.zhimg.com/f41010c93dc76cf204af75eb3b3d463c.jpg"],"type":0,"id":8983082,"ga_prefix":"111613","title":"古人说金子银子不要放在一起，好像有点道理"},{"images":["http://pic2.zhimg.com/a0331909418bf50db99858372f276ff1.jpg"],"type":0,"id":8982440,"ga_prefix":"111612","title":"大误 · 「我想哭但是哭不出来」"},{"images":["http://pic1.zhimg.com/018bcd77e34757bbee5e799fba471770.jpg"],"type":0,"id":8981707,"ga_prefix":"111611","title":"关于秋裤，这里他们说错了的和还没说过的"},{"images":["http://pic4.zhimg.com/c68d6db744b15d7eab678000827cebcb.jpg"],"type":0,"id":8981352,"ga_prefix":"111609","title":"美国新总统怎么选下属？有的看能力，有的嘛，也看关系"},{"images":["http://pic2.zhimg.com/bde86416aebbacbb2f686c472e7957ad.jpg"],"type":0,"id":8980953,"ga_prefix":"111608","title":"决定停止烧钱之后，贾跃亭拿到了来自校友的 6 亿美金投资"},{"images":["http://pic2.zhimg.com/61f5263177bdbbd52b3d73ee566c6dc1.jpg"],"type":0,"id":8981746,"ga_prefix":"111607","title":"在地铁里，捕捉到属于这座城市的表情"},{"images":["http://pic2.zhimg.com/4dc5eaceb9199696513cc4fe06978e99.jpg"],"type":0,"id":8980762,"ga_prefix":"111607","title":"iPhone 电量未耗尽自动关机，这次可能没法只怪天冷"},{"images":["http://pic1.zhimg.com/e8d7612ee70c4026dcf6d64601e2f5c8.jpg"],"type":0,"id":8981295,"ga_prefix":"111607","title":"回头想想，为什么「双十一」的红包规则那么复杂？"},{"images":["http://pic2.zhimg.com/e8897a44f54a6bbbd64831fbc1d1baed.jpg"],"type":0,"id":8981278,"ga_prefix":"111607","title":"读读日报 24 小时热门 TOP 5 · 这部 9.7 分的神作"},{"images":["http://pic3.zhimg.com/e82ad44ab935114d75434b48fc3ee4b6.jpg"],"type":0,"id":8981646,"ga_prefix":"111606","title":"瞎扯 · 如何正确地吐槽"},{"images":["http://pic3.zhimg.com/52768f64b91643e339be7284dd3e7e2a.jpg"],"type":0,"id":8979814,"ga_prefix":"111606","title":"这里是广告 · 来来来，我给你讲讲约会的艺术"}]
     * top_stories : [{"image":"http://pic2.zhimg.com/f7f988f18a83b184d076b484e3946469.jpg","type":0,"id":8983184,"ga_prefix":"111615","title":"Snap 居然估值 250 亿美元，可能是阿里之后美国最大 IPO"},{"image":"http://pic3.zhimg.com/959030b2b3ac0f2e65fb67fdcad4ae72.jpg","type":0,"id":8983053,"ga_prefix":"111614","title":"京东金融被分拆，又一个 A 股千亿市值的梦想要升起来了"},{"image":"http://pic4.zhimg.com/5e8db2424b69f3627b849cf99b19f42f.jpg","type":0,"id":8980953,"ga_prefix":"111608","title":"决定停止烧钱之后，贾跃亭拿到了来自校友的 6 亿美金投资"},{"image":"http://pic3.zhimg.com/28766193090c70dc48afdbaca1eb6d2a.jpg","type":0,"id":8980762,"ga_prefix":"111607","title":"iPhone 电量未耗尽自动关机，这次可能没法只怪天冷"},{"image":"http://pic4.zhimg.com/9f87a09627c0835bcae0df12f68bbd1f.jpg","type":0,"id":8981295,"ga_prefix":"111607","title":"回头想想，为什么「双十一」的红包规则那么复杂？"}]
     */

    @SerializedName("date")
    private String date;

    public List<ZhiHuStories> getStories() {
        return stories;
    }

    public void setStories(List<ZhiHuStories> stories) {
        this.stories = stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZhiHuTopStories> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<ZhiHuTopStories> top_stories) {
        this.top_stories = top_stories;
    }

    @SerializedName("stories")
    private List<ZhiHuStories> stories;
    @SerializedName("top_stories")
    private List<ZhiHuTopStories> top_stories;

}
