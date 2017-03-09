package com.smzdm.mapper;

import com.smzdm.model.Timesort;
import java.util.List;

public interface TimesortMapper {
    int insert(Timesort record);

    Timesort selectByPrimaryKey(Integer id);

    List<Timesort> selectAll();

    int updateByPrimaryKey(Timesort timesort);
}