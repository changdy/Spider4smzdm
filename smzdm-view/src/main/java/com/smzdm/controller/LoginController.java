package com.smzdm.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Changdy on 2017/7/8.
 */
@Controller
public class LoginController {
    @ResponseBody
    @RequestMapping("/user-login")
    public String testSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Map<String, Object> resultMap = new HashMap<>();
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        if (("1").equals(name) && ("2").equals(pwd)) {
            Cookie cookie = new Cookie("user-name", "admin");
            cookie.setMaxAge(800);
            response.addCookie(cookie);
            session.setAttribute("login", true);
            resultMap.put("result", true);
        } else {
            resultMap.put("result", false);
        }
        return JSON.toJSONString(resultMap);
    }
}
