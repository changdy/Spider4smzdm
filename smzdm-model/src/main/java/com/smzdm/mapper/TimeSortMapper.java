package com.smzdm.mapper;

import com.smzdm.model.TimeSort;

import java.util.List;

public interface TimeSortMapper {
    int insert(TimeSort record);

    TimeSort selectByPrimaryKey(Integer id);

    List<TimeSort> selectAll();

    int updateByPrimaryKey(TimeSort timesort);
}