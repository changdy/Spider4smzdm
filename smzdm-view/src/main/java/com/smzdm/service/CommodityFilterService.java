package com.smzdm.service;

import com.smzdm.mapper.CommodityFilterMapper;
import com.smzdm.model.CommodityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Changdy on 2017/7/9.
 */
@Service
@Transactional
public class CommodityFilterService {
    private CommodityFilterMapper commodityFilterMapper;

    @Autowired
    public CommodityFilterService(CommodityFilterMapper commodityFilterMapper) {
        this.commodityFilterMapper = commodityFilterMapper;
    }

    public List<CommodityFilter> queryList(Map<String, Object> parameterMap) {
        return commodityFilterMapper.queryList(parameterMap);
    }

    public int getCount(Map<String, Object> parameterMap) {
        return commodityFilterMapper.getCount(parameterMap);
    }

    public int insert(CommodityFilter commodityFilter) {
        return commodityFilterMapper.insert(commodityFilter);
    }

    public int update(CommodityFilter commodityFilter) {
        return commodityFilterMapper.update(commodityFilter);
    }

}
