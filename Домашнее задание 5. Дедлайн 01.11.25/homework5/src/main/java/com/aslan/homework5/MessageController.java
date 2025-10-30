package com.aslan.homework5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @GetMapping
    public List<Message> getMessages(@RequestParam(required = false) String authorName,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        if (authorName != null && !authorName.isEmpty()) {
            return messageRepository.findByAuthorName(authorName, PageRequest.of(page, size)).getContent();
        }
        return messageRepository.findAll(PageRequest.of(page, size)).getContent();
    }
    @PostMapping
    public Message createMessage(@RequestBody MessageRequest request) {
        User user = userRepository.findById(request.authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Message msg = new Message();
        msg.setContent(request.content);
        msg.setAuthor(user);
        msg.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(msg);
    }
    static class MessageRequest {
        public Long authorId;
        public String content;
    }
}
