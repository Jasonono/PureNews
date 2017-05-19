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
    // 足球id
    public static final String FOOTBALL_ID = "T1399700447917";
    // 娱乐id
    public static final String ENTERTAINMENT_ID = "T1348648517839";
    // 体育id
    public static final String SPORTS_ID = "T1348649079062";
    // 财经id
    public static final String FINANCE_ID = "T1348648756099";
    // 科技id
    public static final String TECH_ID = "T1348649580692";
    // 电影id
    public static final String MOVIE_ID = "T1348648650048";

    /**
     * 获取网址
     *
     * @param id
     * @param index
     * @return
     */
    public static String getNewsUrl(String id, int index) {
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
