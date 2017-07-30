package com.smzdm.mapper;

import com.smzdm.model.CommodityFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Changdy on 2017/6/13.
 */
public interface CommodityFilterMapper {

    int insert(CommodityFilter commodityFilter);

    int update(CommodityFilter commodityFilter);

    int deleteByIds(String ids);

    List<CommodityFilter> selectAll();

    List<CommodityFilter> queryList(Map<String, Object> parameterMap);

    int getCount(Map<String, Object> parameterMap);
}
