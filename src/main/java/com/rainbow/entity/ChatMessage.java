package com.rainbow.entity;

import lombok.Data;
import java.util.Date;

@Data
public class ChatMessage {
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private String content;
    private String type; // text/image/emoji
    private Boolean isAnonymous = false;
    private Date timestamp;
} 