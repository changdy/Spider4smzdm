package com.smzdm.controller;
import com.alibaba.fastjson.JSON;
import com.smzdm.mapper.CommodityMapper;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Changdy on 2017/3/4.
 */
@Controller
public class TestController {

    @Autowired
    private CommodityMapper commodityMapper;

    @RequestMapping("/get-commodity")
    public @ResponseBody String  getcommodity(CommodityParams CommodityParams) {
        Commodity commodity = commodityMapper.selectByPrimaryKey(7014905l);
        Map<String,Object> map  =new HashMap<>();
        map.put("rows", new ArrayList(Collections.singletonList(commodity)));
        map.put("total",1);
        String s = JSON.toJSONString(map);
        System.out.println(s);
        return s;
    }
}
