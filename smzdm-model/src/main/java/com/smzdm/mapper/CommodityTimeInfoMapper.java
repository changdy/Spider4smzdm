package com.smzdm.mapper;

import com.smzdm.model.CommodityTimeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityTimeInfoMapper {
    int insert(CommodityTimeInfo record);

    CommodityTimeInfo selectByPrimaryKey(Integer id);

    List<CommodityTimeInfo> selectAll();

    int insertList(List<CommodityTimeInfo> commodityTimeInfoList);

    String getLastComment(@Param("commodityId")Long commodityId,@Param("discoveryFlag")Boolean discoveryFlag);
}