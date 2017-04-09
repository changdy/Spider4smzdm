package com.smzdm.service.impl;

import com.smzdm.mapper.CommodityMapper;
import com.smzdm.mapper.CommodityTimeInfoMapper;
import com.smzdm.model.CommodityParams;
import com.smzdm.service.QueryCommodityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Changdy on 2017/3/21.
 */
@Service
@Transactional
public class QueryCommodityServiceImpl implements QueryCommodityService {
    private final CommodityMapper commodityMapper;

    private final CommodityTimeInfoMapper commodityTimeInfoMapper;

    private static Logger logger = LogManager.getLogger(QueryCommodityServiceImpl.class.getName());

    @Autowired
    public QueryCommodityServiceImpl(CommodityMapper commodityMapper, CommodityTimeInfoMapper commodityTimeInfoMapper) {
        this.commodityMapper = commodityMapper;
        this.commodityTimeInfoMapper = commodityTimeInfoMapper;
    }

    @Override
    public Map<String, Object> queryCommodity(CommodityParams commodityParams) {
        List<Map<String, Object>> commodityList = commodityMapper.queryCommodity(commodityParams);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("rows", commodityList);
            if (commodityList != null) {
                for (Map<String, Object> commodityMap : commodityList) {
                    Integer flag = (Integer) commodityMap.get("flag");
                    if (flag == null || flag == 0) {
                        //非发现
                        String comment = commodityTimeInfoMapper.getLastComment((Long) commodityMap.get("articleId"), false);
                        if (comment != null) {
                            if (comment.equals("0/0/0")) {
                                commodityMap.put("comment", 0);
                            } else {
                                commodityMap.put("comment", comment);
                            }
                        }
                    } else {
                        //发现
                        String comment = commodityTimeInfoMapper.getLastComment((Long) commodityMap.get("articleId"), true);
                        if (comment.equals("0/-1/0") || comment.equals("0/0/0")) {
                            commodityMap.put("comment", 0);
                        } else {
                            commodityMap.put("comment", comment);
                        }
                    }
                }
                resultMap.put("total", commodityMapper.getCommodityCount(commodityParams));
            } else {
                resultMap.put("total", 0);
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
        }
        return resultMap;
    }
}
