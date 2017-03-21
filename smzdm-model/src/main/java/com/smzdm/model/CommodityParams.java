package com.smzdm.model;


/**
 * Created by Changdy on 2017/3/12.
 */
public class CommodityParams {
    //正序反序
    private String sort;
    //排序的列名
    private String order;
    //偏移量
    private Long offset;
    //取多少
    private Long limit;
    //标题
    private String search;

    public CommodityParams() {
    }



    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
