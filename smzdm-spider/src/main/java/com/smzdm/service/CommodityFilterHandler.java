package com.smzdm.service;

import com.smzdm.mapper.CommodityFilterMapper;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityFilter;
import com.smzdm.model.CommodityTimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Changdy on 2017/6/13.
 * 处理订阅
 */
@Service
public class CommodityFilterHandler {

    private List<CommodityFilter> commodityFilters = new ArrayList<>();

    private Map<Commodity, Long> readyMap = new HashMap<>();

    private EmailHandler emailService;

    @Autowired
    public CommodityFilterHandler(CommodityFilterMapper commodityFilterMapper, EmailHandler emailService) {
        this.commodityFilters = commodityFilterMapper.selectAll();
        this.emailService = emailService;
    }


    public void validCommodity(List<Commodity> commodityList) {
        List<Commodity> matchList = new ArrayList<>();
        for (Commodity commodity : commodityList) {
            for (CommodityFilter commodityFilter : commodityFilters) {
                String title = commodity.getTitle();
                String titleMatch = commodityFilter.getTitleMatch();
                String titleUnmatch = commodityFilter.getTitleUnmatch();
                if (!validTitle(titleMatch, title, true) || validTitle(titleUnmatch, title, false)) {
                    continue;
                }
                String categories = commodity.getCategories();
                String categoryMatch = commodityFilter.getCategoryMatch();
                if (!categoryMatch.isEmpty() && !validCategory(categoryMatch, categories)) {
                    continue;
                }
                String categoryUnmatch = commodityFilter.getCategoryUnmatch();
                if (!categoryUnmatch.isEmpty() && validCategory(categoryUnmatch, categories)) {
                    continue;
                }
                if (commodityFilter.getIgnoreComment() == 1) {
                    matchList.add(commodity);
                } else {
                    readyMap.put(commodity, commodityFilter.getId());
                }
                break;
            }
        }
        emailService.sendMsg(matchList);
    }


    public void validTimeInfo(List<CommodityTimeInfo> infoList) {
        List<Commodity> matchList = new ArrayList<>();
        for (CommodityTimeInfo commodityTimeInfo : infoList) {
            Long articleId = commodityTimeInfo.getArticleId();
            final Commodity[] temp = new Commodity[1];
            readyMap.forEach((commodity, filterId) -> {
                if (articleId.equals(commodity.getArticleId())) {
                    Integer worthy = commodityTimeInfo.getWorthy();
                    Integer collection = commodityTimeInfo.getCollection();
                    Integer comment = commodityTimeInfo.getComment();
                    Integer count = worthy + collection + comment;
                    int filterListIndex = getFilterListIndex(filterId);
                    if (filterListIndex != -1) {
                        CommodityFilter commodityFilter = commodityFilters.get(filterListIndex);
                        if (count > commodityFilter.getRatingCount() && Math.floor(worthy * 100 / count ) > commodityFilter.getWorthPercent()) {
                            matchList.add(commodity);
                            temp[0] = commodity;
                        }
                    }
                }
            });
            readyMap.remove(temp[0]);
        }
        emailService.sendMsg(matchList);
    }

    private int getFilterListIndex(Long id) {
        for (int i = 0; i < commodityFilters.size(); i++) {
            if (commodityFilters.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private boolean validTitle(String params, String title, boolean matchModel) {
        if (params == null || params.isEmpty()) {
            return matchModel;
        }
        String args[] = params.split(",");
        for (String match : args) {
            if (title.contains(match)) {
                if (!matchModel) {
                    return true;
                }
            } else {
                if (matchModel) {
                    return false;
                }
            }
        }
        return matchModel;
    }

    private boolean validCategory(String params, String categories) {
        String[] paramArr = params.split(",");
        String[] categoryArr = categories.split("/");
        for (String param : paramArr) {
            for (String category : categoryArr) {
                if (category.equals(param)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteItem(Long id) {
        List<Commodity> commodityList = new ArrayList<>();
        readyMap.forEach((commodity, filterId) -> {
            if (commodity.getId() < id) {
                commodityList.add(commodity);
            }
        });
        for (Commodity commodity : commodityList) {
            readyMap.remove(commodity);
        }
    }
}
