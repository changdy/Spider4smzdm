package com.smzdm.mapper;

import com.smzdm.model.CommodityFilter;

import java.util.List;

/**
 * Created by Changdy on 2017/6/13.
 */
public interface CommodityFilterMapper {

    int insert(CommodityFilter commodityFilter);

    List<CommodityFilter> selectAll();
}
