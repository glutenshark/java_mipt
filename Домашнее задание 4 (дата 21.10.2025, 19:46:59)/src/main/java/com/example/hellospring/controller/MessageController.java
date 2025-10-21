package com.example.hellospring.controller;
import com.example.hellospring.dto.NewMessageRequest;
import com.example.hellospring.model.Message;
import com.example.hellospring.service.MessageService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService service;
    public MessageController(MessageService service) { this.service = service; }
    @GetMapping
    public List<Message> getMessages(@RequestParam(name = "limit", required = false) Integer limit) {
        return service.getAllMessages(limit);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message addMessage(@Valid @RequestBody NewMessageRequest req) {
        return service.save(req.getAuthor(), req.getContent());
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clear() { service.clear(); }
}
