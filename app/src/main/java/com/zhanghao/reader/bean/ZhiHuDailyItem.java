package com.zhanghao.reader.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class ZhiHuDailyItem implements Serializable{

    /**
     * date : 20161027
     * stories : [{"images":["http://pic4.zhimg.com/62088db7c668cfe158ef751c64e184d7.jpg"],"type":0,"id":8854438,"ga_prefix":"102722","title":"大事 · 执行死刑"},{"images":["http://pic3.zhimg.com/b53ad5494d565ca2e7aafb8f8bbe16ca.jpg"],"type":0,"id":8924887,"ga_prefix":"102721","title":"一部融合了人类智慧和精神的科幻史诗，让无数美国人痴迷"},{"images":["http://pic2.zhimg.com/d4a1cccd485dedd982542c1ce15daabd.jpg"],"type":0,"id":8921106,"ga_prefix":"102720","title":"如果世界上有后悔药，会卖多少钱呢？"},{"images":["http://pic3.zhimg.com/6b605911ec79b807f232193eb57d2caa.jpg"],"type":0,"id":8925329,"ga_prefix":"102719","title":"没用互联网思维，OPPO 和 vivo 完成了销量上的逆袭"},{"images":["http://pic3.zhimg.com/ecd1a6e017674cce146223af91269cda.jpg"],"type":0,"id":8917959,"ga_prefix":"102718","title":"一毕业就拿着高薪，这真的是我应得的吗？"},{"images":["http://pic1.zhimg.com/73a476dcaba44629fd2c930f7eb727d8.jpg"],"type":0,"id":8925082,"ga_prefix":"102717","title":"知乎好问题 · 如何挑选实木与复合板材？各有哪些优缺点？"},{"images":["http://pic1.zhimg.com/6c3ea14cce0e8af2caa529519c6873f8.jpg"],"type":0,"id":8923177,"ga_prefix":"102716","title":"想到大脑里有个天然的「测谎仪」，你还敢说谎吗？"},{"title":"苹果地瓜仙人掌我都认识，怎么到了海里就成了这副模样？","ga_prefix":"102715","images":["http://pic3.zhimg.com/8f66ce30a9a344ba2a8025ca8f597b86.jpg"],"multipic":true,"type":0,"id":8919124},{"images":["http://pic3.zhimg.com/e04ba5d71402638645bc6fc5b7deaa02.jpg"],"type":0,"id":8924241,"ga_prefix":"102714","title":"吃了那么多香肠培根，是时候聊聊「致癌」这件事了"},{"title":"微软的这场发布会，重点可能比你所留意到的更多","ga_prefix":"102713","images":["http://pic1.zhimg.com/bbec3e6352dac8a4b2fb6a4f82c24264.jpg"],"multipic":true,"type":0,"id":8924483},{"images":["http://pic1.zhimg.com/8f3f2e1e2ea16336406d45f22390118c.jpg"],"type":0,"id":8922120,"ga_prefix":"102712","title":"大误 · 不是婚宴，是鸿门宴"},{"images":["http://pic1.zhimg.com/b1c88ecec7262fc1d2e12ce15e8f5fc0.jpg"],"type":0,"id":8923283,"ga_prefix":"102711","title":"「小张，给你一周，写好这个项目的调查报告交给我」"},{"images":["http://pic3.zhimg.com/b326b49a21c183eb79ce838346c7c9de.jpg"],"type":0,"id":8921737,"ga_prefix":"102710","title":"年收入 12 万明明很高，为什么提高税率还叫苦不迭？"},{"images":["http://pic1.zhimg.com/fa786f9a8b492a5643bad9d2fbc157cc.jpg"],"type":0,"id":8921595,"ga_prefix":"102709","title":"各国移民涌进来，对东道国是好事还是坏事？"},{"images":["http://pic3.zhimg.com/cb7862453873948a5f6b07bcf0b4e742.jpg"],"type":0,"id":8922354,"ga_prefix":"102708","title":"爱情里，也上演着一场「权力的游戏」"},{"title":"世外桃源般的诗意栖居，说的是这些建筑","ga_prefix":"102707","images":["http://pic2.zhimg.com/cdc51bbbe9aa4381ad8aa3af6c5c8011.jpg"],"multipic":true,"type":0,"id":8923141},{"images":["http://pic1.zhimg.com/068ed4bf548978b28a9e15f280ce6bbc.jpg"],"type":0,"id":8837436,"ga_prefix":"102707","title":"面试官眼里的「学习能力」，不一定是你想的那样"},{"images":["http://pic1.zhimg.com/edbd9202c3166319f0e98b6a143841bc.jpg"],"type":0,"id":8922424,"ga_prefix":"102707","title":"商场和影院，究竟谁养活谁？"},{"images":["http://pic2.zhimg.com/70c8fae52f993b4f416d6e3839ecd7f5.jpg"],"type":0,"id":8923090,"ga_prefix":"102707","title":"读读日报 24 小时热门 TOP 5 · 双 MVP 新上路，撞上老司机"},{"images":["http://pic4.zhimg.com/0e18bcf7e509b5d764e2a39ffcb6f0a7.jpg"],"type":0,"id":8919203,"ga_prefix":"102706","title":"瞎扯 · 如何正确地吐槽"}]
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

    @SerializedName("stories")
    private List<ZhiHuStories> stories;

}
