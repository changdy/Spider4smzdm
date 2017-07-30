package com.smzdm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smzdm.model.CommodityFilter;
import com.smzdm.service.CommodityFilterService;
import com.smzdm.service.SendSocketInfo;
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

    private SendSocketInfo sendSocketInfo;

    @Autowired
    public CommodityFilterController(CommodityFilterService commodityFilterMapper, ServletMapConvert servletMapConvert, SendSocketInfo sendSocketInfo) {
        this.commodityFilterMapper = commodityFilterMapper;
        this.servletMapConvert = servletMapConvert;
        this.sendSocketInfo = sendSocketInfo;
    }


    @RequestMapping("/query-filter")
    public String queryFilter(HttpServletRequest httpServletRequest) {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", commodityFilterMapper.queryList(servletMapConvert.mapConvert(httpServletRequest)));
        result.put("total", commodityFilterMapper.getCount(servletMapConvert.mapConvert(httpServletRequest)));
        return JSON.toJSONString(result);
    }

    @RequestMapping("/operate-filter")
    public String operateFilter(CommodityFilter commodityFilter, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        int count = -1;
        Object login = request.getSession().getAttribute("login");
        if (login != null) {
            Boolean loginFlag = Boolean.valueOf(login.toString());
            if (loginFlag) {
                if (commodityFilter.getId() == null) {
                    count = commodityFilterMapper.insert(commodityFilter);
                } else {
                    count = commodityFilterMapper.update(commodityFilter);
                }
                JSONObject info = new JSONObject();
                info.put("type", "reset");
                sendSocketInfo.sendMsg(info.toJSONString());
            }
        }
        result.put("count", count);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/delete-filter")
    public String removeFilter(@RequestParam("ids") String ids, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        int count = -1;
        Object login = request.getSession().getAttribute("login");
        if (login != null) {
            Boolean loginFlag = Boolean.valueOf(login.toString());
            if (loginFlag) {
                count = commodityFilterMapper.deleteByIds(ids);
                JSONObject info = new JSONObject();
                info.put("type", "reset");
                sendSocketInfo.sendMsg(info.toJSONString());
            }
        }
        result.put("count", count);
        return JSON.toJSONString(result);
    }
}
