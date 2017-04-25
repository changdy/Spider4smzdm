package com.smzdm.model;

import java.util.Date;

public class CommodityTimeInfo {
    private Integer id;

    private Long articleId;

    private Integer comment;

    private Integer collection;

    private Integer worthy;

    private Integer unworthy;

    private Integer soldOut;

    private Integer timeout;

    private Integer discoveryFlag;

    private Date updateTime;

    public CommodityTimeInfo() {
    }

    public CommodityTimeInfo(Integer id, Long articleId, Integer comment, Integer collection, Integer worthy, Integer unworthy, Integer soldOut, Integer timeout, Integer discoveryFlag, Date updateTime) {

        this.id = id;
        this.articleId = articleId;
        this.comment = comment;
        this.collection = collection;
        this.worthy = worthy;
        this.unworthy = unworthy;
        this.soldOut = soldOut;
        this.timeout = timeout;
        this.discoveryFlag = discoveryFlag;
        this.updateTime = updateTime;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Integer getWorthy() {
        return worthy;
    }

    public void setWorthy(Integer worthy) {
        this.worthy = worthy;
    }

    public Integer getUnworthy() {
        return unworthy;
    }

    public void setUnworthy(Integer unworthy) {
        this.unworthy = unworthy;
    }

    public Integer getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Integer soldOut) {
        this.soldOut = soldOut;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getDiscoveryFlag() {
        return discoveryFlag;
    }

    public void setDiscoveryFlag(Integer discoveryFlag) {
        this.discoveryFlag = discoveryFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CommodityTimeInfo{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", comment=" + comment +
                ", collection=" + collection +
                ", worthy=" + worthy +
                ", unworthy=" + unworthy +
                ", soldOut=" + soldOut +
                ", timeout=" + timeout +
                ", discoveryFlag=" + discoveryFlag +
                ", updateTime=" + updateTime +
                '}';
    }
}