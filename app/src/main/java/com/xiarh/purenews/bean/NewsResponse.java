package com.xiarh.purenews.bean;

import java.util.List;

/**
 * Created by xiarh on 2017/5/9.
 */

public class NewsResponse {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-05-16 14:37","title":"前美国商务部长：美国退出TPP是不理性的决定","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493203719_ss.jpg","url":"http://news.sohu.com/20170516/n493212660.shtml"},{"ctime":"2017-05-16 14:03","title":"日本自卫队找到失联战机上4名机组人员 安危不明","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493203719_ss.jpg","url":"http://news.sohu.com/20170516/n493207955.shtml"},{"ctime":"2017-05-16 13:18","title":"美联航风波不断 空服人员泄露飞机驾驶舱门密码","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493202383_ss.jpg","url":"http://news.sohu.com/20170516/n493202382.shtml"},{"ctime":"2017-05-16 13:37","title":"马克龙欲改变欧盟 外媒：或将受挫于外交现实","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493203719_ss.jpg","url":"http://news.sohu.com/20170516/n493203718.shtml"},{"ctime":"2017-05-16 11:28","title":"白宫：支付黑客赎金并未让任何数据恢复","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493188246_ss.jpg","url":"http://news.sohu.com/20170516/n493199681.shtml"},{"ctime":"2017-05-16 12:51","title":"白宫：勒索病毒的代码不是美国安局开发出来的","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493188565_ss.jpg","url":"http://news.sohu.com/20170516/n493199799.shtml"},{"ctime":"2017-05-16 10:57","title":"迪士尼遭黑客勒索 加勒比海盗5存被提前泄露风险","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493180886_ss.jpg","url":"http://news.sohu.com/20170516/n493184904.shtml"},{"ctime":"2017-05-16 10:59","title":"中国留澳学生10人中8个干代购 有人做到数亿美元","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493180998_ss.jpg","url":"http://news.sohu.com/20170516/n493185097.shtml"},{"ctime":"2017-05-16 11:04","title":"黑客盗取迪士尼未上映电影索取比特币赎金遭拒绝","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493183086_ss.jpg","url":"http://news.sohu.com/20170516/n493187352.shtml"},{"ctime":"2017-05-16 11:04","title":"马克龙税前月入14910欧元 和奥朗德一样","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20170516/Img493187423_ss.jpg","url":"http://news.sohu.com/20170516/n493187422.shtml"}]
     */

    private int code;
    private String msg;
    private List<NewsBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsBean> newslist) {
        this.newslist = newslist;
    }
}
