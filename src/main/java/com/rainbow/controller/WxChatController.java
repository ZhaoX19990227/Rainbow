package com.rainbow.controller;

import com.rainbow.entity.ChatMessage;
import com.rainbow.service.ChatMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
