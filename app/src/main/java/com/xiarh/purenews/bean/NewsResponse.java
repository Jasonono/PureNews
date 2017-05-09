package com.xiarh.purenews.bean;

import java.util.List;

/**
 * Created by xiarh on 2017/5/9.
 */

public class NewsResponse {

    /**
     * size : 10
     * list : [{"imgurl":"http://cms-bucket.nosdn.127.net/23e1a1e099b8457c8911916d63ad95bd20170509143411.jpeg","has_content":true,"docurl":"http://war.163.com/17/0509/14/CK0J15K4000181KT.html","id":4582,"time":"2017-05-09 14:34:18","title":"叙反政府武装人员从大马士革北郊撤离","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/9edfbc620c4e4005941ec9b65eb0edcd20170509143208.jpeg","has_content":true,"docurl":"http://war.163.com/17/0509/14/CK0ITS2U000181KT.html","id":4578,"time":"2017-05-09 14:32:30","title":"俄胜利日阅兵:大多数受阅飞行员参加过在叙行动","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/9/97/97831c09a647b3eeda353dd0bbf44cd5.jpg","has_content":true,"docurl":"http://war.163.com/17/0509/14/CK0IOLCO000181KT.html","id":4580,"time":"2017-05-09 14:29:39","title":"土耳其未遂政变后 首次有土士兵在德寻求政治避难获批","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/d/df/dfb048d7d78deb54024d154c3278d901.jpg","has_content":true,"docurl":"http://war.163.com/17/0509/14/CK0IL990000181KT.html","id":4581,"time":"2017-05-09 14:27:48","title":"俄与波兰因外交用地起争议 俄领事馆被要求还债","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/8/8c/8c7c6945088ef29d1ad037798a0b4369.png","has_content":true,"docurl":"http://war.163.com/17/0509/14/CK0IIIR0000181KT.html","id":4579,"time":"2017-05-09 14:26:20","title":"美著名军事杂志报道南海时犯小儿科错误","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/5/59/59a30b1190a911eb4e71dbd45237b2b8.jpg","has_content":true,"docurl":"http://war.163.com/17/0509/14/CK0I5I97000181KT.html","id":4570,"time":"2017-05-09 14:19:13","title":"美海军司令:国际舞台需为中国海军崛起腾空间","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/c/cd/cd068b670a9f95445195defd88ab9844.jpg","has_content":true,"docurl":"http://war.163.com/17/0509/14/CK0HHI9O000181KT.html","id":4558,"time":"2017-05-09 14:08:18","title":"美媒:中国正在制造新型地效掠海反舰导弹","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/a36396947b4942068a907e548e7203a520170509140037.jpeg","has_content":true,"docurl":"http://war.163.com/17/0509/14/CK0H44SP000181KT.html","id":4559,"time":"2017-05-09 14:00:58","title":"中国驻塞尔维亚使馆悼念三位记者烈士","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/2229ee771488459cb93b5a88f3e5df7d20170509135600.jpeg","has_content":true,"docurl":"http://war.163.com/17/0509/13/CK0GRU8T000181KT.html","id":4560,"time":"2017-05-09 13:56:29","title":"菲美\u201c肩并肩\u201d军演聚焦救灾与反恐","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/4c73d731ebbc46eda8c806ecde822cb920170509135340.jpeg","has_content":true,"docurl":"http://war.163.com/17/0509/13/CK0GNNBS000181KT.html","id":4561,"time":"2017-05-09 13:54:11","title":"索马里\u201c青年党\u201d一地区高级头目被击毙","channelname":"war"}]
     */

    private int size;
    private List<NewsBean> list;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<NewsBean> getList() {
        return list;
    }

    public void setList(List<NewsBean> list) {
        this.list = list;
    }
}
