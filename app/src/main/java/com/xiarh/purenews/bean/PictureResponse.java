package com.xiarh.purenews.bean;

import java.util.List;

/**
 * Created by xiarh on 2017/5/23.
 */

public class PictureResponse {

    /**
     * col : 美女
     * tag : 全部
     * tag3 :
     * sort : 0
     * totalNum : 16870
     * startIndex : 0
     * returnNumber : 20
     * imgs :
     */

    private String col;
    private String tag;
    private String tag3;
    private String sort;
    private int totalNum;
    private int startIndex;
    private int returnNumber;
    private List<PictureBean> imgs;

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(int returnNumber) {
        this.returnNumber = returnNumber;
    }

    public List<PictureBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<PictureBean> imgs) {
        this.imgs = imgs;
    }
}
