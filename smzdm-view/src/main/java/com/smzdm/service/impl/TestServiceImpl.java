package com.smzdm.service.impl;

import com.smzdm.mapper.CommodityMapper;
import com.smzdm.mapper.CommodityTimeInfoMapper;
import com.smzdm.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Changdy on 2017/3/12.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CommodityTimeInfoMapper commodityTimeInfoMapper;

    @Override
    public Map test() {
        List<HashMap> keyInfoMap = commodityMapper.getKeyInfoMap();
        for (int i = 0; i < keyInfoMap.size(); i++) {
            Map map = keyInfoMap.get(i);
            Long id = (Long) map.get("articleId");
            String lastComment = commodityTimeInfoMapper.getLastComment(id);
            map.put("comment", lastComment);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", 100);
        map.put("rows", keyInfoMap);
        return map;
    }
}
