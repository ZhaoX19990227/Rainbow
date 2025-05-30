package com.rainbow.mapper;

import com.rainbow.entity.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ChatMessageMapper {
    int insert(ChatMessage message);
    List<ChatMessage> findHistory(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId, @Param("limit") int limit);
} 