package com.xiarh.purenews.bean;

/**
 * Created by xiarh on 2017/5/31.
 */

public class WeatherListBean {

    private String type;
    private String brf;
    private int resId;

    public WeatherListBean(String type, String brf, int resId) {
        this.type = type;
        this.brf = brf;
        this.resId = resId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
