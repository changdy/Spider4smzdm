package com.smzdm.service.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityContent;
import com.smzdm.model.Relation;
import com.smzdm.service.CategoryHandler;
import com.smzdm.service.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Changdy on 2017/3/5.
 */
@Service
public class HomePageHandler extends AbsInfoHandler {
    private final ChannelHandler channelHandler;

    private final CategoryHandler categoryHandler;

    @Autowired
    public HomePageHandler(ChannelHandler channelHandler, CategoryHandler categoryHandler) {
        this.channelHandler = channelHandler;
        this.categoryHandler = categoryHandler;
    }


    public CommodityContent parseJSONArray(JSONArray jsonArray, Long maxTimesort) {
        CommodityContent commodityContent = new CommodityContent();
        for (int arrayIndex = 0; arrayIndex < jsonArray.size(); arrayIndex++) {
            JSONObject jsonContent = jsonArray.getJSONObject(arrayIndex);
            boolean skip = false;
            Integer channelId = channelHandler.getChannelId(jsonContent);
            if (channelId < 6 || channelId > 9) {
                skip = true;
            }
            if (jsonContent.getLong("timesort") > maxTimesort && !skip) {
                //初始化
                Commodity commodity = initCommodity(jsonContent);
                commodity.setChannelId(channelId);
                commodity.setDiscoveryFlag(0);
                JSONArray articleTeseTags = jsonContent.getJSONArray("article_tese_tags");
                if (articleTeseTags != null && articleTeseTags.size() > 0) {
                    StringBuilder tags = new StringBuilder();
                    for (int i = 0; i < articleTeseTags.size(); i++) {
                        tags.append(articleTeseTags.getJSONObject(i).getString("name")).append("、");
                    }
                    commodity.setTags(tags.substring(0, tags.length() - 1));
                }
                JSONArray mallMoreInfo = jsonContent.getJSONArray("mall_more_info");
                if (mallMoreInfo != null && mallMoreInfo.size() > 0) {
                    StringBuilder infoTitle = new StringBuilder();
                    for (int i = 0; i < mallMoreInfo.size(); i++) {
                        infoTitle.append(mallMoreInfo.getJSONObject(i).getString("title")).append("、");
                    }
                    commodity.setInfoTitle(infoTitle.substring(0, infoTitle.length() - 1));
                }
                JSONArray categoryLayer = jsonContent.getJSONArray("category_layer");
                if (categoryLayer != null && categoryLayer.size() > 0) {
                    categoryHandler.tryToInsertCategory(jsonContent);
                    for (int i = 0; i < categoryLayer.size(); i++) {
                        JSONObject jsonObject = categoryLayer.getJSONObject(i);
                        commodityContent.getRelationList().add(new Relation(null, jsonContent.getLong("article_id"), jsonObject.getInteger("ID")));
                    }
                }
                commodityContent.getCommodityList().add(commodity);
                commodityContent.getJsonsList().add(initJsons(jsonContent));
                commodity.setTopCategoryId(categoryHandler.getCategoryId(jsonContent.getString("top_category")));
            }
            if (!skip) {
                commodityContent.getCommodityTimeInfoList().add(initCommodityTimeInfo(jsonContent, false));
            }
        }
        return commodityContent;
    }
}