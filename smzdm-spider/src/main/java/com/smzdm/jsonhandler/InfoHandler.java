package com.smzdm.jsonhandler;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by Changdy on 2017/3/5.
 */
public interface InfoHandler {
    <T> T parseJSONArray(JSONArray jsonArray,Long maxTimesort);
}
