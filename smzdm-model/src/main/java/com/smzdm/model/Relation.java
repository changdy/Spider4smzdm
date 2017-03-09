package com.smzdm.model;

public class Relation {
    private Integer id;

    private Long commodityId;

    private Integer categoryId;

    public Relation(Integer id, Long commodityId, Integer categoryId) {
        this.id = id;
        this.commodityId = commodityId;
        this.categoryId = categoryId;
    }

    public Relation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}