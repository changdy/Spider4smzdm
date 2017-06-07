package com.smzdm.service.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityContent;
import com.smzdm.service.CategoryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Changdy on 2017/3/17.
 */
@Service
public class DiscoveryHandler extends AbsInfoHandler {

    private final CategoryHandler categoryHandler;

    @Autowired
    public DiscoveryHandler(CategoryHandler categoryHandler) {
        this.categoryHandler = categoryHandler;
    }



    public CommodityContent parseJSONArray(JSONArray jsonArray, Long maxTimesort) {
        CommodityContent commodityContent = new CommodityContent();
        for (int arrayIndex = 0; arrayIndex < jsonArray.size(); arrayIndex++) {
            JSONObject jsonContent = jsonArray.getJSONObject(arrayIndex);
            Long timesort = jsonContent.getLong("timesort");
            if (timesort > maxTimesort) {
                Commodity commodity = initCommodity(jsonContent);
                commodity.setDiscoveryFlag(1);
                Integer productIconType = jsonContent.getInteger("product_icon_type");
                if (productIconType != null && jsonContent.getInteger("product_icon_type") == 6) {
                    commodity.setChannelId(7);
                } else {
                    Integer articleChannel = jsonContent.getInteger("article_channel");
                    if (articleChannel != null) {
                        switch (articleChannel) {
                            case 1:commodity.setChannelId(6);break;
                            case 2:commodity.setChannelId(10);break;
                            case 5:commodity.setChannelId(7);break;
                        }
                    }
                }
                commodity.setTopCategoryId(categoryHandler.getCategoryId(jsonContent.getString("article_top_category")));
                commodityContent.getCommodityList().add(commodity);
                commodityContent.getJsonsList().add(initJsons(jsonContent));
            }
            commodityContent.getCommodityTimeInfoList().add(initCommodityTimeInfo(jsonContent, true));
        }
        return commodityContent;
    }
}
