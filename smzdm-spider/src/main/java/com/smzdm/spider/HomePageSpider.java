package com.smzdm.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 精选的爬虫信息
 */
@Service
public class HomePageSpider implements PageProcessor {
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
        Spider spider = Spider.create(new HomePageSpider());
        ResultItems resultItems = spider.get("http://www.smzdm.com/json_more");
        spider.close();
        return (JSONArray) resultItems.get("json");
    }
}