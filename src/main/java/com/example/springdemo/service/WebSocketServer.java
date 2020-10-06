package com.example.springdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多列模式
 * 每次连接都是单独的对象
 */
@ServerEndpoint("/websocket")
@Component
@Slf4j
public class WebSocketServer {

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;

        String userId = session.getId();

        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            //加入set中
        } else {
            webSocketMap.put(userId, this);
            //加入set中
            int andSet = onlineCount.getAndSet(1);
            //在线数加1
            log.info("当前在线人数{}", andSet);
        }
        onlineCount.getAndAdd(1);

        log.info("连接成功,当前连接数{}", onlineCount.get());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String userId = session.getId();

        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //从set中删除
            onlineCount.getAndDecrement();
        }
        log.info("用户{}退出:,当前在线人数为:{}", userId, onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("来自{}的消息推送消息{}", session.getId(), message);

        webSocketMap.get(session.getId()).session.getAsyncRemote().sendText((session.getId() + "返回的数据" + new Random(System.currentTimeMillis()).nextInt(2000)));

    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.session.getId() + ",原因:" + error.getMessage());
        error.printStackTrace();
    }


}
