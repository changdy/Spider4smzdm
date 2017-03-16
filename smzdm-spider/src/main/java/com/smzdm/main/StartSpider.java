package com.smzdm.main;

import com.alibaba.fastjson.JSONArray;
import com.smzdm.jsonhandler.HotInfoHandler;
import com.smzdm.mapper.*;
import com.smzdm.model.Commodity;
import com.smzdm.model.HotArray;
import com.smzdm.model.Relation;
import com.smzdm.model.TimeSort;
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
    private HotInfoHandler hotInfoHandler;
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

    @Scheduled(cron = "0 0/5 * * * ?")
    public void startHomePageSpider() {
        JSONArray jsonArray = homePageSpider.getJSONArray();
        TimeSort timesort = timeSortMapper.selectByPrimaryKey(1);
        HotArray hotArray = hotInfoHandler.parseJSONArray(jsonArray, timesort.getTimeSort());
        List<Commodity> commodityList = hotArray.getCommodityList();
        if (commodityList.size() > 0) {
            commodityMapper.insertList(commodityList);
            jsonsMapper.insertList(hotArray.getJsonsList());
            List<Relation> relationList = hotArray.getRelationList();
            if (relationList.size() > 0) {
                relationMapper.insertList(relationList);
            }
            timeSortMapper.updateByPrimaryKey(new TimeSort(1, commodityList.get(0).getTimeSort()));
        }
        commodityTimeInfoMapper.insertList(hotArray.getCommodityTimeInfoList());
    }
}
