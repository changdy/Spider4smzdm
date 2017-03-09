package com.smzdm.main;

import com.smzdm.util.CategoryHandler;
import com.smzdm.util.ChannelHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Changdy on 2017/3/7.
 */
public class MainClass {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ctx.getBean(CategoryHandler.class).initIds();
        ctx.getBean(ChannelHandler.class).initList();
        //ctx.getBean(StartSpider.class).startHomePageSpider();
    }
}
