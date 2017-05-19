package com.xiarh.purenews.config;

/**
 * Created by xiarh on 2017/5/9.
 */

public class Config {

    public static int PAGE_SIZE = 10;

    public static final String NEWS_URL = "http://c.m.163.com/nc/article/";

    public static final String NEWS_END_URL = "-" + PAGE_SIZE + ".html";

    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";

    // 头条id
    public static final String HEADLINE_ID = "T1348647909107";
    // 汽车id
    public static final String CAR_ID = "T1348654060988";


    /**
     * 获取网址
     *
     * @param id
     * @param index
     * @return
     */
    public static String getUrl(String id, int index) {
        String url;
        switch (id) {
            case Config.HEADLINE_ID:
                url = Config.NEWS_URL + HEADLINE_TYPE + "/" + id + "/" + index + NEWS_END_URL;
                break;
            default:
                url = Config.NEWS_URL + OTHER_TYPE + "/" + id + "/" + index + NEWS_END_URL;
                break;
        }
        return url;
    }
}
