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
    private String categoryMatchIds;
    private String categoryUnmatchIds;
    private Integer ratingCount;
    private Integer worthPercent;
    private Integer ignoreComment;

    public CommodityFilter() {
    }

    public CommodityFilter(Long id, String name, String titleMatch, String titleUnmatch, String categoryMatch, String categoryUnmatch, String categoryMatchIds, String categoryUnmatchIds, Integer ratingCount, Integer worthPercent, Integer ignoreComment) {

        this.id = id;
        this.name = name;
        this.titleMatch = titleMatch;
        this.titleUnmatch = titleUnmatch;
        this.categoryMatch = categoryMatch;
        this.categoryUnmatch = categoryUnmatch;
        this.categoryMatchIds = categoryMatchIds;
        this.categoryUnmatchIds = categoryUnmatchIds;
        this.ratingCount = ratingCount;
        this.worthPercent = worthPercent;
        this.ignoreComment = ignoreComment;
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

    public String getCategoryMatchIds() {
        return categoryMatchIds;
    }

    public void setCategoryMatchIds(String categoryMatchIds) {
        this.categoryMatchIds = categoryMatchIds;
    }

    public String getCategoryUnmatchIds() {
        return categoryUnmatchIds;
    }

    public void setCategoryUnmatchIds(String categoryUnmatchIds) {
        this.categoryUnmatchIds = categoryUnmatchIds;
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

    public Integer getIgnoreComment() {
        return ignoreComment;
    }

    public void setIgnoreComment(Integer ignoreComment) {
        this.ignoreComment = ignoreComment;
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
                ", categoryMatchIds='" + categoryMatchIds + '\'' +
                ", categoryUnmatchIds='" + categoryUnmatchIds + '\'' +
                ", ratingCount=" + ratingCount +
                ", worthPercent=" + worthPercent +
                ", ignoreComment=" + ignoreComment +
                '}';
    }
}
