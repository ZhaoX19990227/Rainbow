// src/main/java/com/rainbow/entity/AnonymousMessage.java
package com.rainbow.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonymousMessage {
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private String content;
    private Long replyToId; // 被回复的匿名消息ID
    private Date timestamp;
    private Boolean isRead; // Added field for read status
}