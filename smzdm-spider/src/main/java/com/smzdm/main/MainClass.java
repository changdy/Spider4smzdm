package com.smzdm.main;

import com.smzdm.util.CategoryHandler;
import com.smzdm.util.ChannelHandler;
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
        ctx.getBean(CategoryHandler.class).initIds();
        ctx.getBean(ChannelHandler.class).initList();
        //ctx.getBean(StartSpider.class).startHomePageSpider();
    }
}
