package com.xiarh.purenews.bean;

/**
 * Created by xiarh on 2017/5/23.
 */

public class PictureBean {

    private String imageUrl;//大图
    private String title;
    private String objTag;
    private String thumbLargeUrl;//小图
    private String thumbLargeTnUrl;//小图，大小貌似是固定的

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjTag() {
        return objTag;
    }

    public void setObjTag(String objTag) {
        this.objTag = objTag;
    }

    public String getThumbLargeUrl() {
        return thumbLargeUrl;
    }

    public void setThumbLargeUrl(String thumbLargeUrl) {
        this.thumbLargeUrl = thumbLargeUrl;
    }

    public String getThumbLargeTnUrl() {
        return thumbLargeTnUrl;
    }

    public void setThumbLargeTnUrl(String thumbLargeTnUrl) {
        this.thumbLargeTnUrl = thumbLargeTnUrl;
    }
}
