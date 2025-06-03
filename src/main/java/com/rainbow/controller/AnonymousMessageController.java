// src/main/java/com/rainbow/controller/AnonymousMessageController.java
package com.rainbow.controller;

import com.rainbow.entity.AnonymousMessage;
import com.rainbow.mapper.AnonymousMessageMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anonymous")
public class AnonymousMessageController {
    private final AnonymousMessageMapper mapper;

    public AnonymousMessageController(AnonymousMessageMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping("/send")
    public int send(@RequestBody AnonymousMessage msg) {
        if (msg.getTimestamp() == null) {
            msg.setTimestamp(new java.util.Date());
        }

        msg.setIsRead(false); // Ensure isRead is set to false on insert

        return mapper.insert(msg);
    }

    @GetMapping("/list")
    public List<AnonymousMessage> list(@RequestParam Long toUserId) {
        return mapper.findByToUser(toUserId);
    }

    @GetMapping("/replies")
    public List<AnonymousMessage> replies(@RequestParam Long replyToId) {
        return mapper.findReplies(replyToId);
    }



    @PostMapping("/markSingleAsRead")
    public void markSingleAsRead(@RequestParam Long messageId, @RequestParam Long toUserId) {
         if (messageId == null || toUserId == null) {
              return;
         }
         mapper.markAsReadByMessageId(messageId, toUserId);
    }
}