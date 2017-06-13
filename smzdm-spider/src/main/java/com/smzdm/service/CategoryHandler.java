package com.smzdm.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smzdm.mapper.CategoryMapper;
import com.smzdm.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Changdy on 2017/3/6.
 */
@Service
public class CategoryHandler {
    private final CategoryMapper categoryMapper;

    private List<Category> categoryList;

    @Autowired
    public CategoryHandler(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
        categoryList = categoryMapper.selectAll();
    }

    public void tryToInsertCategory(JSONObject jsonContent) {
        JSONObject articleCategory = jsonContent.getJSONObject("article_category");
        if (articleCategory == null) {
            return;
        }
        if (checkInTheList(articleCategory.getInteger("ID"))) {
            return;
        }
        //保存新增的
        List<Category> newCategoryList = new ArrayList<>();
        JSONArray categoryLayer = jsonContent.getJSONArray("category_layer");
        for (int i = categoryLayer.size() - 1; i >= 1; i--) {
            Integer id = categoryLayer.getJSONObject(i).getInteger("ID");
            if (!checkInTheList(id)) {
                String title = categoryLayer.getJSONObject(i).getString("title");
                String urlNickTitle = categoryLayer.getJSONObject(i).getString("url_nicktitle");
                Integer parentId = categoryLayer.getJSONObject(i).getInteger("parent_id");
                Category category = new Category(id, title, parentId, urlNickTitle, i);
                newCategoryList.add(category);
                categoryList.add(category);
            } else {
                break;
            }
        }
        if (newCategoryList.size() > 1) {
            categoryMapper.insertCategoryList(newCategoryList);
        } else {
            categoryMapper.insert(newCategoryList.get(0));
        }
    }

    //true的时候表示在里面，false则不在
    private boolean checkInTheList(Integer id) {
        for (Category category : categoryList) {
            if (category.getId().compareTo(id) == 0) {
                return true;
            }
        }
        return false;
    }

    public Integer getCategoryId(String title) {
        if (title == null || title.isEmpty()) {
            return null;
        }
        for (Category category : categoryList) {
            if (category.getTitle().equals(title)) {
                return category.getId();
            }
        }
        return null;
    }
}
