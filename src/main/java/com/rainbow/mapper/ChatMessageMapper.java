package com.rainbow.mapper;

import com.rainbow.entity.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface ChatMessageMapper {
    int insert(ChatMessage message);
    List<ChatMessage> findHistory(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId, @Param("limit") int limit);
    List<Map<String, Object>> countUnreadMessages(@Param("userId") Long userId);
    void markAsRead(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
} 