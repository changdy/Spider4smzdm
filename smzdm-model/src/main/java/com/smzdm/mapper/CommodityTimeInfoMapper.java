package com.smzdm.mapper;

import com.smzdm.model.CommodityTimeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityTimeInfoMapper {
    int insert(CommodityTimeInfo record);

    CommodityTimeInfo selectByPrimaryKey(Integer id);

    List<CommodityTimeInfo> selectAll();

    int insertList(List<CommodityTimeInfo> commodityTimeInfoList);

    int deleteByArticleIds(String articleIds);

    int insertListToLast(List<CommodityTimeInfo> commodityTimeInfoList);
}