package com.xiarh.purenews.bean;

/**
 * Created by xiarh on 2017/5/9.
 */

public class NewsBean {
    /**
     * imgurl : http://cms-bucket.nosdn.127.net/23e1a1e099b8457c8911916d63ad95bd20170509143411.jpeg
     * has_content : true
     * docurl : http://war.163.com/17/0509/14/CK0J15K4000181KT.html
     * id : 4582
     * time : 2017-05-09 14:34:18
     * title : 叙反政府武装人员从大马士革北郊撤离
     * channelname : war
     */

    private String imgurl;
    private boolean has_content;
    private String docurl;
    private int id;
    private String time;
    private String title;
    private String channelname;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public boolean isHas_content() {
        return has_content;
    }

    public void setHas_content(boolean has_content) {
        this.has_content = has_content;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }
}
