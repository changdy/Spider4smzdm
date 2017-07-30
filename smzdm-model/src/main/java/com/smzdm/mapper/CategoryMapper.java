package com.smzdm.mapper;

import com.smzdm.model.Category;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    int insert(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectAll();

    int insertCategoryList(List<Category> categoryList);

    List<Map> getMainIfo();

    int deleteByIds(String ids);
}