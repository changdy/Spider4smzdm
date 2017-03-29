package com.smzdm.main;

import com.alibaba.fastjson.JSONArray;
import com.smzdm.service.DiscoveryHandler;
import com.smzdm.service.HomePageHandler;
import com.smzdm.mapper.*;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityContent;
import com.smzdm.model.Relation;
import com.smzdm.model.TimeSort;
import com.smzdm.spider.DiscoverySpider;
import com.smzdm.spider.HomePageSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Changdy on 2017/3/7.
 */
@Service
public class StartSpider {
    private final HomePageSpider homePageSpider;

    private final HomePageHandler homePageHandler;
    private final TimeSortMapper timeSortMapper;
    private final CommodityMapper commodityMapper;
    private final JsonsMapper jsonsMapper;
    private final RelationMapper relationMapper;
    private final CommodityTimeInfoMapper commodityTimeInfoMapper;
    private final DiscoverySpider discoverySpider;
    private final DiscoveryHandler discoveryHandler;

    @Autowired
    public StartSpider(HomePageSpider homePageSpider, HomePageHandler homePageHandler, TimeSortMapper timeSortMapper, CommodityMapper commodityMapper, JsonsMapper jsonsMapper, RelationMapper relationMapper, CommodityTimeInfoMapper commodityTimeInfoMapper, DiscoverySpider discoverySpider, DiscoveryHandler discoveryHandler) {
        this.homePageSpider = homePageSpider;
        this.homePageHandler = homePageHandler;
        this.timeSortMapper = timeSortMapper;
        this.commodityMapper = commodityMapper;
        this.jsonsMapper = jsonsMapper;
        this.relationMapper = relationMapper;
        this.commodityTimeInfoMapper = commodityTimeInfoMapper;
        this.discoverySpider = discoverySpider;
        this.discoveryHandler = discoveryHandler;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void startHomePageSpider() {
        JSONArray jsonArray = homePageSpider.getJSONArray();
        CommodityContent commodityContent = homePageHandler.parseJSONArray(jsonArray, timeSortMapper.selectByPrimaryKey(1).getTimeSort());
        List<Commodity> commodityList = commodityContent.getCommodityList();
        if (commodityList.size() > 0) {
            commodityMapper.insertList(commodityList);
            jsonsMapper.insertList(commodityContent.getJsonsList());
            List<Relation> relationList = commodityContent.getRelationList();
            if (relationList.size() > 0) {
                relationMapper.insertList(relationList);
            }
            timeSortMapper.updateByPrimaryKey(new TimeSort(1, commodityList.get(0).getTimeSort()));
        }
        commodityTimeInfoMapper.insertList(commodityContent.getCommodityTimeInfoList());
    }


    @Scheduled(cron = "30 0/3 * * * ?")
    public void startDiscoverySpider(){
        CommodityContent commodityContent = discoveryHandler.parseJSONArray(discoverySpider.getJSONArray(), timeSortMapper.selectByPrimaryKey(2).getTimeSort());
        List<Commodity> commodityList = commodityContent.getCommodityList();
        if (commodityList.size() > 0) {
            commodityMapper.insertList(commodityList);
            jsonsMapper.insertList(commodityContent.getJsonsList());
            timeSortMapper.updateByPrimaryKey(new TimeSort(2, commodityList.get(0).getTimeSort()));
        }
        commodityTimeInfoMapper.insertList(commodityContent.getCommodityTimeInfoList());
    }
}
