package com.smzdm.model;

public class TimeSort {
    private Integer id;

    private Long timeSort;

    public TimeSort(Integer id, Long timeSort) {
        this.id = id;
        this.timeSort = timeSort;
    }

    public TimeSort() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimeSort() {
        return timeSort;
    }

    public void setTimeSort(Long timeSort) {
        this.timeSort = timeSort;
    }
}