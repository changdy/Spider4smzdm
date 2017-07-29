package com.smzdm.service;

import com.smzdm.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by Changdy on 2017/7/29.
 */
@Service
public class CategoryService {
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<Map> getMainIfo() {
        return categoryMapper.getMainIfo();
    }
}
