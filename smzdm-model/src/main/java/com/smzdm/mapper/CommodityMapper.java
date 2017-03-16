package com.smzdm.mapper;

import com.smzdm.model.Commodity;

import java.util.HashMap;
import java.util.List;

public interface CommodityMapper {
    int insert(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int insertList(List<Commodity> commodityList);

    List<HashMap> getKeyInfoMap();
}