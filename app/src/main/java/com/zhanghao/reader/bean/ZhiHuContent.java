package com.zhanghao.reader.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhanghao on 2016/11/19.
 */

public class ZhiHuContent  implements Serializable{

    /**
     * body : <div class="main-wrap content-wrap">
     <div class="headline">

     <div class="img-place-holder"></div>
     </div>
     <div class="content-inner">
     <div class="question">
     <h2 class="question-title"></h2>

     <div class="answer">

     <div class="meta">
     <img class="avatar" src="http://pic1.zhimg.com/da8e974dc_is.jpg">
     <span class="author">知乎用户，</span><span class="bio">生命潦草，我在弯腰</span>
     </div>

     <div class="content">
     <p>虽然这个问题提得很简单，但回答起来却并不容易，<strong>因为存在很多种不同的情况。</strong></p>
     <p>1.前言</p>
     <p>1.1 首先要确定的是，<strong>这个药多长时间吃一次？</strong></p>
     <p>有 1 天吃 1 次的药，有 1 天吃好几次的药，也有几天才吃一次的药，还有的药是必要时服用的，不同的情况，会对应不同的影响。</p>
     <p>因为题主的问题里有&ldquo;按时&rdquo;的字眼，那么我们姑且设定为，<strong>漏服了一次药的情况吧。</strong></p>
     <p>1.2 其次要确定的是，<strong>这个药的作用是什么？</strong></p>
     <p>有的药是控制血压血糖，有的药是抑制免疫系统，有的药是减少你体内的感染病原体，还有的药只是控制某种症状。</p>
     <p>不同的作用，漏服的影响也不同，我们挑几类典型的说说。</p>
     <p><strong>2.为什么要按时服药？</strong></p>
     <p><a href="https://www.zhihu.com/question/40776650/answer/88178467">吃药为什么要一天二或三次，吃几天，而不是一次性吃完？ - 知乎用户的回答 - 知乎</a></p>
     <p>里面有个图，说明了血药浓度对疾病治疗的意义，评论区有人指出该图不够准确，<strong>因为药物的吸收曲线应该是对数的</strong>，在此特别说明。当然如果有人能提供非常准确的曲线，我也很乐意贴过来。</p>
     <p>3.几类药物漏服的影响</p>
     <p><strong>3.1 慢性病用药（降压药、降糖药、抗癫痫药等等）</strong></p>
     <p><strong>漏服药当然会造成血压、血糖等指标不平稳了，</strong>高血压的患者如果漏服药，可能将造成血压上升，心脑血管疾病发作的风险增加；降糖药漏服，可能将造成血糖上升，出现一些糖尿病急症的风险增加；癫痫药漏服，当然是癫痫发作风险升高了。</p>
     <p>值得一提的是，几类降糖药（胰岛素促泌剂或胰岛素）漏服有风险，<strong>但重复用药的风险则更高</strong>，重复用药可能导致胰岛素过多而造成低血糖，严重者还可能有生命危险。</p>
     <p><strong>3.2 化学治疗药物（抗菌药、抗病毒药、抗结核药、抗癌药等）</strong></p>
     <p><strong>漏服可能造成血药浓度低于正常水平，而病原体就有可能借由这一点点的窗口完成耐药的进化。</strong>当然与上一类药相比，这一类漏服的危害可能并不会立即就表现得那么明显，但它的影响会慢慢体现出来，长期而言危害仍然是相当大的。</p>
     <p><strong>这一类药物有点好处就是过了一两个小时发现漏服了，有的还能补服，能够弥补一些漏服带来的影响。</strong>不像上一类，吃完饭一个小时发现没打胰岛素，那时可是不能再打的了。</p>
     <p><strong>3.3 控制症状的药</strong></p>
     <p>比如有的感冒药，只是缓解咳嗽、鼻塞、流涕、周身酸痛、发热等症状的，这类药本来也不固定服药时间，只是在症状较重，难以耐受时才服用（但因为药物过量可能对身体有较大损害，一般会限定每日最大用量）。不难受就不服，<strong>所以也不存在漏服的说法</strong>，给药间隔不一影响也不明显。</p>
     <p>3.4 在药动学中还有一个概念，即稳态，是指长期使用的药物在经过几次给药及代谢后，达到吸收速度与消除速度接近，血药浓度稳定在一定的水平的状态，理想状况下达稳需要过 4-5 个半衰期，<strong>达稳后如果漏服，无疑将破坏稳态</strong>，后面补救后再次达稳，或许是很久以后的事了。</p>
     <p>4.当然还有很多没法归入上面几种类型的药，比如糖皮质激素等，本来用法就比较复杂，漏服的影响就更为复杂，总体而言，<strong>漏服对于很多药物来讲都是一种有危害的行为</strong>，如何减少或避免漏服呢？</p>
     <p><strong>4.1 尽量选用设计上能减少漏服的药品。</strong></p>
     <p><strong>比如很多慢性病进口药就设计成 1 盒 7 片，每天 1 片，</strong>甚至有的药在包装上还标明了&ldquo;星期一&rdquo;、&ldquo;星期二&rdquo;这样的字眼，如果配合得当，想想今天是星期几就能判断服了药没有，这样就能减少漏服的发生。</p>
     <p><strong>4.2 使用特制的装药盒</strong></p>
     <p>一些小药盒能够将药品分成一周 7 天，甚至每日 3 次的服药，有些有多种慢性病的中老年人可以花点时间，把那些药分好，这样就能方便按时服用。</p>
     <p><strong>4.3 使用 APP 辅助提醒</strong></p>
     <p>一些 APP 带有服药提醒功能，可以借助其来提醒服药，减少漏服。</p>
     <p><strong>4.4 做笔记</strong></p>
     <p>一些不会用 APP 的老年人也可以用做笔记的方式，服完药就记上，避免漏服或重复。</p>
     </div>
     </div>


     <div class="view-more"><a href="http://www.zhihu.com/question/52693741">查看知乎讨论<span class="js-question-holder"></span></a></div>

     </div>


     </div>
     </div>
     * image_source : Yestone.com 版权图片库
     * title : 有一顿药没按时吃，之前吃的是不是失效了呀？
     * image : http://pic3.zhimg.com/93033b37984ba6ec22eb5c3048545b46.jpg
     * share_url : http://daily.zhihu.com/story/8986393
     * js : []
     * ga_prefix : 111913
     * images : ["http://pic1.zhimg.com/48d8b668cebd30b0f3b4b89e8f6cb4a0.jpg"]
     * type : 0
     * id : 8986393
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public static ZhiHuContent objectFromData(String str) {

        return new Gson().fromJson(str, ZhiHuContent.class);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
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

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
