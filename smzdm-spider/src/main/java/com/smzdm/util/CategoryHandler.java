package com.smzdm.util;

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
    @Autowired
    private CategoryMapper categoryMapper;

    private String categoryIds;

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }


    public void initIds() {
        categoryIds = categoryMapper.getCategoryIds();
        System.out.println(categoryIds);
    }

    public void tryToInsertCategory(JSONObject jsonContent) {
        JSONObject articleCategory = jsonContent.getJSONObject("article_category");
        if (articleCategory == null) {
            return;
        }
        if (categoryIds.contains(articleCategory.getString("ID"))) {
            return;
        }
        List<Category> categoryList = new ArrayList();
        JSONArray categoryLayer = jsonContent.getJSONArray("category_layer");
        for (int i = categoryLayer.size() - 1; i >= 1; i--) {
            Integer id = categoryLayer.getJSONObject(i).getInteger("ID");
            if (!categoryIds.contains(String.valueOf(id))) {
                String title = categoryLayer.getJSONObject(i).getString("title");
                String urlNicktitle = categoryLayer.getJSONObject(i).getString("url_nicktitle");
                Integer parentId = categoryLayer.getJSONObject(i).getInteger("parent_id");
                categoryList.add(new Category(id, title, parentId, urlNicktitle, i));
                categoryIds += "," + id;
            } else {
                break;
            }
        }
        if (categoryList.size()>1) {
            categoryMapper.insertCategoryList(categoryList);
        }else {
            categoryMapper.insert(categoryList.get(0));
        }
    }
}
