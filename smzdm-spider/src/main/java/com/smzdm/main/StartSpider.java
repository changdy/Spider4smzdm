package com.smzdm.main;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smzdm.mapper.*;
import com.smzdm.model.*;
import com.smzdm.service.CommodityFilterHandler;
import com.smzdm.service.json.DiscoveryHandler;
import com.smzdm.service.json.HomePageHandler;
import com.smzdm.service.InfoSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Changdy on 2017/3/7.
 */
@Service
@Transactional
public class StartSpider {

    private final HomePageHandler homePageHandler;
    private final TimeSortMapper timeSortMapper;
    private final CommodityMapper commodityMapper;
    private final JsonsMapper jsonsMapper;
    private final RelationMapper relationMapper;
    private final CommodityTimeInfoMapper commodityTimeInfoMapper;
    private final DiscoveryHandler discoveryHandler;
    private final CommodityFilterHandler commodityFilterHandler;
    private final InfoSpider infoSpider;

    //首页url
    private final static String HOME_URL = "http://www.smzdm.com/homepage/json_more";
    //发现页url
    private final static List<String> DIS_URL = Arrays.asList("http://faxian.smzdm.com/json_more?page=2", "http://faxian.smzdm.com/json_more?page=1");
    //最近12小时热门url
    private final static List<String> HOT_URL = new ArrayList<>();

    //完成初始化
    static {
        String preUrl = "http://faxian.smzdm.com/json_more?filter=h3s0t0f0c0&page=";
        for (int i = 10; i > 0; i--) {
            HOT_URL.add(preUrl + i);
        }
    }

    @Autowired
    public StartSpider(HomePageHandler homePageHandler, TimeSortMapper timeSortMapper, CommodityMapper commodityMapper, JsonsMapper jsonsMapper, RelationMapper relationMapper, CommodityTimeInfoMapper commodityTimeInfoMapper, DiscoveryHandler discoveryHandler, CommodityFilterHandler commodityFilterHandler, InfoSpider infoSpider) {
        this.homePageHandler = homePageHandler;
        this.timeSortMapper = timeSortMapper;
        this.commodityMapper = commodityMapper;
        this.jsonsMapper = jsonsMapper;
        this.relationMapper = relationMapper;
        this.commodityTimeInfoMapper = commodityTimeInfoMapper;
        this.discoveryHandler = discoveryHandler;
        this.commodityFilterHandler = commodityFilterHandler;
        this.infoSpider = infoSpider;
    }


    @Scheduled(cron = "0 0/5 * * * ?")
    public void startHomePageSpider() {
        JSONArray jsonArray = infoSpider.getJSONArray(HOME_URL);
        CommodityContent commodityContent = homePageHandler.parseJSONArray(jsonArray, timeSortMapper.selectByPrimaryKey(1).getTimeSort());
        List<Commodity> commodityList = commodityContent.getCommodityList();
        if (commodityList.size() > 0) {
            commodityMapper.insertList(commodityList);
            commodityFilterHandler.validCommodity(commodityList);
            jsonsMapper.insertList(commodityContent.getJsonsList());
            List<Relation> relationList = commodityContent.getRelationList();
            if (relationList.size() > 0) {
                relationMapper.insertList(relationList);
            }
            timeSortMapper.updateByPrimaryKey(new TimeSort(1, commodityList.get(0).getTimeSort()));
        }
        List<CommodityTimeInfo> commodityTimeInfoList = commodityContent.getCommodityTimeInfoList();
        addTimeInfo(commodityTimeInfoList);
        commodityFilterHandler.validTimeInfo(commodityTimeInfoList);
        commodityFilterHandler.deleteItem(commodityTimeInfoList.get(commodityTimeInfoList.size() - 1).getArticleId());
    }


    @Scheduled(cron = "30 0/3 * * * ?")
    public void startDiscoverySpider() {
        CommodityContent commodityContent = discoveryHandler.parseJSONArray(infoSpider.getJSONArray(DIS_URL), timeSortMapper.selectByPrimaryKey(2).getTimeSort());
        List<Commodity> commodityList = commodityContent.getCommodityList();
        if (commodityList.size() > 0) {
            commodityMapper.insertList(commodityList);
            commodityFilterHandler.validCommodity(commodityList);
            jsonsMapper.insertList(commodityContent.getJsonsList());
            timeSortMapper.updateByPrimaryKey(new TimeSort(2, commodityList.get(0).getTimeSort()));
        }
        addTimeInfo(commodityContent.getCommodityTimeInfoList());
        commodityFilterHandler.validTimeInfo(commodityContent.getCommodityTimeInfoList());
    }

    private void addTimeInfo(List<CommodityTimeInfo> infoList) {
        commodityTimeInfoMapper.insertList(infoList);
        StringBuilder stringBuilder = new StringBuilder();
        for (CommodityTimeInfo commodityTimeInfo : infoList) {
            stringBuilder.append(commodityTimeInfo.getArticleId().toString()).append(",");
        }
        commodityTimeInfoMapper.deleteByArticleIds(stringBuilder.toString());
        commodityTimeInfoMapper.insertListToLast(infoList);
    }

    @Scheduled(cron = "45 29 0/6 * * ?")
    public void startHotSpider() {
        JSONArray jsonArray = infoSpider.getJSONArray(HOT_URL);
        StringBuilder articleIds = new StringBuilder();
        int length = jsonArray.size();
        for (int i = 0; i < length; i++) {
            JSONObject info = jsonArray.getJSONObject(i);
            articleIds.append(info.getString("article_id")).append(",");
        }
        List<CommodityTimeInfo> commodityTimeInfos = commodityTimeInfoMapper.selectByArticleIds(articleIds.toString());
        commodityTimeInfoMapper.deleteByArticleIds(articleIds.toString());
        Map<Long, Integer> commodityUnworth = new HashMap<>(256);
        commodityTimeInfos.forEach(info -> commodityUnworth.put(info.getArticleId(), info.getUnworthy()));
        Date date = new Date();
        for (int i = 0; i < length; i++) {
            JSONObject info = jsonArray.getJSONObject(i);
            info.put("updateTime", date);
            Long articleId = info.getLong("article_id");
            Integer value = commodityUnworth.get(articleId);
            if (value != null) {
                commodityUnworth.remove(articleId);
                info.put("article_unworth", value);
            } else {
                info.put("article_unworth", 0);
            }
        }
    }
}
