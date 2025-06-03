package com.rainbow.service;

import com.rainbow.entity.ChatMessage;
import com.rainbow.mapper.ChatMessageMapper;
import com.rainbow.mapper.WxUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ChatMessageService {

    private final ChatMessageMapper mapper;

    public ChatMessageService(ChatMessageMapper mapper) {
        this.mapper = mapper;
    }

    public List<ChatMessage> findHistory(Long fromUserId, Long toUserId, int limit) {
        return mapper.findHistory(fromUserId, toUserId, limit);
    }

    public Map<Long, Integer> getUnreadCount(Long userId) {
        List<Map<String, Object>> unreadList = mapper.countUnreadMessages(userId);
        Map<Long, Integer> result = new HashMap<>();
        for (Map<String, Object> item : unreadList) {
            Long fromUserId = ((Number) item.get("userId")).longValue();
            Integer count = ((Number) item.get("count")).intValue();
            result.put(fromUserId, count);
        }
        return result;
    }

    public void markAsRead(Long fromUserId, Long toUserId) {
        mapper.markAsRead(fromUserId, toUserId);
    }
}
