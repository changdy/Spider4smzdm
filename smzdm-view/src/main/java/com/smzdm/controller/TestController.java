package com.smzdm.controller;
import com.alibaba.fastjson.JSON;
import com.smzdm.mapper.CommodityMapper;
import com.smzdm.mapper.CommodityTimeInfoMapper;
import com.smzdm.mapper.RelationMapper;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityParams;
import com.smzdm.service.TestService;
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
    private TestService testService;

    @RequestMapping("/get-commodity")
    public @ResponseBody String  getcommodity(CommodityParams CommodityParams) {
        String s = JSON.toJSONString(testService.test());
        return s;
    }
}
