package com.smzdm.mapper;

import com.smzdm.model.Jsons;

import java.util.List;

public interface JsonsMapper {
    int insert(Jsons record);

    Jsons selectByPrimaryKey(Long id);

    List<Jsons> selectAll();

    int insertList(List<Jsons> jsonsList);
}