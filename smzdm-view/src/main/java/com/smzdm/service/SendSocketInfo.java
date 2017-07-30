package com.smzdm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

/**
 * Created by Changdy on 2017/7/30.
 */
@Service
public class SendSocketInfo {
    private static Logger logger = LoggerFactory.getLogger(SendSocketInfo.class);

    public void sendMsg(String content) {
        try (Socket socket = new Socket("localhost", 1762);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
            bufferedWriter.write(content);
            bufferedWriter.flush();
            socket.shutdownOutput();//关闭输出流
        } catch (Exception e) {
            logger.error("SendSocketInfo:" + e.getCause() + "\n" + e.getMessage());
        }
    }
}
