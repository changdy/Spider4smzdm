package com.smzdm.main;

import com.smzdm.service.CategoryHandler;
import com.smzdm.service.ChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Changdy on 2017/3/7.
 */
public class MainClass {
    private static Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        logger.info("===========爬虫开始了======");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        //StartSpider bean = ctx.getBean(StartSpider.class);
        //bean.startDiscoverySpider();
        //bean.startHomePageSpider();
    }
}
