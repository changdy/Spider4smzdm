package com.smzdm.mapper;

import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommodityMapper {
    int insert(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int insertList(List<Commodity> commodityList);

    List<HashMap> getKeyInfoMap();

    List<Map<String,Object>> queryCommodity(CommodityParams CommodityParams);

    int getCommodityCount(CommodityParams CommodityParams);
}