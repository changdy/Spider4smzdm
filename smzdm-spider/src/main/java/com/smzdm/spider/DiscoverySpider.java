package com.smzdm.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Changdy on 2017/3/17.
 */
@Service
public class DiscoverySpider implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUseGzip(true);

    @Override
    public void process(Page page) {
        page.putField("json", JSON.parseArray(page.getJson().get()));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public JSONArray getJSONArray() {
        Spider spider = Spider.create(new DiscoverySpider());
        List<ResultItems> resultItems = spider.getAll(Arrays.asList("http://faxian.smzdm.com/json_more?page=2", "http://faxian.smzdm.com/json_more?page=1"));
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(resultItems.get(1).get("json"));
        jsonArray.addAll(resultItems.get(0).get("json"));
        spider.close();
        return jsonArray;
    }
}
