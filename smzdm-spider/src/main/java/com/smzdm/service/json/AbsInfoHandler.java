package com.smzdm.service.json;

import com.alibaba.fastjson.JSONObject;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityTimeInfo;
import com.smzdm.model.Jsons;
import com.smzdm.service.DateTimeHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Changdy on 2017/3/18.
 */
public abstract class AbsInfoHandler {

    @Autowired
    private DateTimeHandler dateTimeHandler;

    public Commodity initCommodity(JSONObject content) {
        Commodity commodity = new Commodity();
        commodity.setArticleId(content.getLong("article_id"));
        commodity.setTitle(content.getString("article_title"));
        commodity.setContent(content.getString("article_content").replaceAll("<.+?>", ""));
        commodity.setPriceString(content.getString("article_price"));
        JSONObject gtm = content.getJSONObject("gtm");
        if (gtm != null) {
            commodity.setCategories(gtm.getString("cates_str").replaceAll("/无",""));
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
        String articlePic = content.getString("article_pic");
        if (articlePic != null) {
            commodity.setPicUrl(articlePic);
        } else {
            commodity.setPicUrl(content.getString("article_pic_url"));
        }
        commodity.setInfoUrl(content.getString("article_url"));
        commodity.setMall(content.getString("article_mall"));
        commodity.setMallUrl(content.getString("article_mall_url"));
        commodity.setShoppingUrl(content.getString("article_link"));
        String articleDate = content.getString("article_date").trim();
        commodity.setReferralDate(dateTimeHandler.convertToDate(articleDate));
        commodity.setTimeSort(content.getLong("timesort"));
        commodity.setCreateDate(LocalDateTime.now());
        return commodity;
    }

    public Jsons initJsons(JSONObject content) {
        Jsons jsons = new Jsons();
        jsons.setCreateDate(new Date());
        jsons.setContent(content.toJSONString());
        jsons.setOriginalDate(content.getString("article_date").trim());
        jsons.setTimeSort(content.getLong("timesort"));
        return jsons;
    }

    public CommodityTimeInfo initCommodityTimeInfo(JSONObject content, boolean discovery) {
        CommodityTimeInfo commodityTimeInfo = new CommodityTimeInfo();
        commodityTimeInfo.setCollection(content.getInteger("article_collection"));
        commodityTimeInfo.setComment(content.getInteger("article_comment"));
        commodityTimeInfo.setArticleId(content.getLong("article_id"));
        commodityTimeInfo.setUpdateTime(new Date());
        if (discovery) {
            commodityTimeInfo.setWorthy(content.getInteger("article_rating"));
            commodityTimeInfo.setTimeout(content.getInteger("is_timeout"));
            commodityTimeInfo.setDiscoveryFlag(1);
            commodityTimeInfo.setUnworthy(0);
        } else {
            commodityTimeInfo.setWorthy(content.getInteger("article_worthy"));
            commodityTimeInfo.setUnworthy(content.getInteger("article_unworthy"));
            commodityTimeInfo.setSoldOut(content.getInteger("article_is_sold_out"));
            commodityTimeInfo.setTimeout(content.getInteger("article_is_timeout"));
        }
        return commodityTimeInfo;
    }
}
