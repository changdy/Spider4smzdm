package com.smzdm.service.impl;

import com.smzdm.mapper.CommodityMapper;
import com.smzdm.mapper.CommodityTimeInfoMapper;
import com.smzdm.model.CommodityParams;
import com.smzdm.service.QueryCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Changdy on 2017/3/21.
 */
@Service
public class QueryCommodityServiceImpl implements QueryCommodityService {
    private final CommodityMapper commodityMapper;

    private final CommodityTimeInfoMapper commodityTimeInfoMapper;

    @Autowired
    public QueryCommodityServiceImpl(CommodityMapper commodityMapper, CommodityTimeInfoMapper commodityTimeInfoMapper) {
        this.commodityMapper = commodityMapper;
        this.commodityTimeInfoMapper = commodityTimeInfoMapper;
    }

    @Override
    public Map<String, Object> queryCommodity(CommodityParams commodityParams) {
        List<Map<String, Object>> commodityList = commodityMapper.queryCommodity(commodityParams);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", commodityList);
        if (commodityList != null) {
            for (Map<String, Object> commodityMap : commodityList) {
                Integer flag = (Integer) commodityMap.get("flag");
                if (flag == null || flag == 0) {
                    //非发现
                    String comment = commodityTimeInfoMapper.getLastComment((Long) commodityMap.get("articleId"), false);
                    if (comment.equals("0/0/0")) {
                        commodityMap.put("comment", 0);
                    } else {
                        commodityMap.put("comment", comment);
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
            int size = commodityList.size();
            if (size == commodityParams.getLimit()) {
                resultMap.put("total", commodityMapper.getCommodityCount(commodityParams));
            } else {
                resultMap.put("total", size);
            }
        } else {
            resultMap.put("total", 0);
        }
        return resultMap;
    }
}
