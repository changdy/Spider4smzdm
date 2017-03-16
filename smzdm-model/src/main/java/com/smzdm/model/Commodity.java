package com.smzdm.model;

import java.time.LocalDateTime;

public class Commodity {
    private Long id;

    private Long articleId;

    private Integer discoveryFlag;

    private String title;

    private String content;

    private String tags;

    private String infoTitle;

    private String brand;

    private String priceString;

    private Long priceNumber;

    private Integer lastCategoryId;

    private String referralName;

    private String picUrl;

    private String infoUrl;

    private Integer channelId;

    private String mall;

    private String mallUrl;

    private String shoppingUrl;

    private LocalDateTime referralDate;

    private Long timeSort;

    private LocalDateTime createDate;

    public Commodity() {
    }

    public Commodity(Long id, Long articleId, Integer discoveryFlag, String title, String content, String tags, String infoTitle, String brand, String priceString, Long priceNumber, Integer lastCategoryId, String referralName, String picUrl, String infoUrl, Integer channelId, String mall, String mallUrl, String shoppingUrl, LocalDateTime referralDate, Long timeSort, LocalDateTime createDate) {
        this.id = id;
        this.articleId = articleId;
        this.discoveryFlag = discoveryFlag;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.infoTitle = infoTitle;
        this.brand = brand;
        this.priceString = priceString;
        this.priceNumber = priceNumber;
        this.lastCategoryId = lastCategoryId;
        this.referralName = referralName;
        this.picUrl = picUrl;
        this.infoUrl = infoUrl;
        this.channelId = channelId;
        this.mall = mall;
        this.mallUrl = mallUrl;
        this.shoppingUrl = shoppingUrl;
        this.referralDate = referralDate;
        this.timeSort = timeSort;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getDiscoveryFlag() {
        return discoveryFlag;
    }

    public void setDiscoveryFlag(Integer discoveryFlag) {
        this.discoveryFlag = discoveryFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    public Long getPriceNumber() {
        return priceNumber;
    }

    public void setPriceNumber(Long priceNumber) {
        this.priceNumber = priceNumber;
    }

    public Integer getLastCategoryId() {
        return lastCategoryId;
    }

    public void setLastCategoryId(Integer lastCategoryId) {
        this.lastCategoryId = lastCategoryId;
    }

    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getMallUrl() {
        return mallUrl;
    }

    public void setMallUrl(String mallUrl) {
        this.mallUrl = mallUrl;
    }

    public String getShoppingUrl() {
        return shoppingUrl;
    }

    public void setShoppingUrl(String shoppingUrl) {
        this.shoppingUrl = shoppingUrl;
    }

    public LocalDateTime getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(LocalDateTime referralDate) {
        this.referralDate = referralDate;
    }

    public Long getTimeSort() {
        return timeSort;
    }

    public void setTimeSort(Long timeSort) {
        this.timeSort = timeSort;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}