package com.rainbow.controller;

import com.rainbow.entity.ChatMessage;
import com.rainbow.service.ChatMessageService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/chat")
@RestController
public class WxChatController {

    private final ChatMessageService service;

    public WxChatController(ChatMessageService service) {
        this.service = service;
    }

    @GetMapping("/history")
    public List<ChatMessage> chatHistory(@RequestParam Long fromUserId, @RequestParam Long toUserId, @RequestParam(defaultValue = "30") int limit) {
        return service.findHistory(fromUserId, toUserId, limit);
    }

    @GetMapping("/unread")
    public Map<Long, Integer> getUnreadCount(@RequestParam Long userId) {
        return service.getUnreadCount(userId);
    }

    @PostMapping("/markAsRead")
    public void markAsRead(@RequestParam Long fromUserId, @RequestParam Long toUserId) {
        service.markAsRead(fromUserId, toUserId);
    }
}
