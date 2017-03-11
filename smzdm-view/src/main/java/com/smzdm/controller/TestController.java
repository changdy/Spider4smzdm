package com.smzdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Changdy on 2017/3/4.
 */
@Controller
public class TestController {


    @RequestMapping("/start-spider")
    public @ResponseBody String  startSpider() {
        return "test";
    }
}
