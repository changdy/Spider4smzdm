package com.smzdm.model;

import java.util.Date;

public class Jsons {
    private Long id;

    private Date createDate;

    private String originalDate;

    private Long timeSort;

    private String content;

    public Jsons(Long id, Date createDate, String originalDate, Long timeSort, String content) {
        this.id = id;
        this.createDate = createDate;
        this.originalDate = originalDate;
        this.timeSort = timeSort;
        this.content = content;
    }

    public Jsons() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOriginalDate() {
        return originalDate;
    }

    public void setOriginalDate(String originalDate) {
        this.originalDate = originalDate == null ? null : originalDate.trim();
    }

    public Long getTimeSort() {
        return timeSort;
    }

    public void setTimeSort(Long timeSort) {
        this.timeSort = timeSort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}