package com.smzdm.service;

import com.smzdm.mapper.CommodityMapper;
import com.smzdm.model.CommodityParams;
import com.smzdm.model.CommoditySearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Changdy on 2017/3/21.
 */
@Service
@Transactional
public class CommodityService {
    private final CommodityMapper commodityMapper;


    private static Logger logger = LogManager.getLogger(CommodityService.class.getName());

    @Autowired
    public CommodityService(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    public Map<String, Object> queryList(CommodityParams commodityParams) {
        List<Map<String, Object>> commodityList = commodityMapper.queryList(commodityParams);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("rows", commodityList);
            if (commodityList != null) {
                for (Map<String, Object> map : commodityList) {
                    String comment = map.get("commentInfo").toString();
                    if (comment.equals("0/0/0/0")) {
                        map.put("commentInfo", "-");
                    }
                }
                resultMap.put("total", commodityMapper.getCommodityCount(commodityParams));
            } else {
                resultMap.put("total", 0);
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
        }
        return resultMap;
    }


    public Map<String, Object> queryListInfo(CommoditySearch commoditySearch) {
        Map<String, Object> commodityList = new HashMap<>();
        List<Map<String, Object>> infoList = commodityMapper.queryListInfo(commoditySearch);
        infoList.forEach(map -> {
            String categories = (String) map.get("categories");
            if (categories != null && !("").equals(categories)) {
                String[] split = categories.split("/");
                map.put("category", split[0]);
            } else {
                map.put("category", "");
            }
        });
        commoditySearch.setSort(null);
        int count = commodityMapper.getListCount(commoditySearch);
        commodityList.put("total", count);
        commodityList.put("rows", infoList);
        return commodityList;
    }
}
