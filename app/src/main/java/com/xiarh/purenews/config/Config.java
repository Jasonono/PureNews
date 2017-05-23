package com.xiarh.purenews.config;

/**
 * Created by xiarh on 2017/5/9.
 */

public class Config {

    public static int PAGE_SIZE = 20;

    /**
     * 新闻 http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     */
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
     * 视频 http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html
     */
    public static final String VIDEO_URL = "http://c.m.163.com/nc/video/list/";

    public static final String VIDEO_CENTER_URL = "/n/";

    public static final String VIDEO_END_URL = "-" + PAGE_SIZE + ".html";

    // 精品视频
    public static final String VIDEO_CHOICE_ID = "00850FRB";
    // 娱乐视频
    public static final String VIDEO_ENTERTAINMENT_ID = "V9LG4CHOR";
    // 搞笑视频
    public static final String VIDEO_FUN_ID = "V9LG4E6VR";
    // 热点视频
    public static final String VIDEO_HOT_ID = "V9LG4B3A0";


    /**
     * 图片 http://image.baidu.com/data/imgs?col=%E7%BE%8E%E5%A5%B3&tag=%E5%85%A8%E9%83%A8&pn=10&rn=10&from=1
     */
    public static final String PICTURE_URL = "http://image.baidu.com/data/imgs?col=";

    public static final String PICTURE_CENTER_URL = "&tag=%E5%85%A8%E9%83%A8&pn=";

    public static final String PICTURE_END_URL = "&rn=" + PAGE_SIZE + "&from=1";

    // 美女
    public static final String BEAUTY_ID = "%E7%BE%8E%E5%A5%B3";
    //动漫
    public static final String COMIC_ID = "%E5%8A%A8%E6%BC%AB";
    //摄影
    public static final String PHOTPGRAPHY_ID = "%E6%91%84%E5%BD%B1";

    /**
     * 获取新闻网址
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

    /**
     * 获取视频网址
     *
     * @param id
     * @param index
     * @return
     */
    public static String getVideoUrl(String id, int index) {
        return Config.VIDEO_URL + id + Config.VIDEO_CENTER_URL + index + Config.VIDEO_END_URL;
    }

    /**
     * 获取图片网址
     *
     * @param id
     * @param index
     * @return
     */
    public static String getPictureUrl(String id, int index) {
        return Config.PICTURE_URL + id + Config.PICTURE_CENTER_URL + index + Config.PICTURE_END_URL;
    }
}
