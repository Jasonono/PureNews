package com.xiarh.purenews.bean;

/**
 * Created by xiarh on 2017/5/9.
 */

public class NewsBean {
    /**
     * ctime : 2017-05-16 14:37
     * title : 前美国商务部长：美国退出TPP是不理性的决定
     * description : 搜狐国际
     * picUrl : http://photocdn.sohu.com/20170516/Img493203719_ss.jpg
     * url : http://news.sohu.com/20170516/n493212660.shtml
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
