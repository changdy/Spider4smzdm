package com.smzdm.util;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Changdy on 2017/3/6.
 */
@Service
public class DateTimeHandler {
    public LocalDateTime convertToDate(String string) {
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            if (string.contains("-")) {
                if (string.contains(":")) {
                    LocalDate today = LocalDate.now();
                    int year = today.getYear();
                    string = year + "-" + string;
                    localDateTime = LocalDateTime.parse(string);
                } else {
                    LocalDate localDate = LocalDate.parse(string);
                    localDateTime = localDate.atTime(20, 0);
                }
            } else {
                LocalTime localTime = LocalTime.parse(string);
                localDateTime = localTime.atDate(LocalDate.now());
            }
            if (localDateTime.isBefore(LocalDateTime.now().plusHours(20))) {
                localDateTime.plusDays(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localDateTime;
    }
}
