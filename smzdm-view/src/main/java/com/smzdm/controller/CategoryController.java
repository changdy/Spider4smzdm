package com.smzdm.controller;

import com.alibaba.fastjson.JSON;
import com.smzdm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Changdy on 2017/7/29.
 */
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ResponseBody
    @RequestMapping("/get-category")
    public String queryFilter() {
        return JSON.toJSONString(categoryService.getMainIfo());
    }
}
