package com.smzdm.service;

import com.smzdm.mapper.CommodityFilterMapper;
import com.smzdm.model.Commodity;
import com.smzdm.model.CommodityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Changdy on 2017/6/13.
 * 处理订阅
 */
@Service
public class CommoditySub {
    private CommodityFilterMapper commodityFilterMapper;

    private List<CommodityFilter> commodityFilters = new ArrayList<>();

    private List<Commodity> readyList = new ArrayList<>();

    private List<Long> sentList = new ArrayList<>();

    private EmailService emailService;

    @Autowired
    public CommoditySub(CommodityFilterMapper commodityFilterMapper, EmailService emailService) {
        this.commodityFilterMapper = commodityFilterMapper;
        this.commodityFilters = commodityFilterMapper.selectAll();
        this.emailService = emailService;
    }


    public void validCommodity(List<Commodity> commodityList) {
        List<Commodity> matchList = new ArrayList<>();
        for (Commodity commodity : commodityList) {
            for (CommodityFilter commodityFilter : commodityFilters) {
                String title = commodity.getTitle();
                String titleMatch = commodityFilter.getTitleMatch();
                if (!titleMatch.isEmpty() && !validTitle(titleMatch, title)) {
                    break;
                }
                String titleUnmatch = commodityFilter.getTitleUnmatch();
                if (!titleUnmatch.isEmpty() && validTitle(titleUnmatch, title)) {
                    break;
                }
                String categories = commodity.getCategories();
                String categoryMatch = commodityFilter.getCategoryMatch();
                if (!categoryMatch.isEmpty() && !validCategory(categoryMatch, categories)) {
                    break;
                }
                String categoryUnmatch = commodityFilter.getCategoryUnmatch();
                if (!categoryUnmatch.isEmpty() && validCategory(categoryUnmatch, categories)) {
                    break;
                }
                if (commodityFilter.getIgnoreComment() == 1) {
                    matchList.add(commodity);
                } else {
                    readyList.add(commodity);
                }
            }
        }
        if (matchList.size() > 0) {
            try {
                if (matchList.size() == 1) {
                    emailService.sendMsg(matchList.get(0).getTitle(), matchList.get(0).getContent() + "\n" + "购买地址: http://www.smzdm.com/p/" + matchList.get(0).getArticleId());
                } else {
                    StringBuilder content = new StringBuilder();
                    for (Commodity commodity : matchList) {
                        content.append("标题: ").append(commodity.getTitle()).append("\n描述: ").append(commodity.getContent()).append("\n购买地址: http://www.smzdm.com/p/").append(commodity.getArticleId());
                    }
                    emailService.sendMsg("捕获到" + matchList.size() + "条优惠信息", content.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validTitle(String params, String title) {
        String args[] = params.split(",");
        for (String match : args) {
            if (title.contains(match)) {
                return true;
            }
        }
        return false;
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
}
