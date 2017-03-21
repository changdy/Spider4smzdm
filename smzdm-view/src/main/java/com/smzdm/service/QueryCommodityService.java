package com.smzdm.service;

import com.smzdm.model.CommodityParams;

import java.util.Map;

/**
 * Created by Changdy on 2017/3/21.
 */
public interface QueryCommodityService {
    Map<String,Object> queryCommodity(CommodityParams commodityParams);
}
