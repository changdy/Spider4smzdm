package com.smzdm.main;

import com.alibaba.fastjson.JSONArray;
import com.smzdm.jsonhandler.HotInfoHandler;
import com.smzdm.mapper.*;
import com.smzdm.model.*;
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
    private TimesortMapper timesortMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private JsonsMapper jsonsMapper;

    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    private CommodityTimeInfoMapper commodityTimeInfoMapper;

    @Scheduled(cron = "0 0/5 6-23 * * ?")
    //@Scheduled(cron = "0 0/5 * * * ?")
    public void startHomePageSpider() {
        JSONArray jsonArray = homePageSpider.getJSONArray();
        Timesort timesort = timesortMapper.selectByPrimaryKey(1);
        HotArray hotArray = hotInfoHandler.parseJSONArray(jsonArray, timesort.getTimesort());
        List<Commodity> commodityList = hotArray.getCommodityList();
        if (commodityList.size()> 0) {
            commodityMapper.insertList(commodityList);
            jsonsMapper.insertList(hotArray.getJsonsList());
            List<Relation> relationList = hotArray.getRelationList();
            if (relationList.size()>0) {
                relationMapper.insertList(relationList);
            }
            timesortMapper.updateByPrimaryKey(new Timesort(1,commodityList.get(0).getTimeSort()));
        }
        commodityTimeInfoMapper.insertList(hotArray.getCommodityTimeInfoList());
    }
}
