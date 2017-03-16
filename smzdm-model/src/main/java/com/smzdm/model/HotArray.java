package com.smzdm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Changdy on 2017/3/6.
 */
public class HotArray {
    private List<Commodity> commodityList = new ArrayList<>();
    private List<CommodityTimeInfo> commodityTimeInfoList = new ArrayList<>();
    private List<Jsons> jsonsList = new ArrayList<>();
    private List<Relation> relationList = new ArrayList<>();

    @Override
    public String toString() {
        return "HotArray{" +
                "commodityList=" + commodityList +
                ", commodityTimeInfoList=" + commodityTimeInfoList +
                ", jsonsList=" + jsonsList +
                ", relationList=" + relationList +
                '}';
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public List<CommodityTimeInfo> getCommodityTimeInfoList() {
        return commodityTimeInfoList;
    }

    public void setCommodityTimeInfoList(List<CommodityTimeInfo> commodityTimeInfoList) {
        this.commodityTimeInfoList = commodityTimeInfoList;
    }

    public List<Jsons> getJsonsList() {
        return jsonsList;
    }

    public void setJsonsList(List<Jsons> jsonsList) {
        this.jsonsList = jsonsList;
    }

    public List<Relation> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<Relation> relationList) {
        this.relationList = relationList;
    }
}
