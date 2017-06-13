package com.smzdm.model;

/**
 * Created by Changdy on 2017/6/13.
 */
public class CommodityFilter {
    private Long id;
    private String titleMatch;
    private String titleUnmatch;
    private String categoryMatch;
    private String categoryUnmatch;
    private Integer ratingCount;
    private Integer worthPercent;

    @Override
    public String toString() {
        return "CommodityFilter{" +
                "id=" + id +
                ", titleMatch='" + titleMatch + '\'' +
                ", titleUnmatch='" + titleUnmatch + '\'' +
                ", categoryMatch='" + categoryMatch + '\'' +
                ", categoryUnmatch='" + categoryUnmatch + '\'' +
                ", ratingCount=" + ratingCount +
                ", worthPercent=" + worthPercent +
                '}';
    }

    public CommodityFilter(Long id, String titleMatch, String titleUnmatch, String categoryMatch, String categoryUnmatch, Integer ratingCount, Integer worthPercent) {
        this.id = id;
        this.titleMatch = titleMatch;
        this.titleUnmatch = titleUnmatch;
        this.categoryMatch = categoryMatch;
        this.categoryUnmatch = categoryUnmatch;
        this.ratingCount = ratingCount;
        this.worthPercent = worthPercent;
    }

    public CommodityFilter() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleMatch() {
        return titleMatch;
    }

    public void setTitleMatch(String titleMatch) {
        this.titleMatch = titleMatch;
    }

    public String getTitleUnmatch() {
        return titleUnmatch;
    }

    public void setTitleUnmatch(String titleUnmatch) {
        this.titleUnmatch = titleUnmatch;
    }

    public String getCategoryMatch() {
        return categoryMatch;
    }

    public void setCategoryMatch(String categoryMatch) {
        this.categoryMatch = categoryMatch;
    }

    public String getCategoryUnmatch() {
        return categoryUnmatch;
    }

    public void setCategoryUnmatch(String categoryUnmatch) {
        this.categoryUnmatch = categoryUnmatch;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Integer getWorthPercent() {
        return worthPercent;
    }

    public void setWorthPercent(Integer worthPercent) {
        this.worthPercent = worthPercent;
    }
}
