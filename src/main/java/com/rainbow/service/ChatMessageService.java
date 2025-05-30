package com.rainbow.service;


import com.rainbow.entity.ChatMessage;
import com.rainbow.mapper.ChatMessageMapper;
import com.rainbow.mapper.WxUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageMapper mapper;

    public ChatMessageService(ChatMessageMapper mapper) {
        this.mapper = mapper;
    }

    public List<ChatMessage> findHistory(Long fromUserId, Long toUserId, int limit) {
        return mapper.findHistory(fromUserId, toUserId, limit);
    }
}
