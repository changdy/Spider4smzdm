package com.smzdm.model;

import java.util.List;

/**
 * Created by Changdy on 2017/8/8.
 */
public class CommoditySearch {
    private Integer type;
    private Integer limit;
    private Integer offset;
    private Integer ratingCount;
    private String sort;
    private String endTime;
    private String startTime;
    private List<String> titleMatch;
    private List<String> titleUnmatch;
    private List<String> categoryMatchTitle;
    private List<String> categoryUnmatchTitle;

    public CommoditySearch() {
    }

    public CommoditySearch(Integer type, Integer limit, Integer offset, Integer ratingCount, String sort, String endTime, String startTime, List<String> titleMatch, List<String> titleUnmatch, List<String> categoryMatchTitle, List<String> categoryUnmatchTitle) {

        this.type = type;
        this.limit = limit;
        this.offset = offset;
        this.ratingCount = ratingCount;
        this.sort = sort;
        this.endTime = endTime;
        this.startTime = startTime;
        this.titleMatch = titleMatch;
        this.titleUnmatch = titleUnmatch;
        this.categoryMatchTitle = categoryMatchTitle;
        this.categoryUnmatchTitle = categoryUnmatchTitle;
    }

    public Integer getType() {

        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<String> getTitleMatch() {
        return titleMatch;
    }

    public void setTitleMatch(List<String> titleMatch) {
        this.titleMatch = titleMatch;
    }

    public List<String> getTitleUnmatch() {
        return titleUnmatch;
    }

    public void setTitleUnmatch(List<String> titleUnmatch) {
        this.titleUnmatch = titleUnmatch;
    }

    public List<String> getCategoryMatchTitle() {
        return categoryMatchTitle;
    }

    public void setCategoryMatchTitle(List<String> categoryMatchTitle) {
        this.categoryMatchTitle = categoryMatchTitle;
    }

    public List<String> getCategoryUnmatchTitle() {
        return categoryUnmatchTitle;
    }

    public void setCategoryUnmatchTitle(List<String> categoryUnmatchTitle) {
        this.categoryUnmatchTitle = categoryUnmatchTitle;
    }

    @Override
    public String toString() {
        return "CommoditySearch{" +
                "type=" + type +
                ", limit=" + limit +
                ", offset=" + offset +
                ", ratingCount=" + ratingCount +
                ", sort='" + sort + '\'' +
                ", endTime='" + endTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", titleMatch=" + titleMatch +
                ", titleUnmatch=" + titleUnmatch +
                ", categoryMatchTitle=" + categoryMatchTitle +
                ", categoryUnmatchTitle=" + categoryUnmatchTitle +
                '}';
    }
}
