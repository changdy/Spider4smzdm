package com.smzdm.main;

import com.alibaba.fastjson.JSONArray;
import com.smzdm.handler.HomePageHandler;
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
    @Autowired
    private HomePageSpider homePageSpider;

    @Autowired
    private HomePageHandler homePageHandler;
    @Autowired
    private TimeSortMapper timeSortMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private JsonsMapper jsonsMapper;

    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    private CommodityTimeInfoMapper commodityTimeInfoMapper;

    @Autowired
    private DiscoverySpider discoverySpider;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void startHomePageSpider() {
        JSONArray jsonArray = homePageSpider.getJSONArray();
        TimeSort timesort = timeSortMapper.selectByPrimaryKey(1);
        CommodityContent commodityContent = homePageHandler.parseJSONArray(jsonArray, timesort.getTimeSort());
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



    public void startDiscoverySpider(){
        JSONArray jsonArray = discoverySpider.getJSONArray();
        TimeSort timesort = timeSortMapper.selectByPrimaryKey(2);

    }


}
