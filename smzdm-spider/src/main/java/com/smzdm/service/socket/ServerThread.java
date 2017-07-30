package com.smzdm.service.socket;

import com.alibaba.fastjson.JSONObject;
import com.smzdm.service.CommodityFilterHandler;

import java.io.*;
import java.net.Socket;

/*
 * 服务器线程处理类
 */
public class ServerThread extends Thread {
    // 和本线程相关的Socket
    private Socket socket;
    private CommodityFilterHandler commodityFilterHandler;

    public ServerThread(Socket socket, CommodityFilterHandler commodityFilterHandler) {
        this.socket = socket;
        this.commodityFilterHandler = commodityFilterHandler;
    }

    //线程执行的操作，响应客户端的请求
    public void run() {
        try (InputStream is = socket.getInputStream();
             InputStreamReader isr = new InputStreamReader(is, "UTF-8");
             BufferedReader br = new BufferedReader(isr);
             OutputStream os = socket.getOutputStream();
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"))) {
            //获取输入流，并读取客户端信息
            StringBuilder info = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {//循环读取客户端的信息
                info.append(temp);
            }
            JSONObject jsonObject = JSONObject.parseObject(info.toString());
            if (jsonObject.getString("type").equals("reset")) {
                commodityFilterHandler.reset();
            }
            socket.shutdownInput();//关闭输入流
            //获取输出流，响应客户端的请求
            bufferedWriter.write("欢迎您！");
            bufferedWriter.flush();//调用flush()方法将缓冲输出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
