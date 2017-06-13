package com.smzdm.model;

/**
 * Created by Changdy on 2017/6/13.
 */
public class CommodityFilter {
    private Long id;
    private String name;
    private String titleMatch;
    private String titleUnmatch;
    private String categoryMatch;
    private String categoryUnmatch;
    private Integer ratingCount;
    private Integer worthPercent;

    public CommodityFilter() {
    }

    public CommodityFilter(Long id, String name, String titleMatch, String titleUnmatch, String categoryMatch, String categoryUnmatch, Integer ratingCount, Integer worthPercent) {

        this.id = id;
        this.name = name;
        this.titleMatch = titleMatch;
        this.titleUnmatch = titleUnmatch;
        this.categoryMatch = categoryMatch;
        this.categoryUnmatch = categoryUnmatch;
        this.ratingCount = ratingCount;
        this.worthPercent = worthPercent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "CommodityFilter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", titleMatch='" + titleMatch + '\'' +
                ", titleUnmatch='" + titleUnmatch + '\'' +
                ", categoryMatch='" + categoryMatch + '\'' +
                ", categoryUnmatch='" + categoryUnmatch + '\'' +
                ", ratingCount=" + ratingCount +
                ", worthPercent=" + worthPercent +
                '}';
    }
}
