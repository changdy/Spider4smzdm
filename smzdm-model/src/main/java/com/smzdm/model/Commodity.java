package com.smzdm.model;

import java.time.LocalDateTime;

public class Commodity {
    private Long id;

    private String title;

    private String content;

    private String tags;

    private String infoTitle;

    private String brand;

    private String priceString;

    private Long priceNumber;

    private String referralName;

    private String picUrl;

    private String infoUrl;

    private Integer channelId;

    private String mall;

    private String mallUrl;

    private String shoppingUrl;

    private LocalDateTime referralDate;

    private Long timeSort;

    public Commodity(Long id, String title, String content, String tags, String infoTitle, String brand, String priceString, Long priceNumber, String referralName, String picUrl, String infoUrl, Integer channelId, String mall, String mallUrl, String shoppingUrl, LocalDateTime referralDate, Long timeSort) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.infoTitle = infoTitle;
        this.brand = brand;
        this.priceString = priceString;
        this.priceNumber = priceNumber;
        this.referralName = referralName;
        this.picUrl = picUrl;
        this.infoUrl = infoUrl;
        this.channelId = channelId;
        this.mall = mall;
        this.mallUrl = mallUrl;
        this.shoppingUrl = shoppingUrl;
        this.referralDate = referralDate;
        this.timeSort = timeSort;
    }

    public Commodity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle == null ? null : infoTitle.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString == null ? null : priceString.trim();
    }

    public Long getPriceNumber() {
        return priceNumber;
    }

    public void setPriceNumber(Long priceNumber) {
        this.priceNumber = priceNumber;
    }

    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName == null ? null : referralName.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl == null ? null : infoUrl.trim();
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
        this.mall = mall == null ? null : mall.trim();
    }

    public String getMallUrl() {
        return mallUrl;
    }

    public void setMallUrl(String mallUrl) {
        this.mallUrl = mallUrl == null ? null : mallUrl.trim();
    }

    public String getShoppingUrl() {
        return shoppingUrl;
    }

    public void setShoppingUrl(String shoppingUrl) {
        this.shoppingUrl = shoppingUrl == null ? null : shoppingUrl.trim();
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
}