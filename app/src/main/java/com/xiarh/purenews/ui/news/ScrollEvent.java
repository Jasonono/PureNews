package com.xiarh.purenews.ui.news;

/**
 * Created by xiarh on 2017/6/7.
 */

public class ScrollEvent {

    private String id;

    public ScrollEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
