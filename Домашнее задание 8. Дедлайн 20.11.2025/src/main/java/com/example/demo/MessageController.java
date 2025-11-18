package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    @GetMapping("/api/messages")
    public List<String> getMessages() {
        return List.of("message-1", "message-2");
    }
}
