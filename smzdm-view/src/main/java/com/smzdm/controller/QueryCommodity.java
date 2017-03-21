package com.smzdm.controller;

import com.alibaba.fastjson.JSON;
import com.smzdm.model.CommodityParams;
import com.smzdm.service.QueryCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Changdy on 2017/3/21.
 */
@Controller
public class QueryCommodity {
    private final QueryCommodityService queryCommodityService;

    @Autowired
    public QueryCommodity(QueryCommodityService queryCommodityService) {
        this.queryCommodityService = queryCommodityService;
    }

    @RequestMapping("/query-commodity")
    public @ResponseBody String getcommodity(CommodityParams commodityParams) {
        return JSON.toJSONString(queryCommodityService.queryCommodity(commodityParams));
    }

}
