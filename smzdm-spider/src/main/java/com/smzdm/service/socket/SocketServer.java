package com.smzdm.service.socket;

import com.smzdm.service.CommodityFilterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Changdy on 2017/7/8.
 */
@Service
public class SocketServer {

    private static Logger logger = LoggerFactory.getLogger(SocketServer.class);
    private CommodityFilterHandler commodityFilterHandler;

    @Autowired
    public SocketServer(CommodityFilterHandler commodityFilterHandler) {
        this.commodityFilterHandler = commodityFilterHandler;
    }

    public void initSocket() {
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(1762);
            //记录客户端的数量
            //循环监听等待客户端的连接
            while (true) {
                //调用accept()方法开始监听，等待客户端的连接
                Socket socket = serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread = new ServerThread(socket, commodityFilterHandler);
                //启动线程
                serverThread.start();
                InetAddress address = socket.getInetAddress();
                logger.info("当前客户端的IP：" + address.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
