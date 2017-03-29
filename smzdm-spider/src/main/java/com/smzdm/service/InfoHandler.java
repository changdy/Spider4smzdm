package com.smzdm.service;

import com.alibaba.fastjson.JSONArray;
import com.smzdm.model.CommodityContent;

/**
 * Created by Changdy on 2017/3/5.
 */
public interface InfoHandler {
    CommodityContent parseJSONArray(JSONArray jsonArray, Long maxTimesort);
}
