package com.smzdm.controller;

import com.alibaba.fastjson.JSON;
`import com.alibaba.fastjson.JSONObject;
import com.smzdm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    public String queryCategory() {
        return JSON.toJSONString(categoryService.getMainIfo());
    }

    @ResponseBody
    @RequestMapping("/delete-category")
    public String deleteCategory(@RequestParam("ids") String ids, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        int count = -1;
        Object login = request.getSession().getAttribute("login");
        if (login != null) {
            Boolean loginFlag = Boolean.valueOf(login.toString());
            if (loginFlag) {
                count = categoryService.deleteByIds(ids);
            }
        }
        result.put("count", count);
        return JSON.toJSONString(result);
    }
}
