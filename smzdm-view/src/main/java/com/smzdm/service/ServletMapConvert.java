package com.smzdm.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Changdy on 2017/7/9.
 */
@Service
public class ServletMapConvert {
    public HashMap<String, String> mapConvert(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        HashMap<String, String> convertedMap = new HashMap<>();
        parameterMap.forEach((key, value) -> {
            convertedMap.put(key, value[0]);
        });
        return convertedMap;
    }
}
