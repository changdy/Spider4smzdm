package com.smzdm.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionLog implements HandlerExceptionResolver {

    private static Logger logger = LogManager.getLogger(ExceptionLog.class.getName());

    //系统抛出的异常  
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("ERROR", ex);
        return null;
    }

}  

