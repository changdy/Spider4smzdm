package com.smzdm.mapper;

import com.smzdm.model.Relation;

import java.util.List;

public interface RelationMapper {
    int insert(Relation record);

    Relation selectByPrimaryKey(Integer id);

    List<Relation> selectAll();

    int insertList(List<Relation> relationList);

    String getLastTitle(Long commodityId);
}