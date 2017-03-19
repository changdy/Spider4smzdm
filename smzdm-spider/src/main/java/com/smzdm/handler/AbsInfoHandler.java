package com.smzdm.handler;

import com.alibaba.fastjson.JSONObject;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityTimeInfo;
import com.smzdm.model.Jsons;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Changdy on 2017/3/18.
 */
abstract class AbsInfoHandler implements InfoHandler {

    @Autowired
    private DateTimeHandler dateTimeHandler;

    Commodity initCommodity(JSONObject content) {
        Commodity commodity = new Commodity();
        commodity.setArticleId(content.getLong("article_id"));
        commodity.setTitle(content.getString("article_title"));
        commodity.setContent(content.getString("article_content").replaceAll("<.+?>", ""));
        commodity.setPriceString(content.getString("article_price"));
        JSONObject gtm = content.getJSONObject("gtm");
        if (gtm != null) {
            commodity.setBrand(gtm.getString("brand"));
            String rmbPrice = gtm.getString("rmb_price");
            if (rmbPrice != null && !rmbPrice.equals("") && !rmbPrice.equals("无")) {
                try {
                    commodity.setPriceNumber(Long.valueOf(rmbPrice));
                } catch (Exception e) {
                    commodity.setPriceNumber(0L);
                }
            }
        }
        commodity.setReferralName(content.getString("article_referrals"));
        commodity.setPicUrl(content.getString("article_pic"));
        if (content.getString("article_url") != null) {
            commodity.setInfoUrl(content.getString("article_url") );
        }else {
            commodity.setInfoUrl(content.getString("article_pic_url") );
        }
        commodity.setMall(content.getString("article_mall"));
        commodity.setMallUrl(content.getString("article_mall_url"));
        commodity.setShoppingUrl(content.getString("article_link"));
        String articleDate = content.getString("article_date").trim();
        commodity.setReferralDate(dateTimeHandler.convertToDate(articleDate));
        commodity.setTimeSort(content.getLong("timesort"));
        commodity.setCreateDate(LocalDateTime.now());
        return commodity;
    }

    Jsons initJsons(JSONObject content) {
        Jsons jsons = new Jsons();
        jsons.setCreateDate(new Date());
        jsons.setContent(content.toJSONString());
        jsons.setOriginalDate(content.getString("article_date"));
        jsons.setTimeSort(content.getLong("timesort"));
        return jsons;
    }

    CommodityTimeInfo initCommodityTimeInfo(JSONObject content, boolean discovery) {
        CommodityTimeInfo commodityTimeInfo = new CommodityTimeInfo();
        commodityTimeInfo.setCollection(content.getInteger("article_collection"));
        commodityTimeInfo.setComment(content.getInteger("article_comment"));
        commodityTimeInfo.setCommodityId(content.getLong("article_id"));
        commodityTimeInfo.setUpdateTime(new Date());
        if (!discovery) {
            commodityTimeInfo.setWorthy(content.getInteger("article_worthy"));
            commodityTimeInfo.setUnworthy(content.getInteger("article_unworthy"));
            commodityTimeInfo.setSoldOut(content.getInteger("article_is_sold_out"));
            commodityTimeInfo.setTimeout(content.getInteger("article_is_timeout"));
        } else {
            commodityTimeInfo.setWorthy(content.getInteger("article_rating"));
            commodityTimeInfo.setUnworthy(-1);
            commodityTimeInfo.setTimeout(content.getInteger("is_timeout"));
        }
        return commodityTimeInfo;
    }
}
