package com.smzdm.service.impl;

import com.smzdm.mapper.CommodityMapper;
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

    @Autowired
    public QueryCommodityServiceImpl(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    @Override
    public Map<String, Object> queryCommodity(CommodityParams commodityParams) {
        List<Map<String, Object>> commodityList = commodityMapper.queryCommodity(commodityParams);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", commodityList);
        if (commodityList != null) {
            int size = commodityList.size();
            if (size == commodityParams.getLimit()) {
                resultMap.put("total", commodityMapper.getCommodityCount(commodityParams));
            } else {
                resultMap.put("total",size);
            }
        } else {
            resultMap.put("total", 0);
        }
        return resultMap;
    }
}
