package com.smzdm.controller;

import com.alibaba.fastjson.JSON;
import com.smzdm.model.CommodityParams;
import com.smzdm.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Changdy on 2017/3/21.
 */
@Controller
public class CommodityController {
    private final CommodityService commodityService;

    @Autowired
    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @ResponseBody
    @RequestMapping("/query-commodity")
    public String queryList(CommodityParams commodityParams) {
        return JSON.toJSONString(commodityService.queryList(commodityParams));
    }
}
