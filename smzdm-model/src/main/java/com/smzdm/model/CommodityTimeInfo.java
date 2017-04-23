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

    private Date updateTime;

    public CommodityTimeInfo(Integer id, Long articleId, Integer comment, Integer collection, Integer worthy, Integer unworthy, Integer soldOut, Integer timeout, Date updateTime) {
        this.id = id;
        this.articleId = articleId;
        this.comment = comment;
        this.collection = collection;
        this.worthy = worthy;
        this.unworthy = unworthy;
        this.soldOut = soldOut;
        this.timeout = timeout;
        this.updateTime = updateTime;
    }

    public CommodityTimeInfo() {
        super();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}