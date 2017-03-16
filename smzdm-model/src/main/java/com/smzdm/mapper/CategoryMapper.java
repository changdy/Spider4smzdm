package com.smzdm.mapper;

import com.smzdm.model.Category;

import java.util.List;

public interface CategoryMapper {
    int insert(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectAll();

    int insertCategoryList(List<Category> categoryList);

    String getCategoryIds();
}