// src/main/java/com/rainbow/mapper/AnonymousMessageMapper.java
package com.rainbow.mapper;

import com.rainbow.entity.AnonymousMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AnonymousMessageMapper {
    int insert(AnonymousMessage message);
    List<AnonymousMessage> findByToUser(@Param("toUserId") Long toUserId);
    List<AnonymousMessage> findReplies(@Param("replyToId") Long replyToId);
}