package com.example.hellospring.service;
import com.example.hellospring.model.Message;
import com.example.hellospring.repository.MessageRepository;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class MessageService {
    private final MessageRepository repository;
    private static final int DEFAULT_LIMIT = 30;
    public MessageService(MessageRepository repository) {
        this.repository = repository;
        initializeSampleData();
    }
    private void initializeSampleData() {
        repository.save(new Message("system", "Hello, Spring!"));
        repository.save(new Message("system", "In-memory storage works"));
    }
    public List<Message> getAllMessages(Integer limit) {
        int l = (limit == null || limit <= 0) ? DEFAULT_LIMIT : Math.min(limit, DEFAULT_LIMIT);
        return repository.findAllAscending(l);
    }
    public Message save(String author, String content) { return repository.save(new Message(author, content)); }
    public void clear() { repository.clear(); initializeSampleData(); }
}
