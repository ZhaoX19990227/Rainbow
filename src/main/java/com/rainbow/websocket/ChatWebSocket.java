package com.rainbow.websocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbow.entity.ChatMessage;
import com.rainbow.mapper.ChatMessageMapper;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.*;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/chat/{userId}")
@Component
public class ChatWebSocket {
    private static ChatMessageMapper chatMessageMapper;
    private static final Map<Long, Session> userSessionMap = new ConcurrentHashMap();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ChatMessageMapper mapper;

    @PostConstruct
    public void init() {
        chatMessageMapper = mapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        userSessionMap.put(userId, session);
    }

    @OnClose
    public void onClose(@PathParam("userId") Long userId) {
        userSessionMap.remove(userId);
    }

    @OnMessage
    public void onMessage(String messageJson, @PathParam("userId") Long fromUserId) throws IOException {
        // 前端需发送JSON: {toUserId, content, type, isAnonymous}
        ChatMessage msg = objectMapper.readValue(messageJson, ChatMessage.class);
        msg.setFromUserId(fromUserId);
        msg.setTimestamp(new java.util.Date());
        // 存储到数据库
        chatMessageMapper.insert(msg);
        // 推送给目标用户，fromUserId为0时前端显示匿名
        Session toSession = userSessionMap.get(msg.getToUserId());
        if (toSession != null && toSession.isOpen()) {
            toSession.getBasicRemote().sendText(objectMapper.writeValueAsString(msg));
        }
        // 回显给自己（可选）
        if (!msg.getIsAnonymous()) {
            Session fromSession = userSessionMap.get(fromUserId);
            if (fromSession != null && fromSession.isOpen()) {
                fromSession.getBasicRemote().sendText(objectMapper.writeValueAsString(msg));
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
} 