package com.smzdm.jsonhandler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smzdm.model.*;
import com.smzdm.util.CategoryHandler;
import com.smzdm.util.ChannelHandler;
import com.smzdm.util.DateTimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Changdy on 2017/3/5.
 */
@Service
public class HotInfoHandler implements InfoHandler {
    @Autowired
    private ChannelHandler channelHandler;

    @Autowired
    private CategoryHandler categoryHandler;

    @Autowired
    private DateTimeHandler dateTimeHandler;


    @Override
    public HotArray parseJSONArray(JSONArray jsonArray, Long maxTimesort) {
        HotArray hotArray = new HotArray();
        for (int arrayIndex = 0; arrayIndex < jsonArray.size(); arrayIndex++) {
            JSONObject jsonContent = jsonArray.getJSONObject(arrayIndex);
            boolean skip = false;
            Integer channelId = channelHandler.getChannelId(jsonContent);
            if (channelId < 6) {
                skip = true;
            }
            if (jsonContent.getLong("timesort") > maxTimesort) {
                Commodity commodity = new Commodity();
                commodity.setId(jsonContent.getLong("article_id"));
                commodity.setTitle(jsonContent.getString("article_title"));
                commodity.setContent(jsonContent.getString("article_content").replaceAll("<.+?>", ""));
                commodity.setPriceString(jsonContent.getString("article_price"));
                JSONArray articleTeseTags = jsonContent.getJSONArray("article_tese_tags");
                if (articleTeseTags != null && articleTeseTags.size() > 0) {
                    String tags = "";
                    for (int i = 0; i < articleTeseTags.size(); i++) {
                        tags += articleTeseTags.getJSONObject(i).getString("name");
                    }
                    commodity.setTags(tags);
                }
                JSONArray mallMoreInfo = jsonContent.getJSONArray("mall_more_info");
                if (mallMoreInfo != null && mallMoreInfo.size() > 0) {
                    String infoTitle = "";
                    for (int i = 0; i < mallMoreInfo.size(); i++) {
                        infoTitle += mallMoreInfo.getJSONObject(i).getString("title");
                    }
                    commodity.setInfoTitle(infoTitle);
                }
                JSONObject gtm = jsonContent.getJSONObject("gtm");
                if (gtm != null) {
                    commodity.setBrand(gtm.getString("brand"));
                    String rmbPrice = gtm.getString("rmb_price");
                    if (rmbPrice != null && !rmbPrice.equals("") && !rmbPrice.equals("无")) {
                        try {
                            commodity.setPriceNumber(Long.valueOf(rmbPrice));
                        } catch (Exception e) {
                            commodity.setPriceNumber(0l);
                        }
                    }
                }
                commodity.setReferralName(jsonContent.getString("article_referrals"));
                commodity.setPicUrl(jsonContent.getString("article_pic"));
                commodity.setInfoUrl(jsonContent.getString("article_url"));
                commodity.setChannelId(channelId);
                commodity.setMall(jsonContent.getString("article_mall"));
                commodity.setMallUrl(jsonContent.getString("article_mall_url"));
                commodity.setShoppingUrl(jsonContent.getString("article_link"));
                String articleDate = jsonContent.getString("article_date").trim();
                commodity.setReferralDate(dateTimeHandler.convertToDate(articleDate));
                Long timesort = jsonContent.getLong("timesort");
                commodity.setTimeSort(timesort);
                JSONArray categoryLayer = jsonContent.getJSONArray("category_layer");
                if (categoryLayer != null && categoryLayer.size() > 0) {
                    categoryHandler.tryToInsertCategory(jsonContent);
                    for (int i = 0; i < categoryLayer.size(); i++) {
                        JSONObject jsonObject = categoryLayer.getJSONObject(i);
                        hotArray.getRelationList().add(new Relation(null, jsonContent.getLong("article_id"), jsonObject.getInteger("ID")));
                    }
                }
                Jsons jsons = new Jsons();
                jsons.setCreateDate(new Date());
                jsons.setContent(jsonArray.getJSONObject(arrayIndex).toJSONString());
                jsons.setOriginalDate(articleDate);
                jsons.setTimeSort(timesort);
                hotArray.getCommodityList().add(commodity);
                hotArray.getJsonsList().add(jsons);
            }
            if (!skip) {
                CommodityTimeInfo commodityTimeInfo = new CommodityTimeInfo();
                commodityTimeInfo.setCollection(jsonContent.getInteger("article_collection"));
                commodityTimeInfo.setWorthy(jsonContent.getInteger("article_worthy"));
                commodityTimeInfo.setUnworthy(jsonContent.getInteger("article_unworthy"));
                commodityTimeInfo.setSoldOut(jsonContent.getInteger("article_is_sold_out"));
                commodityTimeInfo.setTimeout(jsonContent.getInteger("article_is_timeout"));
                commodityTimeInfo.setCommodityId(jsonContent.getLong("article_id"));
                commodityTimeInfo.setComment(jsonContent.getInteger("article_comment"));
                commodityTimeInfo.setUpdateTime(new Date());
                hotArray.getCommodityTimeInfoList().add(commodityTimeInfo);
            }
        }
        return hotArray;
    }
}
