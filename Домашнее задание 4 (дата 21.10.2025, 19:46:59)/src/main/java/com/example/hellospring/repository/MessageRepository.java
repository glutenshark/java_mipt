package com.example.hellospring.repository;
import com.example.hellospring.model.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
@Repository
public class MessageRepository {
    private final ConcurrentMap<Long, Message> storage = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);
    public Message save(Message message) {
        if (message.getId() == null) { long id = idCounter.incrementAndGet(); message.setId(id); }
        storage.put(message.getId(), message);
        return message;
    }
    public Optional<Message> findById(Long id) { return Optional.ofNullable(storage.get(id)); }
    public List<Message> findAllAscending(int limit) {
        List<Long> ids = new ArrayList<>(storage.keySet());
        Collections.sort(ids);
        List<Message> result = new ArrayList<>();
        int start = Math.max(0, ids.size() - limit);
        for (int i = start; i < ids.size(); i++) result.add(storage.get(ids.get(i)));
        return result;
    }
    public void deleteById(Long id) { storage.remove(id); }
    public void clear() { storage.clear(); }
    public int count() { return storage.size(); }
}
