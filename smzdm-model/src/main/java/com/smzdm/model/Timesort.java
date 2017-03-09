package com.smzdm.model;

public class Timesort {
    private Integer id;

    private Long timesort;

    public Timesort(Integer id, Long timesort) {
        this.id = id;
        this.timesort = timesort;
    }

    public Timesort() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimesort() {
        return timesort;
    }

    public void setTimesort(Long timesort) {
        this.timesort = timesort;
    }
}