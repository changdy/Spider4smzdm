package com.smzdm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by Changdy on 2017/8/18.
 */
@Service
public class InfoSpider implements PageProcessor {
    private Site site = Site.me().setRetryTimes(5).setSleepTime(1500).setUseGzip(true).setRetrySleepTime(5000);

    @Override
    public void process(Page page) {
        page.putField("json", page.getRawText());
    }

    @Override
    public Site getSite() {
        return site;
    }


    public JSONArray getJSONArray(List<String> urls) {
        Spider spider = Spider.create(new InfoSpider());
        List<ResultItems> resultItems = spider.getAll(urls);
        JSONArray jsonArray = new JSONArray();
        int size = urls.size();
        for (int i = size - 1; i >= 0; i--) {
            jsonArray.addAll(JSON.parseArray(resultItems.get(i).get("json")));
        }
        spider.close();
        return jsonArray;
    }

    public JSONArray getJSONArray(String url) {
        Spider spider = Spider.create(new InfoSpider());
        ResultItems resultItems = spider.get(url);
        spider.close();
        JSONArray jsonArray = JSON.parseObject(resultItems.get("json")).getJSONArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (!jsonObject.containsKey("article_id")) {
                jsonArray.remove(i--);
            }
        }
        return jsonArray;
    }
}
