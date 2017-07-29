package com.smzdm.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.Socket;

/**
 * Created by Changdy on 2017/7/8.
 */
@Controller
public class TestSocket {
    @ResponseBody
    @RequestMapping("/test-socket")
    public String testSocket() throws IOException {
        StringBuilder info = new StringBuilder();
        try (Socket socket = new Socket("localhost", 8888);
             OutputStream os = socket.getOutputStream();
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
             InputStream is = socket.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            bufferedWriter.write("测试消息123222");
            bufferedWriter.flush();
            socket.shutdownOutput();//关闭输出流
            String temp;
            while ((temp = br.readLine()) != null) {//循环读取客户端的信息
                info.append(temp);
            }
        } catch (Exception e) {
            info.append(e.getCause()).append(e.getMessage());
        }
        return JSON.toJSONString(info);
    }
}
