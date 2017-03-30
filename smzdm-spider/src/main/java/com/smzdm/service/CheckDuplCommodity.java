package com.smzdm.service;

import com.smzdm.mapper.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Changdy on 2017/3/30.
 */
@Service
public class CheckDuplCommodity {
    @Autowired
    private CommodityMapper commodityMapper;

    @Scheduled(cron = "0 57 * * * ?")
    public void checkEveryHour() {
        LocalDateTime checkTime = LocalDateTime.now().plusHours(-2);
        commodityMapper.deleteDupl(checkTime);
    }
}
