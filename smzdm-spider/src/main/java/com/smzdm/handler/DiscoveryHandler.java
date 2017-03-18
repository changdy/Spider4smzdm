package com.smzdm.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smzdm.model.CommodityContent;

/**
 * Created by Changdy on 2017/3/17.
 */
public class DiscoveryHandler implements InfoHandler {
    @Override
    public CommodityContent parseJSONArray(JSONArray jsonArray, Long maxTimesort) {
        CommodityContent commodityContent = new CommodityContent();
        for (int arrayIndex = 0; arrayIndex < jsonArray.size(); arrayIndex++) {
            JSONObject jsonContent = jsonArray.getJSONObject(arrayIndex);
            boolean skip = false;
            //Integer channelId = channelHandler.getChannelId(jsonContent);
            //if (channelId < 6) {
            //    skip = true;
            //}
        }
        return null;
    }
}
