package com.smzdm.controller;

import com.alibaba.fastjson.JSON;
import com.smzdm.model.CommodityFilter;
import com.smzdm.service.CommodityFilterService;
import com.smzdm.service.ServletMapConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Changdy on 2017/7/9.
 */
@ResponseBody
@Controller
public class CommodityFilterController {

    private CommodityFilterService commodityFilterMapper;

    private ServletMapConvert servletMapConvert;

    @Autowired
    public CommodityFilterController(CommodityFilterService commodityFilterMapper, ServletMapConvert servletMapConvert) {
        this.commodityFilterMapper = commodityFilterMapper;
        this.servletMapConvert = servletMapConvert;
    }


    @RequestMapping("/query-filter")
    public String queryFilter(HttpServletRequest httpServletRequest) {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", commodityFilterMapper.queryList(servletMapConvert.mapConvert(httpServletRequest)));
        result.put("total", commodityFilterMapper.getCount(servletMapConvert.mapConvert(httpServletRequest)));
        return JSON.toJSONString(result);
    }

    @RequestMapping("/operate-filter")
    public String operateFilter(CommodityFilter commodityFilter) {
        Map<String, Object> result = new HashMap<>();
        if (commodityFilter.getId() == null) {
            result.put("count", commodityFilterMapper.insert(commodityFilter));
        } else {
            result.put("count", commodityFilterMapper.update(commodityFilter));
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping("/remove-filter")
    public String removeFilter(@RequestParam("ids") String ids) {
        Map<String, Object> result = new HashMap<>();
        result.put("count", commodityFilterMapper.deleteByIds(ids));
        return JSON.toJSONString(result);
    }
}
