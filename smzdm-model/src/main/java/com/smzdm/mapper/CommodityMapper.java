package com.smzdm.mapper;

import com.smzdm.model.Commodity;

import java.util.List;

public interface CommodityMapper {
    int insert(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    List<Commodity> selectAll();

    int insertList(List<Commodity> commodityList);
}